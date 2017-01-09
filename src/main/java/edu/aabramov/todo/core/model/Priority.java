package edu.aabramov.todo.core.model;

import java.util.Random;

/**
 * @author Andrii Abramov on 11/24/16.
 */
public enum Priority {
    
    DEFAULT, LOW, MEDIUM, HIGH;
    
    private static final Random random = new Random();
    
    public static Priority getRandom() {
        return values()[random.nextInt(values().length)];
    }
    
}
