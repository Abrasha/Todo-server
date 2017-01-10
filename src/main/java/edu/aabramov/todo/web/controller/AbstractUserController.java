package edu.aabramov.todo.web.controller;

import edu.aabramov.todo.core.model.User;
import edu.aabramov.todo.web.dto.UserDto;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Andrii Abramov on 1/11/17.
 */
public abstract class AbstractUserController extends MappingController<User, UserDto> {
    
    protected List<UserDto> convertToUserDto(List<User> todos) {
        List<UserDto> result = todos.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        
        return result;
    }
    
    @Override
    protected Class<UserDto> getDtoClass() {
        return UserDto.class;
    }
    
    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }
}
