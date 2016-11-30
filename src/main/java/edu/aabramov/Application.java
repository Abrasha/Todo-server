package edu.aabramov;

import edu.aabramov.model.Priority;
import edu.aabramov.model.Todo;
import edu.aabramov.model.User;
import edu.aabramov.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;

/**
 * @author Andrii Abramov on 11/24/16.
 */
@SpringBootApplication
@EnableMongoRepositories
@EnableRedisRepositories
public class Application  {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);
    
    public static void main(String[] args) {
        LOGGER.debug("Application init");
        SpringApplication.run(Application.class, args);
    }
    
}
