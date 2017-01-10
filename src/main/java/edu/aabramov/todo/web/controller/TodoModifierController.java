package edu.aabramov.todo.web.controller;

import edu.aabramov.todo.core.model.Todo;
import edu.aabramov.todo.service.TodoService;
import edu.aabramov.todo.web.controller.annotation.JsonRestController;
import edu.aabramov.todo.web.dto.TodoDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Andrii Abramov on 1/11/17.
 */
@JsonRestController
public class TodoModifierController extends AbstractTodoController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(TodoModifierController.class);
    
    private final TodoService todoService;
    
    @Autowired
    public TodoModifierController(TodoService todoService) {
        this.todoService = todoService;
    }
    
    @PatchMapping(path = "/users/{userId}/todos/{todoId}")
    public Todo updateTodoForUser(@PathVariable("userId") String userId, @PathVariable("todoId") String todoId, @RequestBody TodoDto todo) {
        LOGGER.debug("todos requested for user with id = {}", userId);
        Todo addedTodo = convertToEntity(todo);
        return todoService.updateTodoForUser(userId, todoId, addedTodo);
    }
    
    @DeleteMapping(path = "/users/{userId}/todos/{todoId}")
    public List<Todo> deleteTodoForUser(@PathVariable("userId") String userId, @PathVariable("todoId") String todoId) {
        LOGGER.debug("delete todo with id = for user with id = {}", todoId, userId);
        return todoService.deleteTodoForUser(userId, todoId);
    }
    
    @PostMapping(path = "/users/{userId}/todos")
    public List<TodoDto> addUserTodo(@PathVariable("userId") String userId, @RequestBody TodoDto todo) {
        LOGGER.debug("adding todo = {} to userId = {}", todo, userId);
        Todo updatedTodo = convertToEntity(todo);
        List<Todo> result = todoService.addUserTodo(userId, updatedTodo);
        return convertToTodoDto(result);
    }
    
}
