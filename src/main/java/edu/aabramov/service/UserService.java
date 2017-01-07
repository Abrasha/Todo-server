package edu.aabramov.service;

import edu.aabramov.cache.UserCache;
import edu.aabramov.dto.UserExistsDto;
import edu.aabramov.model.Todo;
import edu.aabramov.model.User;
import edu.aabramov.model.UserDetails;
import edu.aabramov.repository.user.UserRepository;
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
    
    public User getUser(String id) {
        LOGGER.debug("User with id = {} requested", id);
        
        String hashKey = userCache.getIdKey(id);
        
        LOGGER.debug("check if user with id = {} exists in cache", id);
        if (userCache.hasKey(hashKey)) {
            LOGGER.debug("user with id = {} is in cache", id);
            return userCache.get(hashKey);
        } else {
            LOGGER.debug("user with id = {} is not in cache", id);
            LOGGER.debug("getting user with id = {} from repository", id);
            User result = userRepository.findOne(id);
            
            LOGGER.debug("for user = {} from repository", result);
            LOGGER.debug("putting user = {} to cache");
            userCache.refreshUserInCache(result);
            
            LOGGER.debug("put user = {} to cache - done");
            return result;
        }
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
    
    public User getByUsername(String username) {
        LOGGER.debug("user with username = {} requested", username);
        
        String hashKey = userCache.getUsernameKey(username);
        
        LOGGER.debug("created hash key = {} for user with username = {}", hashKey, username);
        LOGGER.debug("check if user with username = {} exists in cache", username);
        
        User foundUser;
        if (userCache.hasKey(hashKey)) {
            LOGGER.debug("user with username = {} is in cache", username);
            foundUser = userCache.get(hashKey);
        } else {
            LOGGER.debug("user with username = {} is not in cache", username);
            LOGGER.debug("getting user with username = {} from repository", username);
            foundUser = userRepository.findOneByUsername(username);
            
            LOGGER.debug("got user with username = {} from repository, user = {}", username, foundUser);
            LOGGER.debug("putting user = {} to cache");
            userCache.refreshUserInCache(foundUser);
            
            LOGGER.debug("put user = {} to cache - done");
        }
        return foundUser;
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
    
    public List<UserDetails> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserDetails::new)
                .collect(toList());
    }
    
    public UserDetails getUserDetails(String userId) {
        return new UserDetails(userRepository.getUserDetails(userId));
    }
    
    public List<Todo> getUserTodos(String userId) {
        return userRepository.getUserTodos(userId);
    }
    
}
