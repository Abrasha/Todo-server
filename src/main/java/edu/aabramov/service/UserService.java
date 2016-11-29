package edu.aabramov.service;

import edu.aabramov.model.Priority;
import edu.aabramov.model.Todo;
import edu.aabramov.model.User;
import edu.aabramov.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;

/**
 * @author Andrii Abramov on 11/25/16.
 */
@Service
public class UserService {
    
    public static final String USER_REDIS_KEY = "User";
    
    @Autowired
    private UserRepository userRepository;
    
    // TODO: 11/25/16 extract to User cache class
    @Resource(name = "redisTemplate")
    private HashOperations<String, String, User> hashOperations;
    
    @PostConstruct
    public void init() {
        insertSamples();
    }
    
    public void insertSamples() {
        
        userRepository.deleteAll();
        
        for (int i = 0; i < 5; i++) {
            final int index = i;
            List<Todo> todos = IntStream.range(1, 5).mapToObj(n -> getTodo(n * index)).collect(Collectors.toList());
            User user = new User();
            user.setUsername("User_" + index);
            user.setTodos(todos);
            
            System.err.println(user);
            
            user = userRepository.save(user);
            
            System.err.println(user);
            
            System.err.println(userRepository.findAll());
        }
    }
    
    
    private Todo getTodo(int number) {
        Todo todo = new Todo();
        todo.setBody("Body #" + number);
        todo.setWhen(new Date());
        todo.setPriority(Priority.DEFAULT);
        todo.setTitle("Title #" + number);
        
        todo.setTags(asList("Tag " + number, "Tag " + number * 2));
        
        return todo;
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
    
    public User getUserByUsername(String username) {
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
