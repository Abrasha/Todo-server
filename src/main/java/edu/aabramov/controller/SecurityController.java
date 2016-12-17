package edu.aabramov.controller;

import edu.aabramov.dto.UserDto;
import edu.aabramov.model.User;
import edu.aabramov.service.AuthorizationService;
import edu.aabramov.service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

/**
 * @author Andrii Abramov on 12/11/16.
 */
@RestController
public class SecurityController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityController.class);
    
    private final AuthorizationService authorizationService;
    private final ModelMapper modelMapper;
    private final UserService userService;
    
    @Autowired
    public SecurityController(AuthorizationService authorizationService, ModelMapper modelMapper, UserService userService) {
        this.authorizationService = authorizationService;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }
    
    
    @PostMapping(value = "/authorize", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UserDto authorize(@RequestHeader("username") String username, @RequestHeader("password") String password) {
        LOGGER.debug("Perform authentication for {}", username);
        User foundUser = authorizationService.authorize(username, password);
        return modelMapper.map(foundUser, UserDto.class);
    }
    
    @PostMapping(path = "/users", produces = APPLICATION_JSON_UTF8_VALUE)
    public UserDto registerUser(@RequestHeader("username") String username, @RequestHeader("password") String password) {
        LOGGER.debug("registering user = {}", username);
        User insertedUser = userService.insert(username, password);
        return modelMapper.map(insertedUser, UserDto.class);
    }
    
}
