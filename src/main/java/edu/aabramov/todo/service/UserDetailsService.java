package edu.aabramov.todo.service;

import edu.aabramov.todo.core.model.User;
import edu.aabramov.todo.core.model.UserDetails;
import edu.aabramov.todo.persistence.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Andrii Abramov on 1/11/17.
 */
@Service
public class UserDetailsService {
    
    private final UserRepository userRepository;
    
    @Autowired
    public UserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    
    public UserDetails getUserDetails(String userId) {
        User result = userRepository.getUserDetails(userId);
        return new UserDetails(result);
    }
    
}
