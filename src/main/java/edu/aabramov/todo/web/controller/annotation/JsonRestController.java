package edu.aabramov.todo.web.controller.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Andrii Abramov on 1/7/17.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@RestController
@RequestMapping
public @interface JsonRestController {
    
    @AliasFor(annotation = RequestMapping.class, attribute = "consumes")
    String[] consumes() default MediaType.APPLICATION_JSON_UTF8_VALUE;
    
    @AliasFor(annotation = RequestMapping.class, attribute = "produces")
    String[] produces() default MediaType.APPLICATION_JSON_UTF8_VALUE;
    
}
