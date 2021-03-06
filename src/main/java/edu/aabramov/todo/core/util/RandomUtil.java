package edu.aabramov.todo.core.util;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @author Andrii Abramov on 11/30/16.
 */
@Component
@Profile(AppProfiles.TEST)
public class RandomUtil {
    
    private final Random random = new Random();
    
    public int randomInt(int max) {
        return random.nextInt(max);
    }
    
    public int randomInt(int min, int max) {
        return min + random.nextInt(max - min);
    }
    
}
