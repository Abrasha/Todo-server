package edu.aabramov.service;

import edu.aabramov.model.Todo;
import edu.aabramov.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Andrii Abramov on 11/30/16.
 */
@Service
public class TodoService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(TodoService.class);
    
    private final TodoGenerator todoGenerator;
    
    private final UserService userService;
    
    @Autowired
    public TodoService(TodoGenerator todoGenerator, UserService userService) {
        LOGGER.debug("TodoService init");
        this.userService = userService;
        this.todoGenerator = todoGenerator;
    }
    
    public List<Todo> insertRandomTodos(int count, String userId) {
        LOGGER.debug("inserting {} random todos for user id: {}", count, userId);
        List<Todo> todos = todoGenerator.getRandomTodos(count);
        User user = userService.findOne(userId);
        LOGGER.debug("{} user loaded", user.getId());
        user.getTodos().addAll(todos);
        LOGGER.debug("updating user {} with {}", user.getId(), user);
        User updatedUser = userService.update(userId, user);
        return updatedUser.getTodos();
    }
    
    public User addUserTodo(String id, Todo todo) {
        LOGGER.debug("adding todo: {} to user id: {}", todo, id);
        User user = userService.findOne(id);
        LOGGER.debug("{} user loaded", user.getId());
        user.getTodos().add(todo);
        LOGGER.debug("updating user {} with {}", user.getId(), todo);
        user = userService.update(id, user);
        return user;
    }
    
    // TODO: 12/1/16 $unwind
    public List<Todo> getUserTodos(String userId) {
        LOGGER.debug("requested todos for user id = {}", userId);
        User user = userService.findOne(userId);
        LOGGER.debug("got user {}", userId);
        return user.getTodos();
    }
    
}
