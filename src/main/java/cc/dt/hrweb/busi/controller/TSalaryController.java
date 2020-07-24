package cc.dt.hrweb.busi.controller;


import cc.dt.hrweb.busi.entity.TSalary;
import cc.dt.hrweb.busi.service.ITSalaryService;
import cc.dt.hrweb.common.controller.BaseController;
import cc.dt.hrweb.common.domain.HrWebResponse;
import cc.dt.hrweb.common.domain.QueryRequest;
import cc.dt.hrweb.common.exception.HrwebException;
import cc.dt.hrweb.system.domain.Test;
import cc.dt.hrweb.system.service.TestService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.wuwenze.poi.ExcelKit;
import com.wuwenze.poi.handler.ExcelReadHandler;
import com.wuwenze.poi.pojo.ExcelErrorField;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.IntStream;

/**
 * @author dtlalala
 */
@Slf4j
@RestController
@RequestMapping("test")
public class TSalaryController extends BaseController {

    private String message;

    @Autowired
    private ITSalaryService itSalaryService;

    @GetMapping
    public Map<String, Object> findTests(QueryRequest request) {
        Page<TSalary> page = new Page<>(request.getPageNum(), request.getPageSize());
        return getDataTable(itSalaryService.page(page, new QueryWrapper<TSalary>().orderByDesc("RELEASE_DATE")));
    }

    /**
     * 生成 Excel导入工资表模板
     */
    @PostMapping("template")
    public void generateImportTemplate(HttpServletResponse response) {
        // 构建数据
        List<TSalary> list = new ArrayList<>();
        IntStream.range(0, 5).forEach(i -> {
            TSalary tSalary = new TSalary();
            tSalary.setUserName("王明");
            tSalary.setBasicWage("8000");
            tSalary.setReleaseDate(new Date());
            Calendar c = Calendar.getInstance();
            SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM");
            String time = format.format(c.getTime());

            Calendar c1 = Calendar.getInstance();
            c1.add(Calendar.MONTH, -1);
            SimpleDateFormat format1 =  new SimpleDateFormat("yyyy-MM");
            String time1 = format.format(c.getTime());
            tSalary.setPayDate(time1 + "—" + time);
            list.add(tSalary);
        });
        // 构建模板
        ExcelKit.$Export(TSalary.class, response).downXlsx(list, true);
    }

    /**
     * 导入Excel数据，并批量插入工资表
     */
    @PostMapping("import")
    public HrWebResponse importExcels(@RequestParam("file") MultipartFile file) throws HrwebException {
        try {
            if (file.isEmpty()) {
                throw new HrwebException("导入数据为空");
            }
            String filename = file.getOriginalFilename();
            if (!StringUtils.endsWith(filename, ".xlsx")) {
                throw new HrwebException("只支持.xlsx类型文件导入");
            }
            // 开始导入操作
            long beginTimeMillis = System.currentTimeMillis();
            final List<TSalary> data = Lists.newArrayList();
            final List<Map<String, Object>> error = Lists.newArrayList();
            ExcelKit.$Import(TSalary.class).readXlsx(file.getInputStream(), new ExcelReadHandler<TSalary>() {
                @Override
                public void onSuccess(int sheet, int row, TSalary salary) {
                    // 数据校验成功时，加入集合
                    salary.setReleaseDate(new Date());
                    data.add(salary);
                }
                @Override
                public void onError(int sheet, int row, List<ExcelErrorField> errorFields) {
                    // 数据校验失败时，记录到 error集合
                    error.add(ImmutableMap.of("row", row, "errorFields", errorFields));
                }
            });
            if (!data.isEmpty()) {
                // 将合法的记录批量入库
                log.info("入库的数据是{}", data);
                this.itSalaryService.batchInsert(data);
            }
            long time = ((System.currentTimeMillis() - beginTimeMillis));
            ImmutableMap<String, Object> result = ImmutableMap.of(
                    "time", time,
                    "data", data,
                    "error", error
            );
            return new HrWebResponse().data(result);
        } catch (Exception e) {
            message = "导入Excel数据失败," + e.getMessage();
            log.error(message);
            throw new HrwebException(message);
        }
    }

    /**
     * 导出 Excel
     */
    @PostMapping("export")
    public void export(HttpServletResponse response) throws HrwebException {
        try {
            List<TSalary> list = this.itSalaryService.findSalary();
            ExcelKit.$Export(TSalary.class, response).downXlsx(list, false);
        } catch (Exception e) {
            message = "导出Excel失败";
            log.error(message, e);
            throw new HrwebException(message);
        }
    }
}
