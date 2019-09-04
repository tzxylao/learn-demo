package com.tzxylao.java8.math;

import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author laoliangliang
 * @date 2019/8/16 15:09
 */
public class LongAdderDemo {
    public static void main(String[] args) {
        LongAdder longAdder = new LongAdder();
        longAdder.increment();
        System.out.println(longAdder.longValue());
        System.out.println(longAdder.sum());

        longAdder.add(2);
        System.out.println(longAdder.sum());

        System.out.println(longAdder.longValue());
        ReentrantLock lock = new ReentrantLock();
        lock.lock();

    }
}
