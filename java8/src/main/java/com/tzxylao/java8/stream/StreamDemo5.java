package com.tzxylao.java8.stream;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author laoliangliang
 * @since 19/4/18 下午9:31
 */
public class StreamDemo5 {
    public static void main(String[] args) {
        //调用parallel产生一个并行流
//        IntStream.range(1, 100).parallel().peek(StreamDemo5::debug).count();

        //现在想要实现一个先并行再串行
        //多次调用parallel，sequential以最后一次调用为准，不能同时并行和串行
//        IntStream.range(1,100)
//                .parallel().peek(StreamDemo5::debug)
//                .sequential().peek(StreamDemo5::debug2)
//                .count();

//        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "20");
//        IntStream.range(1, 100).parallel().peek(StreamDemo5::debug).count();

        //使用自己的线程池，不使用默认线程池，防止任务被阻塞
        ForkJoinPool pool = new ForkJoinPool(3);
        pool.submit(() -> IntStream.range(1,100).parallel().peek(StreamDemo5::debug).count());
        pool.shutdown();

        synchronized (pool) {
            try {
                pool.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void debug(int i) {
        System.out.println(Thread.currentThread().getName()+" debug "+i);
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void debug2(int i) {
        System.err.println(Thread.currentThread().getName()+" debug "+i);
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
