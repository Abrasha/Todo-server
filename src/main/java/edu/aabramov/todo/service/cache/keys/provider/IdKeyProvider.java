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
    
    @Override
    public String getKey(User entity) {
        Assert.notNull(entity);
        return "user:id:" + entity.getId();
    }
    
    public String getKey(String id) {
        Assert.notNull(id);
        return "user:id:" + id;
    }
}
