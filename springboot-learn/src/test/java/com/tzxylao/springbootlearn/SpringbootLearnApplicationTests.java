package com.tzxylao.springbootlearn;

import com.tzxylao.springbootlearn.service.CreatingThread08Service;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StopWatch;

import java.util.concurrent.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootLearnApplicationTests {

    @Autowired
    private CreatingThread08Service creatingThread08Service;

    private int count = 100;

    private CountDownLatch countDownLatch = new CountDownLatch(count);

    @Test
    public void contextLoads() {

        StopWatch stopwatch = new StopWatch("async test");
        stopwatch.start();
        for (int i = 0; i < count; i++) {
            creatingThread08Service.call(countDownLatch);
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stopwatch.stop();
        System.out.println(stopwatch.prettyPrint());
    }


    @Test
    public void pool() {
        StopWatch stopwatch = new StopWatch("pool test");
        stopwatch.start();
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5,10,60, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(1000),new ThreadPoolExecutor.AbortPolicy());
        int count = 20;
        CountDownLatch countDownLatch = new CountDownLatch(count);
        for (int i = 0; i < count; i++) {
            poolExecutor.submit(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
                countDownLatch.countDown();
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stopwatch.stop();
        System.out.println(stopwatch.prettyPrint());
    }
}
