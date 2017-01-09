package edu.aabramov.todo.web.controller.debug;

import edu.aabramov.todo.core.model.User;
import edu.aabramov.todo.core.util.AppProfiles;
import edu.aabramov.todo.web.controller.annotation.JsonRestController;
import edu.aabramov.todo.service.UserService;
import edu.aabramov.todo.service.debug.DebugUserService;
import edu.aabramov.todo.web.dto.UserDetailsDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Andrii Abramov on 12/29/16.
 */
@JsonRestController
@Profile(AppProfiles.TEST)
public class DebugUserController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(DebugUserController.class);
    
    private final DebugUserService debugUserService;
    private final UserService userService;
    
    @Autowired
    public DebugUserController(DebugUserService debugUserService, UserService userService) {
        this.debugUserService = debugUserService;
        this.userService = userService;
    }
    
    @PostMapping(value = "/users/generate")
    public List<User> insertRandomUsers(@RequestParam("count") int count) {
        LOGGER.debug("inserting random users. count = {}", count);
        debugUserService.insertRandomUsers(count);
        return userService.getAllUsers();
    }
    
}
