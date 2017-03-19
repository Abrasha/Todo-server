package edu.aabramov.todo.web.controller.rest.path;

/**
 * @author Andrii Abramov on 1/14/17.
 */
public final class TodoPaths {
    
    private TodoPaths() {
    }
    
    public static final String USER_TODOS = UserPaths.USER_ID + "/todos";
    public static final String USER_TODO = USER_TODOS + "/{todoId}";
    
}
