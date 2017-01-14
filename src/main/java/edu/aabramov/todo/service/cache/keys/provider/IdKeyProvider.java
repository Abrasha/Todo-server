package edu.aabramov.todo.service.cache.keys.provider;

import edu.aabramov.todo.core.model.User;
import edu.aabramov.todo.service.cache.keys.KeyProvider;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * @author Andrii Abramov on 1/9/17.
 */
@Component
public class IdKeyProvider implements KeyProvider<User> {
    
    private String CACHE_PREFIX_USER_ID = "user:id:";
    
    @Override
    public String getKey(User entity) {
        Assert.notNull(entity);
        return CACHE_PREFIX_USER_ID + entity.getId();
    }
    
    public String getKey(String id) {
        Assert.notNull(id);
        return CACHE_PREFIX_USER_ID + id;
    }
}
