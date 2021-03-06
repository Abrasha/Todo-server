package edu.aabramov.todo.web.controller.rest.debug;

import edu.aabramov.todo.core.model.User;
import edu.aabramov.todo.core.util.AppProfiles;
import edu.aabramov.todo.service.UserService;
import edu.aabramov.todo.service.debug.DebugUserService;
import edu.aabramov.todo.web.controller.annotation.JsonRestController;
import edu.aabramov.todo.web.controller.rest.path.UsernamePaths;
import edu.aabramov.todo.web.dto.UserDto;
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
    private final DebugUserService debugUserService;
    
    @Autowired
    public DebugUsernameController(UserService userService, ModelMapper modelMapper, DebugUserService debugUserService) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.debugUserService = debugUserService;
    }
    
    @GetMapping(path = UsernamePaths.USERNAMES_ROOT)
    public List<String> getUsernames() {
        LOGGER.debug("all usernames requested");
        return debugUserService.getUsernames();
    }
    
    @GetMapping(path = UsernamePaths.USERNAMES_ROOT + "/{username}")
    public UserDto getUserByUsername(@PathVariable("username") String username) {
        LOGGER.debug("user with {} username requested", username);
        User foundUser = userService.getByUsername(username);
        return modelMapper.map(foundUser, UserDto.class);
    }
    
}
