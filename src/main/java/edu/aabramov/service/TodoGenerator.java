package edu.aabramov.service;

import com.github.javafaker.Faker;
import edu.aabramov.model.Priority;
import edu.aabramov.model.Status;
import edu.aabramov.model.Todo;
import edu.aabramov.util.DateGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Andrii Abramov on 11/30/16.
 */
@Component
public class TodoGenerator {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(TodoGenerator.class);
    
    private final Faker faker;
    private final DateGenerator dateGenerator;
    private final IdentifierManager identifierManager;
    
    @Autowired
    public TodoGenerator(Faker faker, DateGenerator dateGenerator, IdentifierManager identifierManager) {
        LOGGER.debug("TodoGenerator init");
        this.identifierManager = identifierManager;
        this.faker = faker;
        this.dateGenerator = dateGenerator;
    }
    
    public Todo getRandomTodo() {
        LOGGER.debug("getRandomTodo");
        return generateRandomTodo();
    }
    
    public List<Todo> getRandomTodos(int count) {
        LOGGER.debug("generating {} todos", count);
        
        List<Todo> result = new ArrayList<>(count);
        
        for (int i = 0; i < count; i++) {
            result.add(generateRandomTodo());
        }
        
        return result;
    }
    
    private Todo generateRandomTodo() {
        LOGGER.debug("generateRandomTodo");
        
        Todo result = new Todo();
        
        result.setTitle(faker.lorem().sentence());
        result.setBody(faker.lorem().paragraph(2));
        result.setTags(faker.lorem().words(3));
        result.setPriority(Priority.getRandom());
        result.setStatus(Status.getRandom());
        result.setId(identifierManager.hexObjectId());
        
        Date date = dateGenerator.getRandomDate();
        result.setWhen(date);
        
        return result;
    }
    
    
}
