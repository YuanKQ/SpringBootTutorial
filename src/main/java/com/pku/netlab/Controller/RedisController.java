package com.pku.netlab.Controller;

import com.pku.netlab.Model.Customer;
import com.pku.netlab.Repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Auther: yuan
 * @Date: 18-6-18 23:41
 * @Description:
 */

@RestController
@RequestMapping("/redis")
public class RedisController {
    @Autowired
    RedisTemplate<String, Object> template;
    @Autowired
    CustomerRepository customerRepo;

    @GetMapping("/test")
    public String testRedis(){
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
    
    @GetMapping(value = "/findall", produces = "application/json")
    public Map<Long, Customer> findAll() {
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
