package edu.aabramov.todo.service.debug;

import edu.aabramov.todo.core.model.User;
import edu.aabramov.todo.core.model.UserDetails;
import edu.aabramov.todo.core.util.AppProfiles;
import edu.aabramov.todo.persistence.repository.user.UserRepository;
import edu.aabramov.todo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * @author Andrii Abramov on 11/25/16.
 */
@Service
@Profile(AppProfiles.TEST)
public class DebugUserService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(DebugUserService.class);
    
    private final UserService userService;
    private final UserGenerator userGenerator;
    private final UserRepository userRepository;
    
    @Autowired
    public DebugUserService(UserService userService, UserGenerator userGenerator, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.userGenerator = userGenerator;
        LOGGER.debug("UserService init");
    }
    
    public List<UserDetails> insert(List<User> users) {
        List<UserDetails> result = users.stream()
                .filter(Objects::nonNull)
                .map(user -> userService.insert(user.getUsername(), user.getPassword()))
                .map(UserDetails::new)
                .collect(toList());
        return result;
        
    }
    
    public List<UserDetails> insertRandomUsers(int count) {
        List<UserDetails> result = insert(userGenerator.generateRandomUsers(count));
        return result;
    }
    
    public List<String> getUsernames() {
        return userRepository.getUsernames();
    }
    
    public List<UserDetails> getAllUsers() {
        List<User> allUsers = userRepository.getAllUsersDetails();
        List<UserDetails> result = allUsers.stream()
                .map(UserDetails::new)
                .collect(Collectors.toList());
        return result;
    }
    
}
