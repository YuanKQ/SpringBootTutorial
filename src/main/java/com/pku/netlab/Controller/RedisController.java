package com.pku.netlab.Controller;

import com.pku.netlab.Model.Customer;
import com.pku.netlab.Repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Auther: yuan
 * @Date: 18-6-18 23:41
 * @Description: Redis功能模块
 *  1. 使用RedisTemplate实现CURD操作
 *  2. 测试Redis Cache的配置是否正确
 */

@RestController
@RequestMapping("/redis")
public class RedisController {
    @Autowired
    RedisTemplate<String, Object> template;
    @Autowired
    CustomerRepository customerRepo;

    // 测试Redis Cache的配置是否正确.
    // 除第一次该方法会被调用以外,之后同样的request到来都不会再调用这个方法了,即Console窗口只会打印一次"Enter testRedis method."
    @Cacheable("test")
    @GetMapping("/test")
    public String testRedis(){
        System.out.println("Enter testRedis method.");
        template.opsForValue().set("Tom", "Ford");
        String res = (String)template.opsForValue().get("Tom");
        return res;
    }

    @GetMapping("/save")
    public String save() {
        customerRepo.save(new Customer(1, "Jack", "Smith"));
        customerRepo.save(new Customer(2, "Adam", "Johnsom"));
        customerRepo.save(new Customer(3, "Kimy", "Brown"));
        customerRepo.save(new Customer(4, "David", "Williams"));
        customerRepo.save(new Customer(5, "Peter", "Davis"));
        customerRepo.save(new Customer(6, "Tom", "Ford"));

        return "Done";
    }

    // 每次调用该方法,Console窗口即打印"Enter findAll method."
    @GetMapping(value = "/findall", produces = "application/json")
    public Map<Long, Customer> findAll() {
        System.out.println("Enter findAll method.");
        Map<Long, Customer> customers = customerRepo.findAll();
        return customers;
    }

    @GetMapping(value = "/find/{id}", produces = "application/json")
    public Customer find(@PathVariable Long id) {
        return customerRepo.find(id);
    }

    @GetMapping(value = "/update", produces = "application/json")
    public Customer update(@RequestParam("id") Long id){
        Customer customer = customerRepo.find(id);
        customer.setFirstName(customer.getFirstName().toUpperCase());
        customer.setLastName(customer.getLastName().toUpperCase());
        customerRepo.update(customer);

        return customerRepo.find(id);
    }

    @GetMapping(value = "/delete", produces = "application/json")
    public Long delete(@RequestParam("id") Long id) {
        return customerRepo.delete(id);
    }
}
