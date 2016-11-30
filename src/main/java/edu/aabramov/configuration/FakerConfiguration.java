package edu.aabramov.configuration;

import com.github.javafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;

/**
 * @author Andrii Abramov on 11/30/16.
 */
@Configuration
public class FakerConfiguration {
    
    @Bean
    public Faker faker() {
        return new Faker(Locale.ENGLISH);
    }
    
}
