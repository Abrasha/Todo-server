package edu.aabramov.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author Andrii Abramov on 11/25/16.
 */
@Configuration
public class RedisConfiguration {
    
    @Bean
    public RedisTemplate<?, ?> redisTemplate(JedisConnectionFactory factory) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        return redisTemplate;
    }
    
}
