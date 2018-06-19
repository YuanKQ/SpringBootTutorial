package com.pku.netlab;

import com.alibaba.fastjson.JSON;
import com.pku.netlab.Model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@ComponentScan({"com.pku.netlab.Controller", "com.pku.netlab.Config", "com.pku.netlab.Repo"})
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
        String jsonInput = "{\"DATE OF BRITH\":\"2018-39-18\",\"firstName\":\"Peter\",\"lastName\":\"Smith\", \"age\":18}";
        System.out.println(JSON.parseObject(jsonInput, Person.class));
    }
}
