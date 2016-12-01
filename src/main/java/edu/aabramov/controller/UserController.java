package edu.aabramov.controller;

import edu.aabramov.model.Todo;
import edu.aabramov.model.User;
import edu.aabramov.model.UserDetails;
import edu.aabramov.repository.UserRepository;
import edu.aabramov.service.TodoService;
import edu.aabramov.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

/**
 * @author Andrii Abramov on 11/24/16.
 */
@RestController
public class UserController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    
    private final UserRepository userRepository;
    private final UserService userService;
    private final TodoService todoService;
    
    @Autowired
    public UserController(UserRepository userRepository, UserService userService, TodoService todoService) {
        LOGGER.debug("UserController init");
        this.userRepository = userRepository;
        this.userService = userService;
        this.todoService = todoService;
    }
    
    @GetMapping(path = "/users", produces = APPLICATION_JSON_UTF8_VALUE)
    public List<UserDetails> getAllUsers() {
        LOGGER.debug("all users requested");
        return userRepository.findAll()
                .stream()
                .map(UserDetails::new)
                .collect(Collectors.toList());
    }
    
    @GetMapping(path = "/users/{userId}", produces = APPLICATION_JSON_UTF8_VALUE)
    public User getUser(@PathVariable("userId") String userId) {
        LOGGER.debug("user {} requested", userId);
        return userService.getUser(userId);
    }
    
    @GetMapping(path = "/usernames", produces = APPLICATION_JSON_UTF8_VALUE)
    public List<String> getUsernames() {
        LOGGER.debug("ass usernames requested");
        return userRepository.findAll()
                .stream()
                .map(User::getUsername)
                .collect(Collectors.toList());
    }
    
    @GetMapping(path = "/usernames/{username}", produces = APPLICATION_JSON_UTF8_VALUE)
    public User getUserByUsername(@PathVariable("username") String username) {
        LOGGER.debug("user with {} username requested", username);
        return userService.getUserByUsername(username);
    }
    
    
    @PostMapping(path = "/users", consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
    public User addUser(@RequestBody User user) {
        LOGGER.debug("adding user = {}", user);
        return userService.addUser(user);
    }
    
    @PostMapping(path = "/users/{userId}/todos", consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
    public User addUserTodo(@PathVariable("userId") String userId, @RequestBody Todo todo) {
        LOGGER.debug("adding todo = {} to userId = {}", todo, userId);
        return todoService.addUserTodo(userId, todo);
    }
    
}
