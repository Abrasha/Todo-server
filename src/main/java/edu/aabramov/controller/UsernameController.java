package edu.aabramov.controller;

import edu.aabramov.dto.UserDto;
import edu.aabramov.dto.UserExistsDto;
import edu.aabramov.model.User;
import edu.aabramov.service.UserService;
import org.modelmapper.ModelMapper;
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
    private final ModelMapper modelMapper;
    
    @Autowired
    public UsernameController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }
    
    @GetMapping(path = "/usernames", produces = APPLICATION_JSON_UTF8_VALUE)
    public List<String> getUsernames() {
        LOGGER.debug("all usernames requested");
        return userService.getUsernames();
    }
    
    @GetMapping(path = "/usernames/{username}", produces = APPLICATION_JSON_UTF8_VALUE)
    public UserDto getUserByUsername(@PathVariable("username") String username) {
        LOGGER.debug("user with {} username requested", username);
        User foundUser = userService.getByUsername(username);
        return modelMapper.map(foundUser, UserDto.class);
    }
    
    @GetMapping(path = "/usernames/exists/{username}", produces = APPLICATION_JSON_UTF8_VALUE)
    public UserExistsDto checkIfUserExists(@PathVariable("username") String username) {
        LOGGER.debug("user with {} username requested", username);
        return userService.existsWithUsername(username);
    }
    
}
