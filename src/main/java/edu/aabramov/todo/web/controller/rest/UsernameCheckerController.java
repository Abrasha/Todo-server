package edu.aabramov.todo.web.controller.rest;

import edu.aabramov.todo.core.model.UserExistsEntity;
import edu.aabramov.todo.service.UserService;
import edu.aabramov.todo.web.controller.annotation.JsonRestController;
import edu.aabramov.todo.web.controller.rest.path.UsernamePaths;
import edu.aabramov.todo.web.dto.UserExistsDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Andrii Abramov on 1/11/17.
 */
@JsonRestController
public class UsernameCheckerController extends AbstractUserExistsController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(UsernameCheckerController.class);
    
    private final UserService userService;
    
    @Autowired
    public UsernameCheckerController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping(path = UsernamePaths.USERNAMES_EXISTS)
    public UserExistsDto checkIfUserExists(@PathVariable("username") String username) {
        LOGGER.debug("user with {} username requested", username);
        UserExistsEntity result = userService.existsWithUsername(username);
        return convertToDto(result);
    }
    
}
