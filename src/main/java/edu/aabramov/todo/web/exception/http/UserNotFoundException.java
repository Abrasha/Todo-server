package edu.aabramov.todo.web.exception.http;

import org.springframework.http.HttpStatus;

/**
 * @author Andrii Abramov on 12/29/16.
 */
public class UserNotFoundException extends WebException {
    
    public UserNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
