/**
 * @Author: yuan
 * @Date: 18-6-16 10:25
 * @Description: url路由配置
 * - @RequestMapping配置Tutorial（全）：
 *    http://www.baeldung.com/spring-requestmapping
 * - @RequestMapping new shortcut annotation:
 *    http://www.baeldung.com/spring-new-requestmapping-shortcuts
 *    两者完全等价：@RequestMapping(method = { RequestMethod.GET })与 @GetMapping，  @RequestMapping(method = { RequestMethod.POST}) 与 @PostMapping
 * - @RequestMapping可配置项的解释：
 *    http://jinnianshilongnian.iteye.com/blog/1684403（开头导读部分）
 * - 生产者与消费者概念(即RequestMapping的参数"consumes", "produces")：
 *    http://jinnianshilongnian.iteye.com/blog/1695047
 *    http 协议中的Header部分的规定 --》 生产者、消费者模式 --》(Server) consumes、produces
 *    consumes,produces参数时，方法级别的映射将覆盖类级别的映射，这点要与value参数的使用区别开来。
 */

package com.pku.netlab.Controller;

import com.alibaba.fastjson.JSON;
import com.pku.netlab.Model.Person;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

@RestController
@RequestMapping("/mapping")
public class UrlMappingController {
    /*
     * new shortcut annotation for @RequestMapping
     *
     * test out the mapping by curl commond:
     * curl -i -X ${METHOND} http://localhost:8080/mapping/${url}
     * -i means include header info.
     */
    @GetMapping("/get")
    public String getBySimplePath() {
        return "Get delicious zongzi.";
    }

    @PostMapping("/post")
    public String postBySimplePath() {
        return "Post delicious zongzi.";
    }


    @PutMapping("/put")
    public String putBySimplePath() {
        return "Put delicious zongzi.";
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteBySimplePath() {
        return new ResponseEntity<>("Delete Response", HttpStatus.OK);
    }

    @PatchMapping("/patch")
    public ResponseEntity<String> patchBySimplePath() {
        return new ResponseEntity<>("Patch Response", HttpStatus.OK);
    }


    /*
     * set header attribute
     * test out: curl -i -H "usr:ykq" -H "food:zongzi" http://localhost:8080/mapping/getHeader
     */
    @GetMapping(value = "/getHeader", headers = {"usr=ykq", "food=zongzi"})
    public String getWithHeader() {
        return "Get header.";
    }


    /*
     * consumes: 指定请求/响应的内容区数据的媒体类型，即client端内容区数据的媒体类型， server端（消费者）根据Content-Type消费内容体数据。
     * set header "Content-type"，包括数据类型和编码格式， 例如”application/json;charset=GBK".
     * test out: 用postman吧
     */
    //multiple type： @PostMapping(value = "/consumes", consumes = {"application/json", "application/xml"})
    @PostMapping(value = "/consumes", consumes = "application/json")
    public Person getJsonObject(HttpServletRequest request, HttpServletResponse response){
//        return "getJsonObject";
//        int contenLength = request.getContentLength();
//        if (contenLength <= 0)
//            return "Get null object.";
//        byte buffer[] = new byte[contenLength];
//        for (int i = 0; i < contenLength;) {
//            int readLen = 0;
//            try {
//                readLen = request.getInputStream().read(buffer, i, contenLength-i);
//            } catch (IOException e) {
//                return "IOException";
//            }
//            if (readLen == -1)
//                break;
//        }
//        String charEncoding = request.getCharacterEncoding();
//        if (charEncoding == null)
//            charEncoding = "UTF-8";
//
//        String object;
//        try {
//            object = new String(buffer, charEncoding);
//        } catch (UnsupportedEncodingException e) {
//            return "UnsupportedEncodingException";
//        }
//        return "Get: " + object;
        // 从request对象中获取请求json格式的参数: https://blog.csdn.net/qq_27292113/article/details/76837603
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
            StringBuilder sb = new StringBuilder();
            String input;
            while ((input = reader.readLine())!= null)
                sb.append(input);
            Person p = JSON.parseObject(sb.toString(), Person.class);
            p.getOlder();
            response.setContentType("application/json");
            return p;
        } catch (IOException e) {

            e.printStackTrace();
        }

        return null;
    }

