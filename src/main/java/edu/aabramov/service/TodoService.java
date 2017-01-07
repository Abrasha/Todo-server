package edu.aabramov.service;

import edu.aabramov.model.Priority;
import edu.aabramov.model.Status;
import edu.aabramov.model.Todo;
import edu.aabramov.model.User;
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
        User user = userService.getUser(userId);
        
        LOGGER.debug("{} user loaded", user.getId());
        user.getTodos().addAll(todos);
        
        LOGGER.debug("updating user {} with {}", user.getId(), user);
        User updatedUser = userService.update(userId, user);
        return updatedUser.getTodos();
    }
    
    public User addUserTodo(String id, Todo todo) {
        LOGGER.debug("adding todo: {} to user id: {}", todo, id);
        User user = userService.getUser(id);
        
        LOGGER.debug("{} user loaded", user.getId());
        todo.setId(identifierManager.hexObjectId());
        user.getTodos().add(todo);
        
        LOGGER.debug("updating user {} with {}", user.getId(), todo);
        user = userService.update(id, user);
        return user;
    }
    
    public List<Todo> getUserTodos(String userId) {
        LOGGER.debug("requested todos for user id = {}", userId);
//        User user = userService.getUser(userId);

//        LOGGER.debug("got user {}", userId);
//        return user.getTodos();
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
