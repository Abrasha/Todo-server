package edu.aabramov.todo.persistence.repository.user;

import edu.aabramov.todo.core.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Andrii Abramov on 11/24/16.
 */
@Repository
public interface UserRepository extends MongoRepository<User, String>, UserRepositoryCustom {
    
    User findOneByUsername(String username);
    
    @Query(value = "{ '_id' : ?0 }", fields = "{ 'username' : 1 }")
    User getUserDetails(String userId);
    
    @Query(value = "{}", fields = "{ 'username' : 1 }")
    List<User> getAllUsersDetails();
    
}
