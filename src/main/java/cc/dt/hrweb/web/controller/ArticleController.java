package cc.dt.hrweb.web.controller;

import cc.dt.hrweb.common.domain.HrWebConstant;
import cc.dt.hrweb.common.domain.HrWebResponse;
import cc.dt.hrweb.common.exception.HrwebException;
import cc.dt.hrweb.common.utils.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
//@RestController
@RequestMapping("article")
public class ArticleController {

    @GetMapping
    @RequiresPermissions("article:view")
    public HrWebResponse queryArticle(String date) throws HrwebException {
        String param;
        String data;
        try {
            if (StringUtils.isNotBlank(date)) {
                param = "dev=1&date=" + date;
                data = HttpUtil.sendSSLPost(HrWebConstant.MRYW_DAY_URL, param);
            } else {
                param = "dev=1";
                data = HttpUtil.sendSSLPost(HrWebConstant.MRYW_TODAY_URL, param);
            }
//            return data;
            return new HrWebResponse().data(data);
        } catch (Exception e) {
            String message = "获取文章失败";
            log.error(message, e);
            throw new HrwebException(message);
        }
    }
}
