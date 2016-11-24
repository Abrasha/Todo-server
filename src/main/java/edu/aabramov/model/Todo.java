package edu.aabramov.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author Andrii Abramov on 11/24/16.
 */

public class Todo implements Serializable {
    
    private String title;
    private String body;
    private Date when;
    
    private Priority priority;
    
    private List<String> tags;
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getBody() {
        return body;
    }
    
    public void setBody(String body) {
        this.body = body;
    }
    
    public Date getWhen() {
        return when;
    }
    
    public void setWhen(Date when) {
        this.when = when;
    }
    
    public Priority getPriority() {
        return priority;
    }
    
    public void setPriority(Priority priority) {
        this.priority = priority;
    }
    
    public List<String> getTags() {
        return tags;
    }
    
    public void setTags(List<String> tags) {
        this.tags = tags;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Todo todo = (Todo) o;
        return Objects.equals(title, todo.title) &&
                Objects.equals(body, todo.body) &&
                Objects.equals(when, todo.when) &&
                priority == todo.priority;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(title, body, when, priority, tags);
    }
    
    @Override
    public String toString() {
        return String.format("Todo{title='%s', body='%s', when=%s, priority=%s, tags=%s}", title, body, when, priority, tags);
    }
}
