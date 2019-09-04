package com.tzxylao.java8.queue;

import org.apache.lucene.util.NamedThreadFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author laoliangliang
 * @date 2019/8/19 9:59
 */
public class ReadWriteQueue {
    private static List<Integer> cache = new ArrayList<>();
    private static Random random = new Random();

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                50, 50, 1,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>(0),
                new NamedThreadFactory("测试同时内存读写"));
        executor.submit(new WriteRAM());
        executor.submit(new WriteRAM());
        executor.submit(new ReadRAM());
        executor.submit(new ReadRAM());
    }

    static class WriteRAM implements Runnable {
        @Override
        public void run() {
            for (; ; ) {
                cache.add(random.nextInt(1000));
            }
        }
    }

    static class ReadRAM implements Runnable {
        @Override
        public void run() {
            for (; ; ) {
                if (cache.size() > 0) {
                    cache.remove(0);
                }
            }
        }
    }

}
