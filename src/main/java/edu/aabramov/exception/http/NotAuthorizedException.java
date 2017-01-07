package edu.aabramov.exception.http;

import org.springframework.http.HttpStatus;

/**
 * @author Andrii Abramov on 12/29/16.
 */
public class NotAuthorizedException extends WebException {
    
    public NotAuthorizedException(String message) {
        super(message, HttpStatus.FORBIDDEN);
    }
}
