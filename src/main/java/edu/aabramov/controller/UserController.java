package edu.aabramov.controller;

import edu.aabramov.model.Todo;
import edu.aabramov.model.User;
import edu.aabramov.repository.UserRepository;
import edu.aabramov.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
    
    @GetMapping("/users")
    public List<String> users() {
        return userRepository.findAll().stream().map(User::getUsername).collect(Collectors.toList());
    }
    
    @GetMapping(path = "/users/{userId}/todos")
    public List<Todo> getUsersTodo(@PathVariable("userId") String userId) {
        return userService.getUser(userId).getTodos();
    }
    
    @GetMapping(path = "/usernames/{username}")
    public User userByUsername(@PathVariable("username") String username) {
        return userRepository.findOneByUsername(username);
    }
    
}
