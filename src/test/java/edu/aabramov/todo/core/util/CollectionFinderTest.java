package edu.aabramov.todo.core.util;

import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static edu.aabramov.todo.core.util.CollectionFinder.find;
import static java.util.Arrays.asList;
import static org.junit.Assert.*;

/**
 * @author Andrii Abramov on 1/10/17.
 */
public class CollectionFinderTest {
    
    @Test
    public void testNullCollection() throws Exception {
        assertFalse(find(null, e -> true).isPresent());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testNullPredicate() throws Exception {
        List<Integer> integers = asList(1, 2, 3, 4);
        find(integers, null);
    }
    
    @Test
    public void testBothNulls() throws Exception {
        assertFalse(find(null, null).isPresent());
    }
    
    @Test
    public void testEmptyCollection() throws Exception {
        List<Integer> integers = Collections.emptyList();
        assertFalse(find(integers, e -> true).isPresent());
    }
    
    @Test
    public void testMatchingSingleItem() throws Exception {
        List<Integer> integers = Collections.singletonList(1);
        assertTrue(find(integers, isOdd()).isPresent());
    }
    
    @Test
    public void testNonMatchingSingleItem() throws Exception {
        List<Integer> integers = Collections.singletonList(1);
        assertFalse(find(integers, isEven()).isPresent());
    }
    
    @Test
    public void testNoMatch() throws Exception {
        List<Integer> integers = asList(1, 2, 3, 4, 5);
        assertFalse(find(integers, isNegative()).isPresent());
    }
    
    @Test
    public void testSingleMatchableItem() throws Exception {
        List<Integer> integers = asList(1, 2, 3, 4, 5);
        Optional<Integer> res = find(integers, e -> e == 3);
        assertTrue(res.isPresent());
        assertEquals(3, res.get().intValue());
    }
    
    @Test
    public void testMatching() throws Exception {
        List<Integer> integers = asList(1, 2, 3, 4, 5);
        Optional<Integer> res = find(integers, isOdd());
        assertTrue(res.isPresent());
        assertEquals(1, res.get().intValue());
    }
    
    private Predicate<Integer> isEven() {
        return e -> e % 2 == 0;
    }
    
    private Predicate<Integer> isOdd() {
        return isEven().negate();
    }
    
    private Predicate<Integer> isNegative() {
        return e -> e < 0;
    }
    
}