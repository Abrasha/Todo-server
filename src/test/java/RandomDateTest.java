import edu.aabramov.todo.core.util.DateGenerator;
import edu.aabramov.todo.core.util.RandomUtil;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @author Andrii Abramov on 11/30/16.
 */
public class RandomDateTest {
    
    private final DateGenerator dateGenerator = new DateGenerator(new RandomUtil());
    
    @Test
    public void testRandomDateCreationNotNull() throws Exception {
        Date date = dateGenerator.getRandomDate();
        assertThat(date, is(notNullValue()));
    }
    
    @Test
    public void testRandomDateCreationYearRange() throws Exception {
        Date date = dateGenerator.getRandomDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        assertThat(calendar.get(Calendar.YEAR), greaterThan(1969));
        assertThat(calendar.get(Calendar.YEAR), lessThan(2018));
    }
    
    @Test
    public void testRandomDateCreationMonthRange() throws Exception {
        Date date = dateGenerator.getRandomDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        assertThat(calendar.get(Calendar.MONTH), greaterThan(-1));
        assertThat(calendar.get(Calendar.MONTH), lessThan(12));
    }
    
    @Test
    public void testRandomDateCreationDayRange() throws Exception {
        Date date = dateGenerator.getRandomDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        assertThat(calendar.get(Calendar.DATE), greaterThan(0));
        assertThat(calendar.get(Calendar.DATE), lessThan(31));
    }
}
