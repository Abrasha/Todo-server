package edu.aabramov.todo.web.dto;

import java.util.Objects;

/**
 * @author Andrii Abramov on 12/29/16.
 */
public class ExceptionDto {
    
    private final String message;
    
    public ExceptionDto(String message) {
        this.message = message;
    }
    
    public ExceptionDto(Throwable e) {
        this.message = e != null ? e.getMessage() : "Error occurred";
    }
    
    public String getMessage() {
        return message;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExceptionDto that = (ExceptionDto) o;
        return Objects.equals(message, that.message);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(message);
    }
    
    @Override
    public String toString() {
        return String.format("ExceptionDto{message='%s'}", message);
    }
}
