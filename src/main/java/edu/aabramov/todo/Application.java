package edu.aabramov.todo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

/**
 * @author Andrii Abramov on 11/24/16.
 */
@SpringBootApplication
@EnableMongoRepositories
@EnableRedisRepositories
public class Application {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);
    
    public static void main(String[] args) {
        LOGGER.debug("Application init");
        SpringApplication.run(Application.class, args);
    }
    
}