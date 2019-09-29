package com.tzxylao.java8.date;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author laoliangliang
 * @date 2019/9/6 15:24
 */
public class DateLearn {



    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println(Instant.now().atZone(ZoneId.systemDefault()));
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);
        String format = localDate.format(formatterDate);
        System.out.println(format);
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);
        System.out.println(localDateTime.format(formatter));
        LocalDateTime dateTime = LocalDateTime.parse("2019-06-01 12:00:10", formatter);
        System.out.println(dateTime);
    }
}
