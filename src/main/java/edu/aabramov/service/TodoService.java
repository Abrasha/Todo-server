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
    private final IdentifierManager identifierManager;
    
    @Autowired
    public TodoService(TodoGenerator todoGenerator, UserService userService, IdentifierManager identifierManager) {
        LOGGER.debug("TodoService init");
        this.identifierManager = identifierManager;
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
        todo.setId(identifierManager.hexObjectId());
        user.getTodos().add(todo);
        LOGGER.debug("updating user {} with {}", user.getId(), todo);
        user = userService.update(id, user);
        return user;
    }
    
    public List<Todo> getUserTodos(String userId) {
        LOGGER.debug("requested todos for user id = {}", userId);
        User user = userService.findOne(userId);
        LOGGER.debug("got user {}", userId);
        return user.getTodos();
    }
    
    public Todo getTodoForUser(String userId, String todoId) {
        User forUser = userService.findOne(userId);
        return forUser.getTodos().stream()
                .filter(e -> todoId.equals(e.getId()))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }
    
    public Todo updateTodoForUser(String userId, String todoId, Todo todo) {
        User userToUpdate = userService.findOne(userId);
        
        Todo todoToUpdate = userToUpdate.getTodos().stream()
                .filter(e -> todoId.equals(e.getId()))
                .findFirst()
                .orElseThrow(RuntimeException::new);
        
        todoToUpdate.setId(todoId);
        todoToUpdate.setStatus(todo.getStatus());
        todoToUpdate.setBody(todo.getBody());
        todoToUpdate.setTitle(todo.getTitle());
        todoToUpdate.setPriority(todo.getPriority());
        todoToUpdate.setTags(todo.getTags());
        todoToUpdate.setWhen(todo.getWhen());
        
        userService.update(userId, userToUpdate);
        
        return todoToUpdate;
    }
    
    public User deleteTodoForUser(String userId, String todoId) {
        User user = userService.findOne(userId);
        user.getTodos().removeIf(e -> todoId.equals(e.getId()));
        User updatedUser = userService.update(userId, user);
        return updatedUser;
    }
}
