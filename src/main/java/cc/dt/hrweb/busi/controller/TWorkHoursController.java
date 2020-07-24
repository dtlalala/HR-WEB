package cc.dt.hrweb.busi.controller;


import cc.dt.hrweb.busi.entity.TWorkHours;
import cc.dt.hrweb.busi.mapper.TWorkHoursMapper;
import cc.dt.hrweb.busi.service.ITWorkHoursService;
import cc.dt.hrweb.common.annotation.Log;
import cc.dt.hrweb.common.controller.BaseController;
import cc.dt.hrweb.common.domain.HrWebResponse;
import cc.dt.hrweb.common.domain.QueryRequest;
import cc.dt.hrweb.common.exception.HrwebException;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * @author dtlalala
 */
@Slf4j
@Validated
@RestController
@RequestMapping("worktime")
public class TWorkHoursController extends BaseController {

    private String message;

    @Autowired
    private ITWorkHoursService itWorkHoursService;

    @Autowired
    private TWorkHoursMapper tWorkHoursMapper;
    /**
     * 通过userId查询每个人未填报的普通工时 以及页面其他的数据
     * @param userId
     * @return list
     */
    @GetMapping("/{userId}")
    public HrWebResponse find(@PathVariable Long userId) throws HrwebException {
        try {
            List<TWorkHours> list = itWorkHoursService.findById(userId);
            Calendar c = Calendar.getInstance();
            c.add(Calendar.MONTH, 0);
            c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
            Long countMonth = tWorkHoursMapper.countMonth(new LambdaQueryWrapper<TWorkHours>().eq(TWorkHours::getUserId, userId).ge(TWorkHours::getWorkDate, c.getTime()).ne(TWorkHours::getStatus, "0"));
            Long countAll = tWorkHoursMapper.countAll(new LambdaQueryWrapper<TWorkHours>().eq(TWorkHours::getUserId, userId).ne(TWorkHours::getStatus, "0"));
            Long countSp = tWorkHoursMapper.countSp(new LambdaQueryWrapper<TWorkHours>().eq(TWorkHours::getUserId, userId).eq(TWorkHours::getStatus, "1"));
            return new HrWebResponse().data(list).put("countMonth", countMonth).put("countAll", countAll).put("countSp", countSp);
        } catch (Exception e) {
            message = "获取工时失败";
            log.error(message, e);
            throw new HrwebException(message);
        }
    }

    @Log("填写工时")
    @PutMapping
    public void updateWorkTime(@Valid TWorkHours tWorkHours) throws HrwebException {
        try {
            this.itWorkHoursService.updateByKey(tWorkHours);
        } catch (Exception e) {
            message = "填写用户失败";
            log.error(message, e);
            throw new HrwebException(message);
        }
    }

    @Log("获取已填写工时")
    @GetMapping("findPass")
    public HrWebResponse findPass(@RequestParam Long userId, @RequestParam String status) throws HrwebException {
        try {
            List<TWorkHours> list = itWorkHoursService.findPassById(userId, status);
            return new HrWebResponse().data(list);
        } catch (Exception e) {
            message = "获取已填写工时失败";
            log.error(message, e);
            throw new HrwebException(message);
        }
    }

    @Log("获取需要审批的工时")
    @GetMapping("findApproval/{deptId}")
    public HrWebResponse findApproval(QueryRequest request, TWorkHours tWorkHours, @PathVariable Long deptId) throws HrwebException {
        List<TWorkHours> approval = itWorkHoursService.findApproval(request, tWorkHours, deptId);
        return new HrWebResponse().data(approval);
    }


    @Log("审批工时")
    @PutMapping("approval")
    @RequiresPermissions("worktime:approval")
    public void approval(@NotBlank(message = "{required}") String keyIds,@NotBlank(message = "{required}") String state) throws HrwebException {
        try {
            String[] ids = keyIds.split(StringPool.COMMA);
            itWorkHoursService.approval(ids, state);
        } catch (Exception e) {
            message = "审批工时失败";
            log.error(message, e);
            throw new HrwebException(message);
        }
    }

}
