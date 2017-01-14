package edu.aabramov.todo.web.controller.rest;

import edu.aabramov.todo.core.model.UserExistsEntity;
import edu.aabramov.todo.web.dto.UserExistsDto;

/**
 * @author Andrii Abramov on 1/11/17.
 */
public abstract class AbstractUserExistsController extends MappingController<UserExistsEntity, UserExistsDto> {
    
    @Override
    protected Class<UserExistsDto> getDtoClass() {
        return UserExistsDto.class;
    }
    
    @Override
    protected Class<UserExistsEntity> getEntityClass() {
        return UserExistsEntity.class;
    }
}
