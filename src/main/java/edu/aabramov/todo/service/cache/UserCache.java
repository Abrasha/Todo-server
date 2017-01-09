package edu.aabramov.todo.service.cache;

import edu.aabramov.todo.core.model.User;
import edu.aabramov.todo.service.cache.keys.provider.IdKeyProvider;
import edu.aabramov.todo.service.cache.keys.provider.UsernameKeyProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author Andrii Abramov on 12/2/16.
 */
@Component
public class UserCache extends EntityCache<User> {
    
    private static final String USER_REDIS_KEY = User.class.getSimpleName();
    
    private final UsernameKeyProvider usernameKeyProvider;
    
    private final IdKeyProvider idKeyProvider;
    
    @Autowired
    public UserCache(UsernameKeyProvider usernameKeyProvider, IdKeyProvider idKeyProvider) {
        this.usernameKeyProvider = usernameKeyProvider;
        this.idKeyProvider = idKeyProvider;
    }
    
    
    @Override
    public String getHashKey() {
        return USER_REDIS_KEY;
    }
    
    public Optional<User> getByUsername(String username) {
        return Optional.ofNullable(get(usernameKeyProvider.getKey(username)));
    }
    
    public Optional<User> getById(String id) {
        return Optional.ofNullable(get(idKeyProvider.getKey(id)));
    }
    
    /**
     * @param user should not be {@literal null}
     */
    private void putWithUsername(User user) {
        put(usernameKeyProvider.getKey(user.getUsername()), user);
    }
    
    /**
     * @param user should not be {@literal null}
     */
    private void putWithId(User user) {
        put(idKeyProvider.getKey(user.getId()), user);
    }
    
    public void refreshUserInCache(User user) {
        if (user == null) {
            return;
        }
        putWithUsername(user);
        putWithId(user);
    }
    
    
}
