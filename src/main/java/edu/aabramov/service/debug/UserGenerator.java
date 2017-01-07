package edu.aabramov.service.debug;

import com.github.javafaker.Faker;
import edu.aabramov.configuration.AppProfiles;
import edu.aabramov.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Andrii Abramov on 11/30/16.
 */
@Component
@Profile(AppProfiles.TEST)
public class UserGenerator {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(UserGenerator.class);
    
    private final Faker faker;
    
    
    @Autowired
    public UserGenerator(Faker faker) {
        this.faker = faker;
    }
    
    public List<User> getRandomUsers(int count) {
        return IntStream.range(0, count)
                .mapToObj(this::getRandomUser)
                .collect(Collectors.toList());
    }
    
    private User getRandomUser(int number) {
        User user = new User();
        user.setPassword(faker.lorem().characters(10));
        user.setUsername(faker.superhero().name());
        user.setTodos(new ArrayList<>(0));
        return user;
    }
    
}
