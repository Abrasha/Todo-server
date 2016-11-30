package edu.aabramov.util;

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
        
        int year = randomUtil.getRandomInt(1970, 48); // 1970 -> 2017
        int month = randomUtil.getRandomInt(12);
        
        int day;
        if (month != Calendar.FEBRUARY) {
            day = randomUtil.getRandomInt(30);
        } else {
            day = randomUtil.getRandomInt(29);
        }
        
        int hour = randomUtil.getRandomInt(24);
        int minute = randomUtil.getRandomInt(60);
        calendar.set(year, month, day, hour, minute);
        
        return calendar.getTime();
    }
    
}
