/**
 * @Author: yuan
 * @Date: 18-6-10 12:34
 * @Description:
 */

package com.pku.netlab.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    @RequestMapping("/hello")
    public String hello(){
        return "Hello SpringBoot!";
    }

}
