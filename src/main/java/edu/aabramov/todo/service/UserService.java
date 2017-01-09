package edu.aabramov.todo.service;

import edu.aabramov.todo.core.model.Todo;
import edu.aabramov.todo.core.model.User;
import edu.aabramov.todo.persistence.repository.user.UserRepository;
import edu.aabramov.todo.service.cache.UserCache;
import edu.aabramov.todo.web.dto.UserExistsDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author Andrii Abramov on 11/25/16.
 */
@Service
public class UserService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    
    private final UserRepository userRepository;
    private final UserCache userCache;
    private final PasswordEncoder passwordEncoder;
    
    @Autowired
    public UserService(UserRepository userRepository, UserCache userCache, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        LOGGER.debug("UserService init");
        this.userCache = userCache;
        this.userRepository = userRepository;
    }
    
    public User getUser(final String id) {
        LOGGER.debug("User with id = {} requested", id);
        
        return userCache.getById(id)
                .orElseGet(() -> userRepository.findOne(id));
    }
    
    public User update(String id, User user) {
        LOGGER.debug("updating user: id = {} with user = {}", id, user);
        user.setId(id);
        
        LOGGER.debug("saving user = {} to repository", user);
        User savedUser = userRepository.save(user);
        
        LOGGER.debug("saved user = {} to repository", savedUser);
        
        LOGGER.debug("putting user = {} to cache", savedUser);
        userCache.refreshUserInCache(savedUser);
        LOGGER.debug("put user = {} to cache - done", savedUser);
        return savedUser;
    }
    
    public User getByUsername(final String username) {
        LOGGER.debug("user with username = {} requested", username);
        
        return userCache.getByUsername(username)
                .orElseGet(() -> userRepository.findOneByUsername(username));
    }
    
    public User insert(String username, String password) {
        LOGGER.debug("adding user = {} to repository", username);
        
        User userToAdd = new User();
        userToAdd.setUsername(username);
        
        userToAdd.setTodos(new ArrayList<>(0));
        userToAdd.setPassword(passwordEncoder.encode(password));
        User result = userRepository.insert(userToAdd);
        LOGGER.debug("inserting user = {} to cache", userToAdd);
        userCache.refreshUserInCache(result);
        LOGGER.debug("inserting user = {} to repository", userToAdd);
        return result;
    }
    
    public UserExistsDto existsWithUsername(String username) {
        LOGGER.debug("check if user with username = {} exists", username);
        
        boolean exists = getByUsername(username) != null;
        LOGGER.debug("user with username {} exists: ", exists);
        return new UserExistsDto(username, exists);
    }
    
    public List<String> getUsernames() {
        return userRepository.findAll()
                .stream()
                .map(User::getUsername)
                .collect(toList());
    }
    
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    public User getUserDetails(String userId) {
        return userRepository.getUserDetails(userId);
    }
    
    public List<Todo> getUserTodos(String userId) {
        return userRepository.getUserTodos(userId);
    }
    
}
