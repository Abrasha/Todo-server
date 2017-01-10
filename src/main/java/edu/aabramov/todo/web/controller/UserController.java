package edu.aabramov.todo.web.controller;

import edu.aabramov.todo.core.model.User;
import edu.aabramov.todo.service.UserService;
import edu.aabramov.todo.web.controller.annotation.JsonRestController;
import edu.aabramov.todo.web.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Andrii Abramov on 11/24/16.
 */
@JsonRestController
public class UserController extends MappingController<User, UserDto> {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    
    private final UserService userService;
    
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
        LOGGER.debug("UserController init");
    }
    
    @GetMapping(path = "/users/{userId}")
    public UserDto getUser(@PathVariable("userId") String userId) {
        LOGGER.debug("all users requested");
        User foundUser = userService.getUser(userId);
        return convertToDto(foundUser);
    }
    
    @Override
    protected Class<UserDto> getDtoClass() {
        return UserDto.class;
    }
    
    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }
}
