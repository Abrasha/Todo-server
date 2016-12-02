package edu.aabramov.repository.cache;

import edu.aabramov.model.User;
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
    
}
