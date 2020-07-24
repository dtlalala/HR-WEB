package cc.dt.hrweb.system.controller;

import cc.dt.hrweb.common.domain.HrWebResponse;
import cc.dt.hrweb.common.domain.RedisInfo;
import cc.dt.hrweb.common.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("redis")
public class RedisController {

    @Autowired
    private RedisService redisService;

    @GetMapping("info")
    public HrWebResponse getRedisInfo() throws Exception {
        List<RedisInfo> infoList = this.redisService.getRedisInfo();
        return new HrWebResponse().data(infoList);
    }

    @GetMapping("keysSize")
    public Map<String, Object> getKeysSize() throws Exception {
        return redisService.getKeysSize();
    }

    @GetMapping("memoryInfo")
    public Map<String, Object> getMemoryInfo() throws Exception {
        return redisService.getMemoryInfo();
    }
}
