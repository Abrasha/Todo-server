package edu.aabramov.repository.user;

import edu.aabramov.model.Todo;

import java.util.List;

/**
 * @author Andrii Abramov on 11/24/16.
 */
public interface UserRepositoryCustom {
    
    List<Todo> getUserTodos(String userId);
    
}
