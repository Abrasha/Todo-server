package edu.aabramov.todo.web.controller;

import edu.aabramov.todo.web.controller.annotation.JsonRestController;
import edu.aabramov.todo.web.dto.UserDto;
import edu.aabramov.todo.core.model.User;
import edu.aabramov.todo.service.AuthorizationService;
import edu.aabramov.todo.service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @author Andrii Abramov on 12/11/16.
 */
@JsonRestController
public class SecurityController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityController.class);
    
    private static final String HEADER_PASSWORD = "password";
    private static final String HEADER_USERNAME = "username";
    
    private final AuthorizationService authorizationService;
    private final ModelMapper modelMapper;
    private final UserService userService;
    
    @Autowired
    public SecurityController(AuthorizationService authorizationService, ModelMapper modelMapper, UserService userService) {
        this.authorizationService = authorizationService;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }
    
    
    @PostMapping(value = "/authorize")
    public UserDto authorize(@RequestHeader(HEADER_USERNAME) String username, @RequestHeader(HEADER_PASSWORD) String password) {
        LOGGER.debug("Perform authentication for {}", username);
        User foundUser = authorizationService.authorize(username, password);
        return modelMapper.map(foundUser, UserDto.class);
    }
    
    @PostMapping(path = "/users")
    public UserDto registerUser(@RequestHeader(HEADER_USERNAME) String username, @RequestHeader(HEADER_PASSWORD) String password) {
        LOGGER.debug("registering user = {}", username);
        User insertedUser = userService.insert(username, password);
        return modelMapper.map(insertedUser, UserDto.class);
    }
    
}
