package edu.aabramov.model;

import java.util.Random;

/**
 * @author Andrii Abramov on 11/24/16.
 */
public enum Status {
    
    DEFAULT, DONE, SUSPENDED, IN_PROGRESS;
    
    private static final Random random = new Random();
    
    public static Status getRandom() {
        return values()[random.nextInt(values().length)];
    }
    
}
