package edu.aabramov.todo.web.controller;

import edu.aabramov.todo.web.controller.annotation.JsonRestController;
import edu.aabramov.todo.web.dto.UserExistsDto;
import edu.aabramov.todo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Andrii Abramov on 12/3/16.
 */
@JsonRestController
public class UsernameController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(UsernameController.class);
    
    private final UserService userService;
    
    @Autowired
    public UsernameController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping(path = "/usernames/exists/{username}")
    public UserExistsDto checkIfUserExists(@PathVariable("username") String username) {
        LOGGER.debug("user with {} username requested", username);
        return userService.existsWithUsername(username);
    }
    
}
