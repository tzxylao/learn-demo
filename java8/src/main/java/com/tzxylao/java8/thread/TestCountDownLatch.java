package com.tzxylao.java8.thread;

import java.util.concurrent.CountDownLatch;

public class TestCountDownLatch {
    public static void main(String[] args) {
        // 需要等待两个线程，所以传入参数为2
        CountDownLatch latch = new CountDownLatch(2);
        // 该线程运行1秒
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("1号选手准备就绪！用时1秒！");
                latch.countDown();
            }
        }).start();
        
        // 该线程运行3秒
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("2号选手准备就绪！用时3秒！");
                latch.countDown();
            }
        }).start();
        
        try {
            System.out.println("请1号选手和2号选手各就各位！");
            // 主线程在此等待两个线程执行完毕之后继续执行
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 两个线程执行完毕后，主线程恢复运行
        System.out.println("裁判发枪，1号选手和2号选手开跑！");
    }
}