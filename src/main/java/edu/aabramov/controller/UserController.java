package edu.aabramov.controller;

import edu.aabramov.model.Todo;
import edu.aabramov.model.User;
import edu.aabramov.model.UserDetails;
import edu.aabramov.repository.UserRepository;
import edu.aabramov.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Andrii Abramov on 11/24/16.
 */
@RestController
public class UserController {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserService userService;
    
    @GetMapping(path = "/users", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<UserDetails> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserDetails::new)
                .collect(Collectors.toList());
    }
    
    @GetMapping(path = "/users/{userId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public User getUser(@PathVariable("userId") String userId) {
        return userService.getUser(userId);
    }
    
    @GetMapping(path = "/users/{userId}/todos", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Todo> getUserTodos(@PathVariable("userId") String userId) {
        return userService.getUser(userId).getTodos();
    }
    
    @GetMapping(path = "/usernames", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<String> getUsernames() {
        return userRepository.findAll()
                .stream()
                .map(User::getUsername)
                .collect(Collectors.toList());
    }
    
    @GetMapping(path = "/usernames/{username}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public User getUserByUsername(@PathVariable("username") String username) {
        return userService.getUserBuUsername(username);
    }
    
    @PostMapping(path = "/users/{userId}/todos", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public User addUserTodo(@PathVariable("userId") String userId, @RequestBody Todo todo) {
        return userService.addUserTodo(userId, todo);
    }
    
}
