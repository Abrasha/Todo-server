package edu.aabramov.todo.web.controller;

import edu.aabramov.todo.core.model.UserDetails;
import edu.aabramov.todo.service.UserDetailsService;
import edu.aabramov.todo.web.controller.annotation.JsonRestController;
import edu.aabramov.todo.web.dto.UserDetailsDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Andrii Abramov on 1/11/17.
 */
@JsonRestController
public class UserDetailsController extends AbstractUserDetailsController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsController.class);
    
    private final UserDetailsService userDetailsService;
    
    @Autowired
    public UserDetailsController(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
        LOGGER.debug("UserController init");
    }
    
    @GetMapping(path = "/users/{userId}/details")
    public UserDetailsDto getUserDetails(@PathVariable("userId") String userId) {
        LOGGER.debug("user details {} requested", userId);
        UserDetails result = userDetailsService.getUserDetails(userId);
        return convertToDto(result);
    }
    
}
