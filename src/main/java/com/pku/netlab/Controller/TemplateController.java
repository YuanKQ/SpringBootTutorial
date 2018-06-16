/**
 * @Author: yuan
 * @Date: 18-6-10 21:01
 * @Description: 根据utl返回对应的freemarker模板
 */

package com.pku.netlab.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TemplateController {

    @RequestMapping("/ftl")
    public String retrunFtl(){
        return "hello";
    }

    @RequestMapping("/html")
    public String returnJsp(){
        // 返回不了notFound.html, 而是notFound.ftl
        return "notFound";
    }

}
