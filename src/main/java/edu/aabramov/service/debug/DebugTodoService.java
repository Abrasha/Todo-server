package edu.aabramov.service.debug;

import edu.aabramov.model.Todo;
import edu.aabramov.model.User;
import edu.aabramov.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Andrii Abramov on 11/30/16.
 */
@Service
public class DebugTodoService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(DebugTodoService.class);
    
    private final TodoGenerator todoGenerator;
    
    private final UserService userService;
    
    @Autowired
    public DebugTodoService(TodoGenerator todoGenerator, UserService userService) {
        LOGGER.debug("TodoService init");
        this.userService = userService;
        this.todoGenerator = todoGenerator;
    }
    
    public List<Todo> insertRandomTodos(int count, String userId) {
        LOGGER.debug("inserting {} random todos for user id: {}", count, userId);
        List<Todo> todos = todoGenerator.getRandomTodos(count);
        User user = userService.getUser(userId);
        
        LOGGER.debug("{} user loaded", user.getId());
        user.getTodos().addAll(todos);
        
        LOGGER.debug("updating user {} with {}", user.getId(), user);
        User updatedUser = userService.update(userId, user);
        return updatedUser.getTodos();
    }
    
}
