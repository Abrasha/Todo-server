package edu.aabramov.controller;

import edu.aabramov.model.User;
import edu.aabramov.model.UserDetails;
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
    
    @Autowired
    public UserController(UserService userService) {
        LOGGER.debug("UserController init");
        this.userService = userService;
    }
    
    @GetMapping(path = "/users", produces = APPLICATION_JSON_UTF8_VALUE)
    public List<UserDetails> getAllUsers() {
        LOGGER.debug("all users requested");
        return userService.getAllUsers();
    }
    
    @GetMapping(path = "/users/details/{userId}", produces = APPLICATION_JSON_UTF8_VALUE)
    public UserDetails getUserDetails(@PathVariable("userId") String userId) {
        LOGGER.debug("user details {} requested", userId);
        return userService.getUserDetails(userId);
    }
    
    @PostMapping(path = "/users", consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
    public User addUser(@RequestBody User user) {
        LOGGER.debug("adding user = {}", user);
        return userService.insert(user);
    }
    
}
