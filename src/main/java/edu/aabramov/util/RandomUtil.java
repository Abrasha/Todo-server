package edu.aabramov.util;

import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @author Andrii Abramov on 11/30/16.
 */
@Component
public class RandomUtil {
    
    private Random random = new Random();
    
    public int getRandomInt(int max) {
        return random.nextInt(max);
    }
    
    public int getRandomInt(int min, int max) {
        return min + random.nextInt(max - min);
    }
    
}
