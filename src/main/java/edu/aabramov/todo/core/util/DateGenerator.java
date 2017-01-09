package edu.aabramov.todo.core.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

/**
 * @author Andrii Abramov on 11/30/16.
 */
@Component
public class DateGenerator {
    
    private final RandomUtil randomUtil;
    
    @Autowired
    public DateGenerator(RandomUtil randomUtil) {
        this.randomUtil = randomUtil;
    }
    
    public Date getRandomDate() {
        Calendar calendar = Calendar.getInstance();
        
        int year = randomUtil.randomInt(1970, 2017); // 1970 -> 2017
        int month = randomUtil.randomInt(12);
        
        int day;
        if (month != Calendar.FEBRUARY) {
            day = randomUtil.randomInt(30);
        } else {
            day = randomUtil.randomInt(29);
        }
        
        int hour = randomUtil.randomInt(24);
        int minute = randomUtil.randomInt(60);
        calendar.set(year, month, day, hour, minute);
        
        return calendar.getTime();
    }
    
}
