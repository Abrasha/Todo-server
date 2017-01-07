package edu.aabramov.service.debug;

import edu.aabramov.configuration.AppProfiles;
import edu.aabramov.model.User;
import edu.aabramov.model.UserDetails;
import edu.aabramov.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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
    
    @Autowired
    public DebugUserService(UserService userService, UserGenerator userGenerator) {
        LOGGER.debug("UserService init");
        this.userService = userService;
        this.userGenerator = userGenerator;
    }
    
    public List<User> insert(List<User> users) {
        return users.stream()
                .filter(Objects::nonNull)
                .map(user -> userService.insert(user.getUsername(), user.getPassword()))
                .collect(toList());
        
    }
    
    public List<UserDetails> insertRandomUsers(int count) {
        return insert(userGenerator.getRandomUsers(count))
                .stream()
                .map(UserDetails::new)
                .collect(toList());
    }
    
}
