package edu.aabramov.todo.web.controller;

import edu.aabramov.todo.core.model.Todo;
import edu.aabramov.todo.web.dto.TodoDto;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Andrii Abramov on 1/11/17.
 */
public abstract class AbstractTodoController extends MappingController<Todo, TodoDto> {
    
    protected List<TodoDto> convertToTodoDto(List<Todo> todos) {
        List<TodoDto> result = todos.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return result;
    }
    
    @Override
    protected Class<TodoDto> getDtoClass() {
        return TodoDto.class;
    }
    
    @Override
    protected Class<Todo> getEntityClass() {
        return Todo.class;
    }
}
