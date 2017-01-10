package edu.aabramov.todo.web.controller;

import edu.aabramov.todo.core.model.Priority;
import edu.aabramov.todo.core.model.Status;
import edu.aabramov.todo.core.model.Todo;
import edu.aabramov.todo.service.TodoService;
import edu.aabramov.todo.web.controller.annotation.JsonRestController;
import edu.aabramov.todo.web.dto.TodoDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Andrii Abramov on 1/11/17.
 */
@JsonRestController
public class TodoProviderController extends AbstractTodoController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(TodoProviderController.class);
    
    private final TodoService todoService;
    
    @Autowired
    public TodoProviderController(TodoService todoService) {
        this.todoService = todoService;
    }
    
    @GetMapping(path = "/users/{userId}/todos")
    public List<TodoDto> getUserTodos(@PathVariable("userId") String userId) {
        LOGGER.debug("todos requested for user with id = {}", userId);
        List<Todo> result = todoService.getUserTodos(userId);
        return convertToTodoDto(result);
    }
    
    @GetMapping(path = "/users/{userId}/todos", params = "priority")
    public List<TodoDto> getUserTodosWithPriority(@PathVariable("userId") String userId, @RequestParam("priority") Priority priority) {
        LOGGER.debug("todos requested for user with id = {} with priority = {}", userId, priority);
        List<Todo> result = todoService.getTodoForUserWithPriority(userId, priority);
        return convertToTodoDto(result);
    }
    
    @GetMapping(path = "/users/{userId}/todos", params = "tag")
    public List<TodoDto> getUserTodosWithTag(@PathVariable("userId") String userId, @RequestParam("tag") String tag) {
        LOGGER.debug("todos requested for user with id = {} with tag = {}", userId, tag);
        List<Todo> result = todoService.getTodoForUserWithTag(userId, tag);
        return convertToTodoDto(result);
    }
    
    @GetMapping(path = "/users/{userId}/todos", params = "status")
    public List<TodoDto> getUserTodosWithStatus(@PathVariable("userId") String userId, @RequestParam("status") Status status) {
        LOGGER.debug("todos requested for user with id = {} with status = {}", userId, status);
        List<Todo> result = todoService.getTodoForUserWithStatus(userId, status);
        return convertToTodoDto(result);
    }
    
    @GetMapping(path = "/users/{userId}/todos/{todoId}")
    public TodoDto getTodoForUser(@PathVariable("userId") String userId, @PathVariable("todoId") String todoId) {
        LOGGER.debug("todos requested for user with id = {}", userId);
        return convertToDto(todoService.getTodoForUser(userId, todoId));
    }
    
}
