package edu.aabramov.controller;

import edu.aabramov.model.User;
import edu.aabramov.model.UserExistsDto;
import edu.aabramov.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

/**
 * @author Andrii Abramov on 12/3/16.
 */
@RestController
public class UsernameController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(UsernameController.class);
    
    private final UserService userService;
    
    @Autowired
    public UsernameController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping(path = "/usernames", produces = APPLICATION_JSON_UTF8_VALUE)
    public List<String> getUsernames() {
        LOGGER.debug("all usernames requested");
        return userService.getUsernames();
    }
    
    @GetMapping(path = "/usernames/{username}", produces = APPLICATION_JSON_UTF8_VALUE)
    public User getUserByUsername(@PathVariable("username") String username) {
        LOGGER.debug("user with {} username requested", username);
        return userService.getByUsername(username);
    }
    
    @GetMapping(path = "/usernames/exists/{username}", produces = APPLICATION_JSON_UTF8_VALUE)
    public UserExistsDto checkIfUserExists(@PathVariable("username") String username) {
        LOGGER.debug("user with {} username requested", username);
        return userService.existsWithUsername(username);
    }
    
}
