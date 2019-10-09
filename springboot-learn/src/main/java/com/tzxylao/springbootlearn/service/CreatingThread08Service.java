package com.tzxylao.springbootlearn.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

@Service
public class CreatingThread08Service {

    @Async
    public void call(CountDownLatch countDownLatch) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " is running");
        countDownLatch.countDown();
        System.out.println(Thread.currentThread().getName() + " is over");

    }
}
