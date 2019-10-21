package com.tzxylao.java8.thread;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author laoliangliang
 * @date 2019/10/10 10:10
 */
public class ExecutorDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(1);
        executor.setMaxPoolSize(5);
        executor.setQueueCapacity(1);
        executor.setBeanName("mybean");
        executor.setThreadNamePrefix("mytask-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        executor.initialize();
        List<FutureTask<String>> result = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            FutureTask<String> futureTask = new FutureTask<>(() -> {
                System.out.println("hello world!");
                Thread.sleep(200);
                return "hello "+Thread.currentThread().getName();
            });
            executor.submit(futureTask);
            result.add(futureTask);
        }
        for (FutureTask<String> futureTask : result) {
            try {
                System.out.println(futureTask.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        ListenableFuture<?> runnable = executor.submitListenable(() -> {
            Thread.sleep(1000);
            System.out.println("runnable");
            return "runnable result";
        });
        runnable.addCallback(new ListenableFutureCallback<Object>() {
            @Override
            public void onFailure(Throwable e) {
              e.printStackTrace();
            }

            @Override
            public void onSuccess(Object o) {
                System.out.println("success "+o.toString());
            }
        });

        System.out.println(runnable.get());
        executor.shutdown();

        /*ExecutorService delegate = Executors.newCachedThreadPool();
        ListeningExecutorService service =
                MoreExecutors.listeningDecorator(delegate);
        ListenableFuture<String> listenableFuture = service.submit(() -> "hahaha");
        System.out.println("before");
        Futures.addCallback(listenableFuture, new FutureCallback<String>() {
            @Override
            public void onSuccess(@Nullable String s) {
                System.out.println("get "+s);
            }

            @Override
            public void onFailure(Throwable e) {
                e.printStackTrace();
            }
        },delegate);
        System.out.println("after");
        service.shutdown();*/
    }
}
