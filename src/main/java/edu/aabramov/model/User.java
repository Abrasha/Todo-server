package edu.aabramov.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * @author Andrii Abramov on 11/24/16.
 */
@Document(collection = "users")
public class User implements Serializable {
    
    private static final long serialVersionUID = 7526488181196658002L;
    
    @Id
    private String id;
    
    @Indexed
    private String username;
    
    private List<Todo> todos;
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
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
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(todos, user.todos) &&
                Objects.equals(username, user.username);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(todos, username);
    }
    
    @Override
    public String toString() {
        return String.format("User{id='%s', username='%s', todos=%s}", id, username, todos);
    }
}
