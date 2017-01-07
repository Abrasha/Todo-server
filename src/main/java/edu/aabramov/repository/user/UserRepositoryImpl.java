package edu.aabramov.repository.user;

import edu.aabramov.model.Todo;
import edu.aabramov.model.User;
import edu.aabramov.repository.query.ExtractFieldQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Andrii Abramov on 1/7/17.
 */
@Component
public class UserRepositoryImpl implements UserRepositoryCustom {
    
    private final MongoTemplate mongoTemplate;
    
    @Autowired
    public UserRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
    
    @Override
    public List<Todo> getUserTodos(String userId) {
        Query query = ExtractFieldQuery.create(Criteria.where("_id").is(userId), "todos");
//        Query query = Query.query(Criteria.where("_id").is(userId));
//        query.fields().include("todos").exclude("_id");
        User result = mongoTemplate.findOne(query, User.class);
        return result.getTodos();
    }
    
    public static class Todolist {
        List<Todo> todos;
        
        public List<Todo> getTodos() {
            return todos;
        }
        
        public void setTodos(List<Todo> todos) {
            this.todos = todos;
        }
    }
}
