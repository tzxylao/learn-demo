package com.tzxylao.java8.thread;

import java.util.concurrent.CountDownLatch;

/**
 * @author laoliangliang
 * @date 2019/8/5 14:21
 */
public class CountDownLatchDemo {
    private static CountDownLatch c1 = new CountDownLatch(1);
    public static void main(String[] args) throws InterruptedException {
        System.out.println("1");
        new Thread(() -> {
            try {
                System.out.println("2");
                c1.await();
                System.out.println("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            System.out.println("4");
        }).start();
        System.out.println("5");
        Thread.sleep(1000);
        c1.countDown();
        System.out.println("6");
    }
}
