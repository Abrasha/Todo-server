package edu.aabramov.todo.persistence.repository.user;

import edu.aabramov.todo.core.model.Todo;

import java.util.List;

/**
 * @author Andrii Abramov on 11/24/16.
 */
public interface UserRepositoryCustom {
    
    List<Todo> getUserTodos(String userId);
    
    List<String> getUsernames();
    
}
