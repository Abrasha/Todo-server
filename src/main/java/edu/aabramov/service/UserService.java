package edu.aabramov.service;

import edu.aabramov.model.User;
import edu.aabramov.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * @author Andrii Abramov on 11/25/16.
 */
@Service
public class UserService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    
    private static final String USER_REDIS_KEY = "User";
    
    private final UserRepository userRepository;
    
    // TODO: 11/25/16 extract to User cache class
    @Resource(name = "redisTemplate")
    private HashOperations<String, String, User> hashOperations;
    
    @Autowired
    public UserService(UserRepository userRepository) {
        LOGGER.debug("UserService init");
        this.userRepository = userRepository;
    }
    
    public User getUser(String id) {
        LOGGER.debug("User with id = {} requested", id);
        String hashKey = "user:id:" + id;
        LOGGER.debug("check if user with id = {} exists in cache", id);
        if (hashOperations.hasKey(USER_REDIS_KEY, hashKey)) {
            LOGGER.debug("user with id = {} is in cache", id);
            return hashOperations.get(USER_REDIS_KEY, hashKey);
        } else {
            LOGGER.debug("user with id = {} is not in cache", id);
            LOGGER.debug("getting user with id = {} from repository", id);
            User result = userRepository.findOne(id);
            LOGGER.debug("for user = {} from repository", result);
            LOGGER.debug("putting user = {} to cache");
            hashOperations.put(USER_REDIS_KEY, hashKey, result);
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
        String hashKey = "user:id:" + savedUser.getId();
        LOGGER.debug("putting user = {} to cache", savedUser);
        LOGGER.debug("created hash key = {} for user with id = {}", hashKey, id);
        hashOperations.put(USER_REDIS_KEY, hashKey, savedUser);
        LOGGER.debug("put user = {} to cache - done", savedUser);
        return savedUser;
    }
    
    public User getUserByUsername(String username) {
        LOGGER.debug("user with username = {} requested", username);
        String hashKey = "user:username:" + username;
        LOGGER.debug("created hash key = {} for user with username = {}", hashKey, username);
        LOGGER.debug("check if user with username = {} exists in cache", username);
        if (hashOperations.hasKey(USER_REDIS_KEY, hashKey)) {
            LOGGER.debug("user with username = {} is in cache", username);
            return hashOperations.get(USER_REDIS_KEY, hashKey);
        } else {
            LOGGER.debug("user with username = {} is not in cache", username);
            LOGGER.debug("getting user with username = {} from repository", username);
            User result = userRepository.findOneByUsername(username);
            LOGGER.debug("got user with username = {} from repository, user = {}", username, result);
            LOGGER.debug("putting user = {} to cache");
            hashOperations.put(USER_REDIS_KEY, hashKey, result);
            LOGGER.debug("put user = {} to cache - done");
            return result;
        }
    }
    
    public User addUser(User user) {
        LOGGER.debug("adding user = {} to repository", user);
        if (user.getTodos() == null) {
            LOGGER.debug("no todos for user, init with empty list", user);
            user.setTodos(new ArrayList<>(0));
        }
        LOGGER.debug("inserting user = {} to repository", user);
        return userRepository.insert(user);
    }
}
