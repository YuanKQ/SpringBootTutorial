package com.pku.netlab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

import java.net.URL;
import java.net.URLClassLoader;

@SpringBootApplication
@ComponentScan({"com.pku.netlab.Controller", "com.pku.netlab.Config"})
public class SpringbootRookieApplication{


    public static void main(String[] args) {
        SpringApplication.run(SpringbootRookieApplication.class, args);

        /*
        // 获取classifierpath：$ProjectPath/target/classes
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        URL[] urls = ((URLClassLoader)cl).getURLs();

        for(URL url: urls){
            System.out.println(url.getFile());
        }*/

    }
}
