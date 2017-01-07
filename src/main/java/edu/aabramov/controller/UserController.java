package edu.aabramov.controller;

import edu.aabramov.controller.annotation.JsonRestController;
import edu.aabramov.dto.UserDto;
import edu.aabramov.model.User;
import edu.aabramov.model.UserDetails;
import edu.aabramov.service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author Andrii Abramov on 11/24/16.
 */
@JsonRestController
public class UserController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    
    private final UserService userService;
    private final ModelMapper modelMapper;
    
    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        LOGGER.debug("UserController init");
        this.userService = userService;
    }
    
    @GetMapping(path = "/users")
    public List<UserDetails> getAllUsers() {
        LOGGER.debug("all users requested");
        return userService.getAllUsers();
    }
    
    @GetMapping(path = "/users/{userId}")
    public UserDto getUser(@PathVariable("userId") String userId) {
        LOGGER.debug("all users requested");
        User foundUser = userService.getUser(userId);
        return modelMapper.map(foundUser, UserDto.class);
    }
    
    @GetMapping(path = "/users/{userId}/details")
    public UserDetails getUserDetails(@PathVariable("userId") String userId) {
        LOGGER.debug("user details {} requested", userId);
        return userService.getUserDetails(userId);
    }
    
}
