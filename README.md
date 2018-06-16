# SpringBoot 学习计划
## Spring Core
### 理解Bean的作用域，生命周期，体会Bean的注入
> 详见《Spring实战》第三版：第1章~第3章
### 常见注解
@Configure，@Bean

@Controller，@RestController

@RequestMapping
## Spring Web
### 配置模板引擎
> ViewResolverConfig
- FreeMarker配置参见：
  ftl完全兼容html的语法
 
  Freemarker官方教程(https://freemarker.apache.org/docs)
  
  application.properties默认设置(https://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html)
- jsp配置参考：
  SpringBoot已经不推荐使用jsp了
  倘若实在想作：http://tengj.top/2017/03/13/springboot5/
- html配置：
  使用模板引擎thymeleaf才可以通过Controller直接返回.html

  使用了freemarker后，html将为ftl所使用: "Static files should be served from resources, not from controller."
  
  但是，不管是否配置了模板，均存在一例外。浏览器输入localhost:8080/, 浏览器将返回内容classpath:/static/index.html。 参见https://spring.io/guides/gs/serving-web-content/#_add_a_home_page
### 配置路由（URL映射） 
> UrlMappingController.java
- @RequestMapping配置Tutorial（全）：
   http://www.baeldung.com/spring-requestmapping
- @RequestMapping new shortcut annotation:
   http://www.baeldung.com/spring-new-requestmapping-shortcuts
   
   两者完全等价：@RequestMapping(method = { RequestMethod.GET })与 @GetMapping，  @RequestMapping(method = { RequestMethod.POST}) 与 @PostMapping
- @RequestMapping可配置项的解释：
    http://jinnianshilongnian.iteye.com/blog/1684403（开头导读部分）
- 生产者与消费者概念(即RequestMapping的参数"consumes", "produces")：
    http://jinnianshilongnian.iteye.com/blog/1695047
    
    http 协议中的Header部分的规定 --》 生产者、消费者模式 --》(Server) consumes、produces

    consumes,produces参数时，方法级别的映射将覆盖类级别的映射，这点要与value参数的使用区别开来。
 
