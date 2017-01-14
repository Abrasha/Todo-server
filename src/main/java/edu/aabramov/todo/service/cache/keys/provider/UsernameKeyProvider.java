package edu.aabramov.todo.service.cache.keys.provider;

import edu.aabramov.todo.core.model.User;
import edu.aabramov.todo.service.cache.keys.KeyProvider;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * @author Andrii Abramov on 1/9/17.
 */
@Component
public class UsernameKeyProvider implements KeyProvider<User> {
    
    private String CACHE_PREFIX_USERNAME = "user:username:";
    
    @Override
    public String getKey(User entity) {
        Assert.notNull(entity);
        return CACHE_PREFIX_USERNAME + entity.getUsername();
    }
    
    public String getKey(String username) {
        Assert.notNull(username);
        return CACHE_PREFIX_USERNAME + username;
    }
    
}
