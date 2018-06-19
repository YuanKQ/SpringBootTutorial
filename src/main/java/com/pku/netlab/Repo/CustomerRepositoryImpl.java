package com.pku.netlab.Repo;

import com.pku.netlab.Model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * @Auther: yuan
 * @Date: 18-6-19 22:53
 * @Description: 在Redis构建了一个CUSTOMER的数据库，每一行表示customer，每一行的主键为customer的id
 */

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {
    private static final String KEY = "CUSTOMER";
    private HashOperations<String, Long, Customer> hashOps;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @PostConstruct
    private void init() {
        hashOps = redisTemplate.opsForHash();
    }

    @Override
    public void save(Customer customer) {
        hashOps.put(KEY, customer.getId(), customer);
    }

    @Override
    public Customer find(Long id) {
        return hashOps.get(KEY, id);
    }

    @Override
    public Map<Long, Customer> findAll() {
        return hashOps.entries(KEY);
    }

    @Override
    public void update(Customer customer) {
        hashOps.put(KEY, customer.getId(), customer);
    }

    @Override
    public long delete(Long id) {
        return hashOps.delete(KEY, id);
    }
}
