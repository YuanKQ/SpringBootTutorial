package com.pku.netlab;

import com.pku.netlab.Model.Customer;
import com.pku.netlab.Service.CustomerCacheService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: yuan
 * @Date: 18-7-1 19:12
 * @Description:
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisCacheServiceTest {
    @Resource
    CustomerCacheService cacheService;

    @Test
    public void test1() {
        /*
         * 测试第二次从缓存中查询
         * 此时只会输出即第一次调用
         */
        Customer customer = new Customer(10L, "Kate", "Brown");
        System.out.println("test1:");
        cacheService.getById(9L);
        cacheService.getByCustomer(customer);
        cacheService.getById(9L);
        cacheService.getByCustomer(customer);
    }

    @Test
    public void test2() {
        /*
         * 测试不同方法从同一缓存钟查询(same key)
         * 此时只会输出"getById"
         */
        System.out.println("test2:");
        Customer customer = new Customer(1L, "Jack",  "Smith");
        cacheService.getById(1L);
        cacheService.getByCustomer(customer);
    }

    @Test
    public void test3() throws InterruptedException {
        /*
         * 测试保存后的写入缓存的查询
         */
        System.out.println("test3");
        Customer customer = new Customer(9L, "Kate",  "Williams");
        cacheService.update(customer); // one execute this sentence
        Thread.sleep(500);
        System.out.println(cacheService.getByCustomer(customer));
    }

    @Test
    public void test4() {
        /*
         * 更新缓存car
         * 清空缓存customer中所有对象
         * 所以以下执行方法均会执行
         */
        System.out.println("test4");
        Customer customer = new Customer(12, "Dean",  "Smith");
        cacheService.add(customer);
        cacheService.add(customer);
        cacheService.getById(12L);
        cacheService.getById(12L);
    }


}
