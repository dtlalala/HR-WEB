package cc.dt.hrweb.web.busi.controller;


import cc.dt.hrweb.web.busi.entity.DtTest;
import cc.dt.hrweb.web.busi.mapper.DtTestMapper;
import cc.dt.hrweb.common.controller.BaseController;
import cc.dt.hrweb.common.domain.QueryRequest;
import cc.dt.hrweb.web.busi.service.IDtTestService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author dtlalala
 */
@RestController
@RequestMapping("dttest")
public class DtTestController extends BaseController {
    @Autowired
    private IDtTestService iDtTestService;

    @Autowired
    private DtTestMapper dtTestMapper;

//    @GetMapping
//    public Map<String, Object> getDttest(QueryRequest request, DtTest dtTest){
//        IPage<DtTest> dttest = iDtTestService.getDttest(request,dtTest);
//        return this.getDataTable(dttest);
//    }

    @PostMapping
    public void insertDt(DtTest dtTest){
        this.iDtTestService.insertDt(dtTest);
    }

    @GetMapping
    @RequiresPermissions("test:view")
//    public HrWebResponse selectAll(){
//        List<DtTest> dtTests = dtTestMapper.selectAll();
//        return new HrWebResponse().data(dtTests);
//    }
    public Map<String, Object> findTests(QueryRequest request) {
        Page<DtTest> page = new Page<>(request.getPageNum(), request.getPageSize());
        return getDataTable(iDtTestService.page(page, null));
    }

}
