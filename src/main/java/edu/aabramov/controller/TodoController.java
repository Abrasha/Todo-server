package edu.aabramov.controller;

import edu.aabramov.model.Priority;
import edu.aabramov.model.Status;
import edu.aabramov.model.Todo;
import edu.aabramov.model.User;
import edu.aabramov.service.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

/**
 * @author Andrii Abramov on 11/30/16.
 */
@RestController
public class TodoController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(TodoController.class);
    
    private final TodoService todoService;
    
    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }
    
    @GetMapping(path = "/users/{userId}/todos", produces = APPLICATION_JSON_UTF8_VALUE)
    public List<Todo> getUserTodos(@PathVariable("userId") String userId) {
        LOGGER.debug("todos requested for user with id = {}", userId);
        return todoService.getUserTodos(userId);
    }
    
    @GetMapping(path = "/users/{userId}/todos", produces = APPLICATION_JSON_UTF8_VALUE, params = "priority")
    public List<Todo> getUserTodosWithPriority(@PathVariable("userId") String userId, @RequestParam("priority") Priority priority) {
        LOGGER.debug("todos requested for user with id = {} with priority = {}", userId, priority);
        return todoService.getTodoForUserWithPriority(userId, priority);
    }
    
    @GetMapping(path = "/users/{userId}/todos", produces = APPLICATION_JSON_UTF8_VALUE, params = "tag")
    public List<Todo> getUserTodosWithTag(@PathVariable("userId") String userId, @RequestParam("tag") String tag) {
        LOGGER.debug("todos requested for user with id = {} with tag = {}", userId, tag);
        return todoService.getTodoForUserWithTag(userId, tag);
    }
    
    @GetMapping(path = "/users/{userId}/todos", produces = APPLICATION_JSON_UTF8_VALUE, params = "status")
    public List<Todo> getUserTodosWithStatus(@PathVariable("userId") String userId, @RequestParam("status") Status status) {
        LOGGER.debug("todos requested for user with id = {} with status = {}", userId, status);
        return todoService.getTodoForUserWithStatus(userId, status);
    }
    
    @GetMapping(path = "/users/{userId}/todos/{todoId}", produces = APPLICATION_JSON_UTF8_VALUE)
    public Todo getTodoForUser(@PathVariable("userId") String userId, @PathVariable("todoId") String todoId) {
        LOGGER.debug("todos requested for user with id = {}", userId);
        return todoService.getTodoForUser(userId, todoId);
    }
    
    @PatchMapping(path = "/users/{userId}/todos/{todoId}", consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
    public Todo updateTodoForUser(@PathVariable("userId") String userId, @PathVariable("todoId") String todoId, @RequestBody Todo todo) {
        LOGGER.debug("todos requested for user with id = {}", userId);
        return todoService.updateTodoForUser(userId, todoId, todo);
    }
    
    @DeleteMapping(path = "/users/{userId}/todos/{todoId}", consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
    public List<Todo> deleteTodoForUser(@PathVariable("userId") String userId, @PathVariable("todoId") String todoId) {
        LOGGER.debug("delete todo with id = for user with id = {}", todoId, userId);
        return todoService.deleteTodoForUser(userId, todoId);
    }
    
    @PostMapping(path = "/users/{userId}/todos", consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
    public User addUserTodo(@PathVariable("userId") String userId, @RequestBody Todo todo) {
        LOGGER.debug("adding todo = {} to userId = {}", todo, userId);
        return todoService.addUserTodo(userId, todo);
    }
    
    @PostMapping(value = "/users/{userId}/todos/generate", produces = APPLICATION_JSON_UTF8_VALUE)
    public List<Todo> insertRandomTodos(@RequestParam("count") int count, @PathVariable("userId") String userId) {
        LOGGER.debug("inserting {} todos for user id: {}", count, userId);
        return todoService.insertRandomTodos(count, userId);
    }
    
}
