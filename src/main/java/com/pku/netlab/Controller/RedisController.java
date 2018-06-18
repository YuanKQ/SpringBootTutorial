package com.pku.netlab.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: yuan
 * @Date: 18-6-18 23:41
 * @Description:
 */

@RestController
@RequestMapping("/redis")
public class RedisController {
    @Autowired
    RedisTemplate<String, Object> template;

    @GetMapping("/test")
    public String testRedis(){
        template.opsForValue().set("Tom", "Ford");
        String res = (String)template.opsForValue().get("Tom");
        return res;
    }
}
