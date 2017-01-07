package edu.aabramov.controller.debug;

import edu.aabramov.configuration.AppProfiles;
import edu.aabramov.controller.annotation.JsonRestController;
import edu.aabramov.model.UserDetails;
import edu.aabramov.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

/**
 * @author Andrii Abramov on 12/29/16.
 */
@JsonRestController
@Profile(AppProfiles.TEST)
public class DebugUserController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(DebugUserController.class);
    
    private final UserService userService;
    
    @Autowired
    public DebugUserController(UserService userService) {
        this.userService = userService;
    }
    
    @PostMapping(value = "/users/generate")
    public List<UserDetails> insertRandomUsers(@RequestParam("count") int count) {
        LOGGER.debug("inserting random users. count = {}", count);
        userService.insertRandomUsers(count);
        return userService.getAllUsers();
    }
    
}
