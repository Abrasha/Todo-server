package edu.aabramov.model;

import java.util.Random;

/**
 * @author Andrii Abramov on 11/24/16.
 */
public enum Priority {
    
    DEFAULT, HIGH, MEDIUM, LOW;
    
    private static final Random random = new Random();
    
    public static Priority getRandom() {
        return values()[random.nextInt(values().length)];
    }
    
}
