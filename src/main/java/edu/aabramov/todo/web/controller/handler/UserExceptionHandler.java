package edu.aabramov.todo.web.controller.handler;

import edu.aabramov.todo.web.dto.ExceptionDto;
import edu.aabramov.todo.web.exception.http.WebException;
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
    public ResponseEntity<ExceptionDto> handleWebException(WebException e) {
        return new ResponseEntity<>(new ExceptionDto(e), e.getHttpStatus());
    }
    
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionDto handleOtherExceptions(Exception e) {
        return new ExceptionDto(e);
    }
    
}
