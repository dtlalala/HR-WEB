package cc.dt.hrweb.job.task;

import cc.dt.hrweb.busi.entity.TUserinfo;
import cc.dt.hrweb.busi.entity.TWorkHours;
import cc.dt.hrweb.busi.service.ITUserinfoService;
import cc.dt.hrweb.busi.service.ITWorkHoursService;
import cc.dt.hrweb.system.domain.Dept;
import cc.dt.hrweb.system.domain.User;
import cc.dt.hrweb.system.service.DeptService;
import cc.dt.hrweb.system.service.UserService;
import com.iceyyy.workday.WorkUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class TestTask {

    public void test(String params) {
        log.info("我是带参数的test方法，正在被执行，参数为：{}" , params);
    }
    public void test1() {
        log.info("我是不带参数的test1方法，正在被执行");
    }

    @Autowired
    private UserService userService;

    @Autowired
    private ITWorkHoursService itWorkHoursService;

    @Autowired
    private DeptService deptService;


    @Autowired
    private ITUserinfoService itUserinfoService;

    /**
     * 定时任务 每日生成新的工时
     */
    public void test2() {
        List<User> userList = userService.findAll();
        List<TWorkHours> list = new ArrayList<>();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        if (WorkUtils.isWorkendDay(formatter.format(date))) {
            for (int i = 0; i < userList.size(); i++) {
                TWorkHours t = new TWorkHours();
                t.setUserId(userList.get(i).getUserId());
                t.setDeptId(userList.get(i).getDeptId());
                List<Dept> dept = deptService.findDeptName(userList.get(i).getDeptId());
                t.setDeptName(dept.get(0).getDeptName());
                TUserinfo personnelById = itUserinfoService.findPersonnelById(userList.get(i).getUserId());
                t.setUserName(personnelById.getPersonnelName());
                t.setStatus("0");
                t.setCategory("0");
                t.setOvertime("false");
                t.setWorkDate(new Date());
                list.add(t);
            }
            itWorkHoursService.saveBatch(list);
            log.info("今天是{},工作日：{}，员工工时方法", formatter.format(date), !WorkUtils.isWorkendDay(formatter.format(date)));
        }
    }
}
