package edu.aabramov.service;

import edu.aabramov.model.User;
import edu.aabramov.repository.UserRepository;
import edu.aabramov.repository.cache.UserCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author Andrii Abramov on 11/25/16.
 */
@Service
public class UserService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    
    private final UserRepository userRepository;
    private final UserCache userCache;
    
    @Autowired
    public UserService(UserRepository userRepository, UserCache userCache) {
        LOGGER.debug("UserService init");
        this.userCache = userCache;
        this.userRepository = userRepository;
    }
    
    public User findOne(String id) {
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
            userCache.put(hashKey, result);
            
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
        String hashKey = userCache.getIdKey(savedUser.getId());
        
        LOGGER.debug("putting user = {} to cache", savedUser);
        LOGGER.debug("created hash key = {} for user with id = {}", hashKey, id);
        userCache.put(hashKey, savedUser);
        LOGGER.debug("put user = {} to cache - done", savedUser);
        return savedUser;
    }
    
    public User findByUsername(String username) {
        LOGGER.debug("user with username = {} requested", username);
        
        String hashKey = userCache.getUsernameKey(username);
        
        LOGGER.debug("created hash key = {} for user with username = {}", hashKey, username);
        LOGGER.debug("check if user with username = {} exists in cache", username);
        
        if (userCache.hasKey(hashKey)) {
            LOGGER.debug("user with username = {} is in cache", username);
            return userCache.get(hashKey);
        } else {
            LOGGER.debug("user with username = {} is not in cache", username);
            LOGGER.debug("getting user with username = {} from repository", username);
            User result = userRepository.findOneByUsername(username);
            
            LOGGER.debug("got user with username = {} from repository, user = {}", username, result);
            LOGGER.debug("putting user = {} to cache");
            userCache.put(hashKey, result);
            
            LOGGER.debug("put user = {} to cache - done");
            return result;
        }
    }
    
    public User insert(User user) {
        LOGGER.debug("adding user = {} to repository", user);
        
        if (user.getTodos() == null) {
            LOGGER.debug("no todos for user, init with empty list", user);
            user.setTodos(new ArrayList<>(0));
        }
        
        User result = userRepository.insert(user);
        String hashKey = userCache.getIdKey(result.getId());
        LOGGER.debug("inserting user = {} to cache with hashKey = {}", user, hashKey);
        userCache.put(hashKey, result);
        LOGGER.debug("inserting user = {} to repository", user);
        return result;
    }
}
