package com.pku.netlab;

import com.alibaba.fastjson.JSON;
import com.pku.netlab.Dao.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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


        /*
         * Json object serialize and deserialize
         */
        // Serialize: getter
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(12, "Peter", "Smith", new Date()));
        personList.add(new Person(20, "Kate", "Brown", new Date()));
        String jsonOutput = JSON.toJSONString(personList);
        System.out.println(jsonOutput);

        //Deserialize: setter
        String jsonInput = "{\"DATE OF BRITH\":\"2018-39-18\",\"fullName\":\"Peter\",\"lastName\":\"Smith\", \"age\":18}";
        Person p = JSON.parseObject(jsonInput, Person.class);
        System.out.println(p);
    }
}
