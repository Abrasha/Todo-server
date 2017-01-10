package edu.aabramov.todo.persistence.repository.query;

import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;

/**
 * @author Andrii Abramov on 1/7/17.
 */
public class ExtractFieldQuery {
    
    public static final String FIELD_ID = "_id";
    
    public static Query create(String fieldName) {
        return create(fieldName, true);
    }
    
    public static Query create(String fieldName, boolean excludeId) {
        Query query = new Query();
        query.fields().include(fieldName);
        
        if (excludeId) {
            excludeIdField(query);
        }
        
        return query;
    }
    
    public static Query create(CriteriaDefinition filter, String fieldName) {
        return create(filter, fieldName, true);
    }
    
    public static Query create(CriteriaDefinition filter, String fieldName, boolean excludeId) {
        Query query = new Query(filter);
        query.fields().include(fieldName);
        
        if (excludeId) {
            excludeIdField(query);
        }
        
        return query;
    }
    
    private static void excludeIdField(Query query) {
        query.fields().exclude(FIELD_ID);
    }
    
}
