package edu.aabramov.todo.service;

import edu.aabramov.todo.core.model.Priority;
import edu.aabramov.todo.core.model.Status;
import edu.aabramov.todo.core.model.Todo;
import edu.aabramov.todo.core.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author Andrii Abramov on 11/30/16.
 */
@Service
public class TodoService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(TodoService.class);
    
    private final UserService userService;
    private final IdentifierManager identifierManager;
    
    @Autowired
    public TodoService(UserService userService, IdentifierManager identifierManager) {
        LOGGER.debug("TodoService init");
        this.identifierManager = identifierManager;
        this.userService = userService;
    }
    
    public List<Todo> addUserTodo(String id, Todo todo) {
        LOGGER.debug("adding todo: {} to user id: {}", todo, id);
        User user = userService.getUser(id);
        
        LOGGER.debug("{} user loaded", user.getId());
        todo.setId(identifierManager.hexObjectId());
        user.getTodos().add(todo);
        
        LOGGER.debug("updating user {} with {}", user.getId(), todo);
        user = userService.update(id, user);
        return userService.getUserTodos(id);
    }
    
    public List<Todo> getUserTodos(String userId) {
        LOGGER.debug("requested todos for user id = {}", userId);
        
        return userService.getUserTodos(userId);
    }
    
    public Todo getTodoForUser(String userId, String todoId) {
        User forUser = userService.getUser(userId);
        return forUser.getTodos().stream()
                .filter(e -> todoId.equals(e.getId()))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }
    
    public List<Todo> getTodoForUserWithStatus(String userId, Status status) {
        return getTodoMatching(userId, e -> e.getStatus() == status);
    }
    
    public List<Todo> getTodoForUserWithTag(String userId, String tag) {
        return getTodoMatching(userId, e -> e.getTags().contains(tag));
    }
    
    public List<Todo> getTodoForUserWithPriority(String userId, Priority priority) {
        return getTodoMatching(userId, e -> e.getPriority() == priority);
    }
    
    public Todo updateTodoForUser(String userId, String todoId, Todo todo) {
        
        User userToUpdate = userService.getUser(userId);
        // todo search with edu.aabramov.todo.core.util.CollectionFinder
        Todo todoToUpdate = userToUpdate.getTodos().stream()
                .filter(e -> todoId.equals(e.getId()))
                .findFirst()
                .orElseThrow(RuntimeException::new);
        
        mergeTodo(todoId, todo, todoToUpdate);
        
        userService.update(userId, userToUpdate);
        
        return todoToUpdate;
    }
    
    public List<Todo> deleteTodoForUser(String userId, String todoId) {
        User user = userService.getUser(userId);
        user.getTodos().removeIf(e -> todoId.equals(e.getId()));
        User updatedUser = userService.update(userId, user);
        return updatedUser.getTodos();
    }
    
    private List<Todo> getTodoMatching(String userId, Predicate<Todo> matcher) {
        User forUser = userService.getUser(userId);
        return forUser.getTodos().stream()
                .filter(matcher)
                .collect(Collectors.toList());
    }
    
    private void mergeTodo(String todoId, Todo todo, Todo todoToUpdate) {
        todoToUpdate.setId(todoId);
        todoToUpdate.setStatus(todo.getStatus());
        todoToUpdate.setBody(todo.getBody());
        todoToUpdate.setTitle(todo.getTitle());
        todoToUpdate.setPriority(todo.getPriority());
        todoToUpdate.setTags(todo.getTags());
        todoToUpdate.setWhen(todo.getWhen());
    }
}
