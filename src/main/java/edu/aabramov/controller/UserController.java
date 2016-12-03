package edu.aabramov.controller;

import edu.aabramov.model.Todo;
import edu.aabramov.model.User;
import edu.aabramov.model.UserDetails;
import edu.aabramov.service.TodoService;
import edu.aabramov.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

/**
 * @author Andrii Abramov on 11/24/16.
 */
@RestController
public class UserController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    
    private final UserService userService;
    private final TodoService todoService;
    
    @Autowired
    public UserController(UserService userService, TodoService todoService) {
        LOGGER.debug("UserController init");
        this.userService = userService;
        this.todoService = todoService;
    }
    
    @GetMapping(path = "/users", produces = APPLICATION_JSON_UTF8_VALUE)
    public List<UserDetails> getAllUsers() {
        LOGGER.debug("all users requested");
        return userService.getAllUsers();
    }
    
    @GetMapping(path = "/users/{userId}", produces = APPLICATION_JSON_UTF8_VALUE)
    public User getUser(@PathVariable("userId") String userId) {
        LOGGER.debug("user {} requested", userId);
        return userService.findOne(userId);
    }
    
    @PostMapping(path = "/users", consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
    public User addUser(@RequestBody User user) {
        LOGGER.debug("adding user = {}", user);
        return userService.insert(user);
    }
    
    @PostMapping(path = "/users/{userId}/todos", consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
    public User addUserTodo(@PathVariable("userId") String userId, @RequestBody Todo todo) {
        LOGGER.debug("adding todo = {} to userId = {}", todo, userId);
        return todoService.addUserTodo(userId, todo);
    }
    
}
