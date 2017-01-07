package edu.aabramov.controller.debug;

import edu.aabramov.configuration.AppProfiles;
import edu.aabramov.controller.annotation.JsonRestController;
import edu.aabramov.model.Todo;
import edu.aabramov.service.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

/**
 * @author Andrii Abramov on 1/7/17.
 */
@JsonRestController
@Profile(AppProfiles.TEST)
public class DebugTodoController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(DebugTodoController.class);
    
    private final TodoService todoService;
    
    @Autowired
    public DebugTodoController(TodoService todoService) {
        this.todoService = todoService;
    }
    
    @PostMapping(value = "/users/{userId}/todos/generate")
    public List<Todo> insertRandomTodos(@RequestParam("count") int count, @PathVariable("userId") String userId) {
        LOGGER.debug("inserting {} todos for user id: {}", count, userId);
        return todoService.insertRandomTodos(count, userId);
    }
    
}
