package com.pku.netlab.springbootrookie.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: yuan
 * @Date: 18-6-10 12:34
 * @Description:
 */
@RestController
public class HelloWorldController {
    @RequestMapping("/")
    public String hello(){
        return "Hello SpringBoot!";
    }
}
