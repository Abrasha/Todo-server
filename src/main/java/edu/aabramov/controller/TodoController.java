package edu.aabramov.controller;

import edu.aabramov.model.Todo;
import edu.aabramov.service.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

/**
 * @author Andrii Abramov on 11/30/16.
 */
@RestController
public class TodoController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(TodoController.class);
    
    private final TodoService todoService;
    
    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }
    
    @PostMapping(value = "/users/{userId}/todos/generate", produces = APPLICATION_JSON_UTF8_VALUE)
    public List<Todo> insertRandomTodos(@RequestParam("count") int count, @PathVariable("userId") String userId) {
        LOGGER.debug("inserting {} todos for user id: {}", count, userId);
        return todoService.insertRandomTodos(count, userId);
    }
    
    @GetMapping(path = "/users/{userId}/todos", produces = APPLICATION_JSON_UTF8_VALUE)
    public List<Todo> getUserTodos(@PathVariable("userId") String userId) {
        LOGGER.debug("todos requested for user with id = {}", userId);
        return todoService.getUserTodos(userId);
    }
    
}
