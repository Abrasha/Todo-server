package edu.aabramov.todo.service.cache.keys;

/**
 * @author Andrii Abramov on 1/9/17.
 */
public interface KeyProvider<T> {
    
    String getKey(T entity);
    
}
