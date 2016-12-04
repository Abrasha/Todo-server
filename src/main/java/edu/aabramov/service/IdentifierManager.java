package edu.aabramov.service;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

/**
 * @author Andrii Abramov on 12/4/16.
 */
@Component
public class IdentifierManager {
    
    public String hexObjectId() {
        return new ObjectId().toHexString();
    }
    
}
