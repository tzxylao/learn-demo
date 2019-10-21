package com.tzxylao.java8.time;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author laoliangliang
 * @date 2019/10/14 17:21
 */
public class TimerLearn {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static void main(String[] args) {
//        timerTest();
        print("1:");
        ScheduledExecutorService service = new ScheduledThreadPoolExecutor(1);
        for (int i = 0; i < 2; i++) {
            service.schedule(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Thread name : " + Thread.currentThread().getName());
                    print("2:");

                }
            }, 3, TimeUnit.SECONDS);
        }
        print("3:");
        service.shutdown();
        System.out.println(Integer.bitCount(7));
    }

    private static void timerTest() {

        print("1:");
        LocalDateTime localDateTime;
        String format;
        Timer timer = new Timer();
        for (int i = 0; i < 2; i++) {
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Thread name : "+ Thread.currentThread().getName());
                    print("2:");
                }
            }, 3000);
        }

        localDateTime = LocalDateTime.now();
        format = localDateTime.format(formatter);
        System.out.println("3:"+format);
    }

    private static void print(String s) {
        LocalDateTime localDateTime = LocalDateTime.now();
        String format = localDateTime.format(formatter);
        System.out.println(s + format);
    }
}
