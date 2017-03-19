package edu.aabramov.todo.web.controller.rest.path;

/**
 * @author Andrii Abramov on 1/14/17.
 */
public final class UserPaths {
    
    private UserPaths() {
    }
    
    public static final String USERS_ROOT = "/users";
    public static final String USER_ID = USERS_ROOT + "/{userId}";
    
}
