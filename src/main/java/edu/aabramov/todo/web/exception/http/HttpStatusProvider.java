package edu.aabramov.todo.web.exception.http;

import org.springframework.http.HttpStatus;

/**
 * @author Andrii Abramov on 12/29/16.
 */
public interface HttpStatusProvider {
    
    HttpStatus getHttpStatus();
    
}
