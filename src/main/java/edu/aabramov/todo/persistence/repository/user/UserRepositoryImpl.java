package edu.aabramov.todo.persistence.repository.user;

import edu.aabramov.todo.core.model.Todo;
import edu.aabramov.todo.core.model.User;
import edu.aabramov.todo.persistence.repository.query.ExtractFieldQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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
        User result = mongoTemplate.findOne(query, User.class);
        return result.getTodos();
    }
    
    @Override
    public List<String> getUsernames() {
        Query query = ExtractFieldQuery.create("username");
        List<User> result = mongoTemplate.find(query, User.class);
        return result.stream()
                .map(User::getUsername)
                .collect(Collectors.toList());
    }
    
}
