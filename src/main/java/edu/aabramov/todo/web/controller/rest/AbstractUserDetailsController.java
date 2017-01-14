package edu.aabramov.todo.web.controller.rest;

import edu.aabramov.todo.core.model.UserDetails;
import edu.aabramov.todo.web.dto.UserDetailsDto;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Andrii Abramov on 1/11/17.
 */
public abstract class AbstractUserDetailsController extends MappingController<UserDetails, UserDetailsDto> {
    
    protected List<UserDetailsDto> convertToUserDetailsDto(List<UserDetails> todos) {
        List<UserDetailsDto> result = todos.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        
        return result;
    }
    
    @Override
    protected Class<UserDetailsDto> getDtoClass() {
        return UserDetailsDto.class;
    }
    
    @Override
    protected Class<UserDetails> getEntityClass() {
        return UserDetails.class;
    }
}
