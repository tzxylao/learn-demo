package com.tzxylao.java8.thread;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author laoliangliang
 * @date 2019/7/19 15:59
 */
public class ThreadPractice {
    public void startTread(){
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(4, 4, 60, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(1));
        poolExecutor.submit(new MyThread());
        poolExecutor.submit(new MyThread());
        poolExecutor.submit(new MyThread());
        poolExecutor.submit(new MyThread());
        poolExecutor.shutdown();
        poolExecutor.shutdownNow();
        /*try {
            while(true){
                boolean b = poolExecutor.awaitTermination(1, TimeUnit.MILLISECONDS);
                System.out.println(b);
                if (b) {
                    break;
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }

    public static void main(String[] args) {
        ThreadPractice practice = new ThreadPractice();
        practice.startTread();
    }
    class MyThread implements Runnable{

        @Override
        public void run() {
            while(true){
                System.out.println(Thread.currentThread().getId());
                System.out.println(System.currentTimeMillis());
                if(Thread.currentThread().isInterrupted()){
                    System.out.println("中断退出");
                    break;
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("阻塞退出");
                    break;
                }
            }
        }
    }
}
