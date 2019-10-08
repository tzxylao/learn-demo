package com.tzxylao.java8.time;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/**
 * @author laoliangliang
 * @date 2019/9/30 15:11
 */
public class TimeCal {

    public static void main(String[] args) {
        DateTime dateTime = new DateTime().withTime(15, 0, 0, 0);
        System.out.println(dateTime);

        Date now = new Date();
        DateTime d1 = new DateTime(now);
        DateTime p1 = d1.plusDays(1);
        Date tomorrow = p1.toDate();
        System.out.println(tomorrow);
        DateTime dt1 = d1.minusDays(1);
        System.out.println(dt1.toDate());

        System.out.println(dt1.getDayOfWeek() == DateTimeConstants.SUNDAY);

        DateTime d2 = p1.withTime(12, 0, 0, 0);
        long millis = d2.getMillis();
        System.out.println(millis);

        LocalDateTime time = LocalDateTime.now();
        LocalTime localTime = LocalTime.now();
        LocalTime localTime1 = localTime.withMinute(10);
        System.out.println(localTime1);
        LocalDate date = LocalDate.now();
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        System.out.println(dayOfWeek == DayOfWeek.MONDAY);


    }
}
