package edu.aabramov.exception.http;

import org.springframework.http.HttpStatus;

/**
 * @author Andrii Abramov on 12/29/16.
 */
public abstract class WebException extends RuntimeException implements HttpStatusProvider {
    
    private final HttpStatus httpStatus;
    
    public WebException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
    
    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
