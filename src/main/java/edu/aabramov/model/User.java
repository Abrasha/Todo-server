package edu.aabramov.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * @author Andrii Abramov on 11/24/16.
 */
@Document
public class User implements Serializable {
    
    @Id
    private String id;
    private String username;
    private String password;
    
    private List<Todo> todos;
    
    public String getId() {
        return id;
    }
    
    public List<Todo> getTodos() {
        return todos;
    }
    
    public void setTodos(List<Todo> todos) {
        this.todos = todos;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(todos, user.todos) &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(todos, username, password);
    }
    
    @Override
    public String toString() {
        return String.format("User{id='%s', todos=%s, username='%s', password='%s'}", id, todos, username, password);
    }
}
