package edu.aabramov.controller;

import edu.aabramov.model.Todo;
import edu.aabramov.model.User;
import edu.aabramov.model.UserDetails;
import edu.aabramov.repository.UserRepository;
import edu.aabramov.service.TodoService;
import edu.aabramov.service.UserService;
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
    
    private final UserRepository userRepository;
    private final UserService userService;
    private final TodoService todoService;
    
    @Autowired
    public UserController(UserRepository userRepository, UserService userService, TodoService todoService) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.todoService = todoService;
    }
    
    @GetMapping(path = "/users", produces = APPLICATION_JSON_UTF8_VALUE)
    public List<UserDetails> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserDetails::new)
                .collect(Collectors.toList());
    }
    
    @GetMapping(path = "/users/{userId}", produces = APPLICATION_JSON_UTF8_VALUE)
    public User getUser(@PathVariable("userId") String userId) {
        return userService.getUser(userId);
    }
    
    @GetMapping(path = "/usernames", produces = APPLICATION_JSON_UTF8_VALUE)
    public List<String> getUsernames() {
        return userRepository.findAll()
                .stream()
                .map(User::getUsername)
                .collect(Collectors.toList());
    }
    
    @GetMapping(path = "/usernames/{username}", produces = APPLICATION_JSON_UTF8_VALUE)
    public User getUserByUsername(@PathVariable("username") String username) {
        return userService.getUserByUsername(username);
    }
    
    
    @PostMapping(path = "/users", consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }
    
    @PostMapping(path = "/users/{userId}/todos", consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
    public User addUserTodo(@PathVariable("userId") String userId, @RequestBody Todo todo) {
        return todoService.addUserTodo(userId, todo);
    }
    
}
