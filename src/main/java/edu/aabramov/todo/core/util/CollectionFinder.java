package edu.aabramov.todo.core.util;

import org.springframework.util.Assert;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * @author Andrii Abramov on 1/10/17.
 */
public final class CollectionFinder {
    
    public static <T> Optional<T> find(Collection<T> items, Predicate<T> filter) {
        if (items == null) {
            return Optional.empty();
        }
        Assert.notNull(filter);
        return items.stream().filter(filter).findFirst();
    }
    
}
