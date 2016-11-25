package edu.aabramov.service;

import edu.aabramov.model.Todo;
import edu.aabramov.model.User;
import edu.aabramov.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author Andrii Abramov on 11/25/16.
 */
@Service
public class UserService {
    
    public static final String USER_REDIS_KEY = "User";
    
    @Autowired
    private RedisTemplate<String, User> redisTemplate;
    
    @Autowired
    private UserRepository userRepository;
    
    // TODO: 11/25/16 extract to User cache class
    private HashOperations<String, String, User> hashOperations;
    
    @PostConstruct
    public void init() {
        hashOperations = redisTemplate.opsForHash();
    }
    
    public User getUser(String id) {
        String hashKey = "user:id:" + id;
        if (hashOperations.hasKey(USER_REDIS_KEY, hashKey)) {
            return hashOperations.get(USER_REDIS_KEY, hashKey);
        } else {
            User result = userRepository.findOne(id);
            hashOperations.put(USER_REDIS_KEY, hashKey, result);
            return result;
        }
    }
    
    public User getUserBuUsername(String username) {
        String hashKey = "user:username:" + username;
        if (hashOperations.hasKey(USER_REDIS_KEY, hashKey)) {
            return hashOperations.get(USER_REDIS_KEY, hashKey);
        } else {
            User result = userRepository.findOneByUsername(username);
            hashOperations.put(USER_REDIS_KEY, hashKey, result);
            return result;
        }
    }
    
    public User addUserTodo(String id, Todo todo) {
        User user = getUser(id);
        user.getTodos().add(todo);
        user = userRepository.save(user);
        
        String hashKey = "user:id:" + user.getId();
        
        hashOperations.put(USER_REDIS_KEY, hashKey, user);
        return user;
    }
    
}
