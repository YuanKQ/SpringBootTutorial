package com.pku.netlab.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Auther: yuan
 * @Date: 18-6-10 21:01
 * @Description:
 */
@Controller
public class TemplateController {
    @RequestMapping("/ftl")
    public String retrunFtl(){
        return "hello";
    }

    @RequestMapping("/html")
    public String returnJsp(){
        return "notFound";
    }

}
