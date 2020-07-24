package cc.dt.hrweb.system.controller;

import cc.dt.hrweb.common.exception.HrwebException;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: dt_啦la啦
 * @version: 1.0.0
 * @date: 2020/4/10
 * @description: 验证码生成和验证
 */
@Slf4j
@RestController
@RequestMapping("/imageCode")
public class ImageCodeController {
    @Autowired
    DefaultKaptcha defaultKaptcha;

    /**
     * 生成验证码
     * @param request
     * @param response
     * @throws Exception
     */
    @GetMapping
    public void createImageCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            // 生产验证码字符串并保存到session中
            String createText = defaultKaptcha.createText();
            request.getSession().setAttribute("imageCode", createText);
            // 使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
            log.info("验证码验证：---------createImageCode:{}",request.getSession().getAttribute("imageCode"));
        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        // 定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream = response.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();
    }

    /**
     * 验证验证码输入
     * @param request
     * @param response
     * @param inputImageCode
     * @return
     */
    @PostMapping("/checkImageCode")
    public Object checkImageCode(HttpServletRequest request,
                                 HttpServletResponse response, HttpSession session, String inputImageCode) throws Exception {
        Map<String, Object> result = new HashMap<>();
        String imageCode = (String) session.getAttribute("imageCode");
        final String errorMessage = "验证码错误";
        //String inputImageCode = request.getParameter("inputImageCode");
        log.info("验证码验证：---------imageCode:{},inputImageCode:{}",session.getAttribute("imageCode"),inputImageCode);
        if (StringUtils.isEmpty(inputImageCode)) {
            throw new HrwebException(errorMessage);
        } else {
            if (!imageCode.equals(inputImageCode)) {
//            result.put("code", "201");
//            result.put("msg", "error");
//            result.put("result","验证码错误");
                throw new HrwebException(errorMessage);
            } else {
                result.put("code", "200");
                result.put("msg", "success");
            }
        }
        return result;
    }

}
