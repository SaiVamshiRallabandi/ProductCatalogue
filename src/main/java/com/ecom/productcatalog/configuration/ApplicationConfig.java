package com.ecom.productcatalog.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class ApplicationConfig {

    @Bean
    public RedisTemplate<String,Object> getRedisTemplete(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String,Object> redisTemplate=new RedisTemplate<String,Object>();
        redisTemplate.setConnectionFactory( redisConnectionFactory);
        return redisTemplate;
    }
}
