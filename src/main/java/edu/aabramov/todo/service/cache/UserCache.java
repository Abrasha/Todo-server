package edu.aabramov.todo.service.cache;

import edu.aabramov.todo.core.model.User;
import org.springframework.stereotype.Component;

/**
 * @author Andrii Abramov on 12/2/16.
 */
@Component
public class UserCache extends EntityCache<User> {
    
    private static final String USER_REDIS_KEY = "User";
    
    @Override
    public String getHashKey() {
        return USER_REDIS_KEY;
    }
    
    public String getIdKey(String userId) {
        return "user:id:" + userId;
    }
    
    public String getUsernameKey(String username) {
        return "user:username:" + username;
    }
    
    public void putWithUsername(String username, User user) {
        put(getUsernameKey(username), user);
    }
    
    public void putWithId(String userId, User user) {
        put(getIdKey(userId), user);
    }
    
    public void refreshUserInCache(User user) {
        if (user == null) {
            return;
        }
        putWithUsername(user.getUsername(), user);
        putWithId(user.getId(), user);
    }
    
    
}
