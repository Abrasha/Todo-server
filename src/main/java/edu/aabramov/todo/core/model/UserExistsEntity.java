package edu.aabramov.todo.core.model;

import java.util.Objects;

/**
 * @author Andrii Abramov on 1/11/17.
 */
public class UserExistsEntity {
    
    private String username;
    private boolean exists;
    
    public UserExistsEntity(String username, Boolean exists) {
        this.username = username;
        this.exists = exists;
    }
    
    public UserExistsEntity() {
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public boolean getExists() {
        return exists;
    }
    
    public void setExists(boolean exists) {
        this.exists = exists;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserExistsEntity that = (UserExistsEntity) o;
        return Objects.equals(username, that.username) &&
                Objects.equals(exists, that.exists);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(username, exists);
    }
    
    @Override
    public String toString() {
        return String.format("UserExistsDto{username='%s', exists=%s}", username, exists);
    }
}
