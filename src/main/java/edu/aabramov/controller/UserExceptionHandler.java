package edu.aabramov.controller;

import edu.aabramov.dto.ExceptionDto;
import edu.aabramov.exception.http.WebException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Andrii Abramov on 12/29/16.
 */
@RestControllerAdvice
public class UserExceptionHandler {
    
    @ExceptionHandler(WebException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ExceptionDto> handleException(WebException e) {
        return new ResponseEntity<>(new ExceptionDto(e), e.getHttpStatus());
    }
    
}
