package edu.aabramov.service;

import edu.aabramov.model.User;
import org.modelmapper.ModelMapper;
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
    
    // todo
    public User authorize(String username, String password) {
        
        User user = userService.getByUsername(username);
        
        if (passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }
        
        throw new RuntimeException("Not authorized: " + username);
    }
    
}
