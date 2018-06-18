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
    
### fastJson配置
> 使用 Fastjson 提供的`FastJsonHttpMessageConverter` 来替换 Spring 默认的 `HttpMessageConverter`以提高`@RestController`, `@ResponseBody`, `@RequestBody` 注解的 JSON序列化速度

官方doc： http://static.javadoc.io/com.alibaba/fastjson/1.2.33/index.html
@JsonField解析: https://github.com/alibaba/fastjson/wiki/JSONField
sample: http://www.baeldung.com/fastjson
配置: https://www.jianshu.com/p/aabd308ac963


序列化 JSON.toJSONString()：Object --> String
- private/protected字段没有加getter方法的话，该字段不会被序列化（该字段不会输出到String中）

反序列化 JSON.parseObject()：String --> Object
- private/protected字段没有setter方法的话，该字段不会被反序列化(该字段的值保留为系统默认值）
- define a no-args or default constructor

验证：
- 本地 `JSON.toJSONString()`与`JSON.parseObject()`
- client与server端：JSON序列化与反序列化

## Spring Data
### Redis
### install redis
 倘若是Manjaro，直接在Terminal命令输入`yaourt -S redis`
 其他OS参见官网：http://www.baeldung.com/spring-data-redis-tutorial
### Introduction to Spring Data Redis 
> first run Reids by the command `redis-server /etc/redis.conf --port 7001`
- using annotation
  http://www.baeldung.com/spring-data-redis-tutorial
  Redis2.0之后，一些配置需要更新： https://stackoverflow.com/questions/49021994/jedisconnectionfactory-sethostname-is-deprecated
- using autoconfig(application.properties)
  https://github.com/x113773/testall/issues/17