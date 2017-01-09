import com.github.javafaker.Faker;
import edu.aabramov.todo.core.model.Todo;
import edu.aabramov.todo.service.IdentifierManager;
import edu.aabramov.todo.service.debug.TodoGenerator;
import edu.aabramov.todo.core.util.DateGenerator;
import edu.aabramov.todo.core.util.RandomUtil;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @author Andrii Abramov on 11/30/16.
 */
public class TodoGeneratorTest {
    
    private final Faker faker = new Faker();
    private final RandomUtil randomUtil = new RandomUtil();
    private IdentifierManager identifierManager = new IdentifierManager();
    private final DateGenerator dateGenerator = new DateGenerator(randomUtil);
    private final TodoGenerator todoGenerator = new TodoGenerator(faker, dateGenerator, identifierManager);
    
    @Test
    public void testGenerateRandomTodo() throws Exception {
        Todo randomTodo = todoGenerator.getRandomTodo();
        assertThat(randomTodo, is(notNullValue()));
        assertThat(randomTodo.getBody(), is(notNullValue()));
        assertThat(randomTodo.getTags(), is(notNullValue()));
        assertThat(randomTodo.getTitle(), is(notNullValue()));
        assertThat(randomTodo.getPriority(), is(notNullValue()));
        assertThat(randomTodo.getWhen(), is(notNullValue()));
    }
    
    @Test
    public void testGenerateTenRandomTodos() throws Exception {
        List<Todo> randomTodos = todoGenerator.getRandomTodos(10);
        assertThat(randomTodos.size(), is(10));
        
        randomTodos.forEach(e -> assertThat(e, is(notNullValue())));
        
    }
}
