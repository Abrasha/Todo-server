package edu.aabramov.todo.web.controller.rest.debug;

import edu.aabramov.todo.core.model.UserDetails;
import edu.aabramov.todo.core.util.AppProfiles;
import edu.aabramov.todo.service.debug.DebugUserService;
import edu.aabramov.todo.web.controller.annotation.JsonRestController;
import edu.aabramov.todo.web.controller.rest.AbstractUserDetailsController;
import edu.aabramov.todo.web.controller.rest.path.UserPaths;
import edu.aabramov.todo.web.dto.UserDetailsDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Andrii Abramov on 12/29/16.
 */
@JsonRestController
@Profile(AppProfiles.TEST)
public class DebugUserDetailsController extends AbstractUserDetailsController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(DebugUserDetailsController.class);
    
    private final DebugUserService debugUserService;
    
    @Autowired
    public DebugUserDetailsController(DebugUserService debugUserService) {
        this.debugUserService = debugUserService;
    }
    
    @PostMapping(value = UserPaths.USERS_ROOT + "/generate")
    public List<UserDetailsDto> insertRandomUsers(@RequestParam("count") int count) {
        LOGGER.debug("inserting random users. count = {}", count);
        debugUserService.insertRandomUsers(count);
        List<UserDetails> result = debugUserService.getAllUsers();
        return convertToUserDetailsDto(result);
    }
    
    @GetMapping(value = UserPaths.USERS_ROOT)
    public List<UserDetailsDto> getAllUsers() {
        List<UserDetails> result = debugUserService.getAllUsers();
        return convertToUserDetailsDto(result);
    }
    
}
