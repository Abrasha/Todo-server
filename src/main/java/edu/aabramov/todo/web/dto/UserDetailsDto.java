package edu.aabramov.todo.web.dto;

import edu.aabramov.todo.core.model.User;

import java.util.Objects;

/**
 * @author Andrii Abramov on 11/25/16.
 */
public class UserDetailsDto {
    
    private String id;
    private String username;
    
    public UserDetailsDto(String id, String username) {
        this.id = id;
        this.username = username;
    }
    
    public UserDetailsDto(User user) {
        this(user.getId(), user.getUsername());
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDetailsDto that = (UserDetailsDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(username, that.username);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, username);
    }
    
    @Override
    public String toString() {
        return String.format("UserDetailsDto{id='%s', username='%s'}", id, username);
    }
}
