package cc.dt.hrweb.web.controller;

import cc.dt.hrweb.common.domain.HrWebConstant;
import cc.dt.hrweb.common.domain.HrWebResponse;
import cc.dt.hrweb.common.exception.HrwebException;
import cc.dt.hrweb.common.utils.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

@Slf4j
@Validated
//@RestController
@RequestMapping("weather")
public class WeatherController {

    @GetMapping
    @RequiresPermissions("weather:view")
    public HrWebResponse queryWeather(@NotBlank(message = "{required}") String areaId) throws HrwebException {
        try {
            String data = HttpUtil.sendPost(HrWebConstant.MEIZU_WEATHER_URL, "cityIds=" + areaId);
            return new HrWebResponse().data(data);
        } catch (Exception e) {
            String message = "天气查询失败";
            log.error(message, e);
            throw new HrwebException(message);
        }
    }
}
