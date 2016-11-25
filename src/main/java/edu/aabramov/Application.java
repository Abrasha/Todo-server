package edu.aabramov;

import edu.aabramov.model.Priority;
import edu.aabramov.model.Todo;
import edu.aabramov.model.User;
import edu.aabramov.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;

/**
 * @author Andrii Abramov on 11/24/16.
 */
@SpringBootApplication
@EnableMongoRepositories
@EnableRedisRepositories
public class Application implements CommandLineRunner {
    
    @Autowired
    private UserRepository userRepository;
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @Override
    public void run(String... args) throws Exception {
        
//        userRepository.deleteAll();
//
//        for (int i = 0; i < 5; i++) {
//            final int index = i;
//            List<Todo> todos = IntStream.range(1, 5).mapToObj(n -> getTodo(n * index)).collect(Collectors.toList());
//            User user = new User();
//            user.setUsername("User_" + index);
//            user.setTodos(todos);
//
//            System.err.println(user);
//
//            user = userRepository.save(user);
//
//            System.err.println(user);
//        }
//
//
//        System.err.println(userRepository.findAll());
    }
    
    private Todo getTodo(int number) {
        Todo todo = new Todo();
        todo.setBody("Body #" + number);
        todo.setWhen(new Date());
        todo.setPriority(Priority.DEFAULT);
        todo.setTitle("Title #" + number);
        
        todo.setTags(asList("Tag " + number, "Tag " + number * 2));
        
        return todo;
    }
}
