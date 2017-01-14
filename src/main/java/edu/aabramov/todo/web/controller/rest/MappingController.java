package edu.aabramov.todo.web.controller.rest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Andrii Abramov on 1/10/17.
 */
public abstract class MappingController<Entity, Dto> {
    
    @Autowired
    private ModelMapper modelMapper;
    
    protected Entity convertToEntity(Dto dto) {
        return modelMapper.map(dto, getEntityClass());
    }
    
    protected Dto convertToDto(Entity entity) {
        return modelMapper.map(entity, getDtoClass());
    }
    
    protected abstract Class<Dto> getDtoClass();
    
    protected abstract Class<Entity> getEntityClass();
    
}
