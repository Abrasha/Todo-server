package edu.aabramov.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author Andrii Abramov on 11/25/16.
 */
@Configuration
public class RedisConfiguration {
    
    private final Environment env;
    
    @Autowired
    public RedisConfiguration(Environment env) {
        this.env = env;
    }
    
    @Bean
    @Primary
    public JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setUsePool(true);
        jedisConnectionFactory.setHostName(env.getProperty("spring.redis.host"));
        jedisConnectionFactory.setPort(Integer.valueOf(env.getProperty("spring.redis.port")));
        jedisConnectionFactory.setPassword(env.getProperty("spring.redis.password"));
        jedisConnectionFactory.setUsePool(true);
        return jedisConnectionFactory;
    }
    
    @Bean
    public RedisTemplate<?, ?> redisTemplate(JedisConnectionFactory factory) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        return redisTemplate;
    }
    
}