    /*
     * produces: 指定什么媒体类型的响应是可接受的，即server端（生产者）根据client端的"Accept"字段产生指定媒体类型。
     * set header "Accept"
     * test out： 用Postman吧
     */
    @GetMapping(value = "/produces", produces = "application/json")
    public Person sendJsonObject(HttpServletResponse response) {
        Person p = new Person(12, "Peter", "Smith", new Date());

        // 告知客户端以正确的格式解码
        response.setContentType("application/json;charset=utf-8");
        return p;
    }


    /*
     * Path Variable
     * test out: curl http://localhost:8080/mapping/SinglePathVariable/11
     *           curl http://localhost:8080/mapping/MultiplePathVariable/11/split/kk
     */
    @GetMapping(value = "/singlePathVariable/{id}")
    public String getSinglePathVariable(@PathVariable String id){
        return "Get a path param id=" + id;
    }

    @GetMapping(value = "/multiplePathVariable/{id}/split/{name}")
    public String getMultiplePathVariable(@PathVariable String id, @PathVariable String name){
        return "Get multiple path params: id= " + id + ", name= " + name;
    }

    /*
     * Path Variable with regEx
     * test out: curl http://localhost:8080/mapping/regexPathVariable/X2016
     */
    @GetMapping(value = "/regexPathVariable/{id:\\A[a-zA-Z][\\d]+}")
    public String getRegExPathVariable(@PathVariable String id){
        return "Get a path param id=" + id;
    }


    /*
     * Request Parameters: 最长优先匹配原则
     * test out:
     * [getSingleParameter]   curl http://localhost:8080/mapping/parameters?id=1234&timezone=beijing&other=0.12345
     * [getSingleParameter]   curl http://localhost:8080/mapping/parameters?id=1234
     * [getMultipleParameter] curl http://localhost:8080/mapping/parameters?id=1234&timezone=beijing&name=kk&other=0.12345
     * [getMultipleParameter] curl http://localhost:8080/mapping/parameters?id=1234&name=kk
     */
    @GetMapping(value= "/parameters", params = "id")
    public String getSingleParameter(@RequestParam String id){
        return "Get a parameter id=" + id;
    }

    @GetMapping(value = "/parameters", params = {"id", "name"})
    public String getMultipleParameter(@RequestParam long id, @RequestParam String name){
        return "Get multiple parameters id=" + id + " name=" + name;
    }


    /*
     * multiple paths mapping to the same Controller
     */
    /*
     * test out:  curl http://localhost:8080/mapping/singlePathVariable/11/end
     *            curl http://localhost:8080/mapping/end
     */
    @GetMapping(value = "/**/end")   // @GetMapping(value = "/*/end")就不能匹配 http://localhost:8080/mapping/end
    public String regexEnd(){
        return "End.";
    }

    /*
     * test out: curl http://localhost:8080/mapping/first/end
     *           curl http://localhost:8080/mapping/second/end
     *           curl http://localhost:8080/mapping/1/2/end
     */
    @GetMapping(value = {"/first/end", "/second/end", "/1/2/end"})
    public String multipleEnd(){
         return "Multiple end.";
    }


    /*
     * Multiple methods mapping to the same controller
     * test out: 用Postman
     */
    @RequestMapping(value = "/multipleMethods", method = {RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.PATCH})
    public String multipleMethods(){
        return "MultipleMethods Mapping";
    }


    /*
     * a fallback for all requests
     * test out:  curl http://localhost:8080/mapping
     *            curl http://localhost:8080/mapping/kk/ll/balabala
     */
//    @RequestMapping(value = "**", method = {RequestMethod.GET, RequestMethod.POST}) // value="*" 就不能匹配 http://localhost:8080/mapping
//    public String fallback() {
//        return "Fall back for UrlMappingController.";
//    }
}

