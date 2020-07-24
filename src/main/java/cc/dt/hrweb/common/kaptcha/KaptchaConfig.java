package cc.dt.hrweb.common.kaptcha;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Properties;
/**
 * @author: dt_啦la啦
 * @version: 1.0.0
 * @date: 2020/4/10
 * @description: 验证码的自定义配置类
 */
@Component
public class KaptchaConfig {

    @Bean
    public DefaultKaptcha getDefaultKaptcha() {
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        // 图片宽
        properties.setProperty("kaptcha.image.width", "250");
        // 图片高
        properties.setProperty("kaptcha.image.height", "100");
        // 图片边框
        properties.setProperty("kaptcha.border", "no");
        // 边框颜色
        properties.setProperty("kaptcha.border.color", "105,179,90");
        // 字体颜色
        properties.setProperty("kaptcha.textproducer.font.color", "17,129,213");
        // 字体大小
        properties.setProperty("kaptcha.textproducer.font.size", "80");
        // session key
        properties.setProperty("kaptcha.session.key", "code");
        // 验证码长度
        properties.setProperty("kaptcha.textproducer.char.length", "4");
        // 字体
        properties.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");
        //背景
        properties.setProperty("kaptcha.background.clear.from", "white");
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}
