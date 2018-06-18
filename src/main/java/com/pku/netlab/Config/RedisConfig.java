package com.pku.netlab.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.Jedis;

import javax.validation.Valid;

/**
 * @Auther: yuan
 * @Date: 18-6-18 22:48
 * @Description: Redis Configuration
 *  一旦使用RedisConfig对Redis进行配置，那么application.properties中的配置就会失效。
 *  但是，依然可以通过@Value来读取application.properties中的值。
 */

@Configuration
public class RedisConfig {

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        /*
         * 修改redis密码： /etc/redis.conf
         * 启动redis命令为： redis-server /etc/redis.conf --port 7001
         */
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration("localhost", 7001);
        config.setPassword(RedisPassword.of("p@ssword"));
        return new JedisConnectionFactory(config);
    }


    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }
}
