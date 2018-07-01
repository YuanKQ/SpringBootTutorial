package com.pku.netlab.Service;

import com.pku.netlab.Model.Customer;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: yuan
 * @Date: 18-6-27 10:39
 * @Description: Cache annotation
 */

@Service
@CacheConfig(cacheNames = "customer")
public class CustomerCacheService {
    private static Map<Long, Customer> customers = new HashMap<>();

    static {
       customers.put(1L, new Customer(1, "Jack",  "Smith"));
       customers.put(2L, new Customer(2, "Adam",  "Smith"));
       customers.put(3L, new Customer(3, "Kimy",  "Smith"));
       customers.put(4L, new Customer(4, "David", "Ford"));
       customers.put(5L, new Customer(5, "Peter", "Brown"));
       customers.put(6L, new Customer(6, "Tom",   "White"));
       customers.put(7L, new Customer(7, "Kate",  "Williams"));
       customers.put(8L, new Customer(8, "Kate",  "Williams"));
       customers.put(9L, new Customer(9, "Kate",  "Williams"));
       customers.put(10L, new Customer(10, "Kate",  "Williams"));
    }

    @Cacheable(key = "#id")
    public Customer getById(long id) {
        System.out.println("\tgetById");
        return customers.get(id);
    }

    @Cacheable(key = "#customer.id")
    public Customer getByCustomer(Customer customer){
        System.out.println("\tgetByCustomer");
        return customers.get(customer.getId());
    }

    //二者是否会发生key冲突
    @Cacheable(key = "'allCustomer'")
    public List<Customer> getAll() {
        System.out.println("\tgetAll");
        List<Customer> resultList = new ArrayList<>();
        for (Long key: customers.keySet())
            resultList.add(customers.get(key));

        return resultList;
    }


    @Cacheable(key = "'allCustomer'")
    public void findAll() {
        System.out.println("\tfindAll");
    }


    @CachePut(key = "#customer.id")
    public Customer update(Customer customer){
        System.out.println("\tupdate customer");
        Customer customer1 = customers.get(customer.getId());
        customer1.setLastName(customer1.getLastName().toUpperCase());
        customers.put(customer.getId(), customer1);
        return customer1;
    }

    @CachePut(key = "#customer.id")
    @CacheEvict(allEntries = true)
    //@CacheEvict(cacheNames = "'put&delte'", allEntries = true) 使用这句话,则只会对add方法的缓存起作用.然而对于其他方法而言, cache的名称为customer,所以不受影响.
    public Customer add(Customer customer){
        System.out.println("\tsave customer");
        customers.put(customer.getId(), customer);
        return customer;
    }


    @CacheEvict(allEntries = true)
    public void delete(){
        System.out.println("delete all entries.");
    }
}
