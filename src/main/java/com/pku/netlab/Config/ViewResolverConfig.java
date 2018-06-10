package com.pku.netlab.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.util.Properties;

/**
 * @Auther: yuan
 * @Date: 18-6-10 15:54
 * @Description: 模板引擎配置类，能够使用jsp，ftl，html。
 */

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
