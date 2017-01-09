package edu.aabramov.todo.service.config;

import com.github.javafaker.Faker;
import edu.aabramov.todo.core.util.AppProfiles;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Locale;

/**
 * @author Andrii Abramov on 11/30/16.
 */
@Configuration
public class ServiceConfiguration {
    
    @Bean
    @Profile(AppProfiles.TEST)
    public Faker faker() {
        return new Faker(Locale.ENGLISH);
    }
    
}
