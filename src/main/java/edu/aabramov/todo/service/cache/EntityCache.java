package edu.aabramov.todo.service.cache;

import org.springframework.data.redis.core.HashOperations;

import javax.annotation.Resource;

/**
 * @author Andrii Abramov on 12/2/16.
 */
public abstract class EntityCache<T> {
    
    @Resource(name = "redisTemplate")
    private HashOperations<String, String, T> hashOperations;
    
    public boolean hasKey(String key) {
        return hashOperations.hasKey(getHashKey(), key);
    }
    
    public void put(String hashKey, T value) {
        hashOperations.put(hashKey, getHashKey(), value);
    }
    
    public T get(String hashKey) {
        return hashOperations.get(hashKey, getHashKey());
    }
    
    public abstract String getHashKey();
    
}
