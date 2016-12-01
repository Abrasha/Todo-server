package edu.aabramov.repository;

import edu.aabramov.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Andrii Abramov on 11/24/16.
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {
    
    User findOneByUsername(String username);
    
}
