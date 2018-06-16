/**
 * @Auther: yuan
 * @Date: 18-6-10 15:54
 * @Description: 模板引擎配置类，使用ftl，html
 * FreeMarker配置参见：
 *   ftl完全兼容html的语法
 *   Freemarker官方教程(https://freemarker.apache.org/docs)
 *   application.properties默认设置(https://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html)
 *
 *
 * jsp配置参考：
 *   SpringBoot已经不推荐使用jsp了
 *   倘若实在想作：http://tengj.top/2017/03/13/springboot5/
 *
 *
 * html配置：
 *   使用模板引擎thymeleaf才可以通过Controller直接返回.html
 *   使用了freemarker后，html将为ftl所使用: "Static files should be served from resources, not from controller."
 *
 *   但是，不管是否配置了模板，均存在一例外。浏览器输入localhost:8080/, 浏览器将返回内容classpath:/static/index.html。 参见https://spring.io/guides/gs/serving-web-content/#_add_a_home_page
 */

package com.pku.netlab.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.util.Properties;

@Configuration
public class ViewResolverConfig {
    @Bean
    FreeMarkerViewResolver freeMarkerViewResolver(){
        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
        resolver.setPrefix("");
        resolver.setSuffix(".ftl");
        resolver.setContentType("text/html;charset=UTF-8");
        // ?
        resolver.setCache(true);
        resolver.setRequestContextAttribute("request");
        resolver.setExposeSpringMacroHelpers(true);
        resolver.setExposeRequestAttributes(true);
        resolver.setExposeSessionAttributes(true);

        return resolver;
    }

//    @Bean
//    InternalResourceViewResolver servletResolver() {
//        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//        resolver.setPrefix("/resources/templates/JSP/");
//        resolver.setSuffix(".jsp");
//        return resolver;
//    }

    @Bean
    FreeMarkerConfigurer freemakerConfig() {
        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();

        /*以下写法都是错的：
        **configurer.setTemplateLoaderPath("/templates/FTL/");
        **configurer.setTemplateLoaderPath("resources/templates/FTL/");
        **configurer.setTemplateLoaderPath("/resources/templates/FTL/");
         */
        configurer.setTemplateLoaderPath("classpath:/templates/FTL/");
        Properties freemarkerSettings = new Properties();
        freemarkerSettings.setProperty("template_update_delay", "5");
        freemarkerSettings.setProperty("default_encoding", "UTF-8");
        freemarkerSettings.setProperty("locale", "UTF-8");
        freemarkerSettings.setProperty("datetime_format", "yyyy-MM-dd HH:mm:ss");
        freemarkerSettings.setProperty("time_format", "HH:mm:ss");
        freemarkerSettings.setProperty("number_format", "0.####");
        freemarkerSettings.setProperty("boolean_format", "true,false");
        freemarkerSettings.setProperty("whitespace_stripping", "true");
        freemarkerSettings.setProperty("tag_syntax", "auto_detect");
        freemarkerSettings.setProperty("url_escaping_charset", "UTF-8");
        freemarkerSettings.setProperty("classic_compatible", "true");
        configurer.setFreemarkerSettings(freemarkerSettings);

        return configurer;
    }

}
