package cc.dt.hrweb.busi.controller;


import cc.dt.hrweb.busi.entity.TUserinfo;
import cc.dt.hrweb.common.controller.BaseController;
import cc.dt.hrweb.common.domain.HrWebResponse;
import cc.dt.hrweb.common.exception.HrwebException;
import cc.dt.hrweb.busi.service.ITUserinfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author dtlalala
 */
@Slf4j
@Validated
@RestController
@RequestMapping("tuserinfo")
public class TUserinfoController extends BaseController {
    private String message;

    @Autowired
    private ITUserinfoService itUserinfoService;

    @GetMapping("/{userId}")
    public HrWebResponse findDetailByid(@PathVariable Long userId){
        TUserinfo personnel = itUserinfoService.findPersonnelById(userId);
        return new HrWebResponse().data(personnel);

    }

    @GetMapping
    public String test(){
        return "test!";
    }

    @PutMapping("personnel")
    public void updatePersonnel(@Valid TUserinfo tUserinfo) throws HrwebException {
        try {
            this.itUserinfoService.updatePersonnel(tUserinfo);
        } catch (Exception e) {
            message = "修改员工信息失败";
            log.error(message, e);
            throw new HrwebException(message);
        }
    }
}
