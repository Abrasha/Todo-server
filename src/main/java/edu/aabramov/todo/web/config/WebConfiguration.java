package edu.aabramov.todo.web.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Andrii Abramov on 1/9/17.
 */
@Configuration
public class WebConfiguration {
    
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    
}
