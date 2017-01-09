package edu.aabramov.todo.web.controller.debug;

import edu.aabramov.todo.core.util.AppProfiles;
import edu.aabramov.todo.web.controller.annotation.JsonRestController;
import edu.aabramov.todo.web.dto.UserDto;
import edu.aabramov.todo.core.model.User;
import edu.aabramov.todo.service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author Andrii Abramov on 12/3/16.
 */
@JsonRestController
@Profile(AppProfiles.TEST)
public class DebugUsernameController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(DebugUsernameController.class);
    
    private final UserService userService;
    private final ModelMapper modelMapper;
    
    @Autowired
    public DebugUsernameController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }
    
    @GetMapping(path = "/usernames")
    public List<String> getUsernames() {
        LOGGER.debug("all usernames requested");
        return userService.getUsernames();
    }
    
    @GetMapping(path = "/usernames/{username}")
    public UserDto getUserByUsername(@PathVariable("username") String username) {
        LOGGER.debug("user with {} username requested", username);
        User foundUser = userService.getByUsername(username);
        return modelMapper.map(foundUser, UserDto.class);
    }
    
}
