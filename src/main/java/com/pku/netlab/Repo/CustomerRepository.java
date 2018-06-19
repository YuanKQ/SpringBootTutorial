package com.pku.netlab.Repo;

import com.pku.netlab.Model.Customer;

import java.util.Map;
import java.util.spi.CurrencyNameProvider;

/**
 * @Auther: yuan
 * @Date: 18-6-19 22:53
 * @Description:
 */
public interface CustomerRepository {
    void save(Customer customer);
    Customer find(Long id);
    Map<Long, Customer> findAll();
    void update(Customer customer);
    long delete(Long id);
}
