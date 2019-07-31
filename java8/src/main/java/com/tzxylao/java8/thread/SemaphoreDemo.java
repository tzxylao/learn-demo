package com.tzxylao.java8.thread;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 * @author laoliangliang
 * @date 2019/7/30 16:31
 */
public class SemaphoreDemo {

    private Semaphore h, o, s3, s4;

    private CyclicBarrier a = new CyclicBarrier(1);
    private CyclicBarrier b = new CyclicBarrier(1);

    public SemaphoreDemo() {
        h = new Semaphore(2);
        o = new Semaphore(1);
        s3 = new Semaphore(0);
        s4 = new Semaphore(0);
    }

    public static void main(String[] args) throws InterruptedException {
        SemaphoreDemo semaphoreDemo = new SemaphoreDemo();
        semaphoreDemo.hydrogen(() -> {
            System.out.print("H");
        });
        semaphoreDemo.oxygen(() -> {
            System.out.print("O");
        });
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        releaseHydrogen.run();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        releaseOxygen.run();
    }
}
