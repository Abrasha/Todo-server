package edu.aabramov.todo.service;

import edu.aabramov.todo.web.exception.http.NotAuthorizedException;
import edu.aabramov.todo.core.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Andrii Abramov on 12/11/16.
 */
@Service
public class AuthorizationService {
    
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    
    @Autowired
    public AuthorizationService(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }
    
    public User authorize(String username, String password) {
        
        User user = userService.getByUsername(username);
        
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }
        
        throw new NotAuthorizedException("Wrong login or password.");
    }
    
}
