package com.tzxylao.java8;

import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * @author laoliangliang
 * @date 2019/4/4 14:49
 */
public class StreamTest {
    public static void main(String[] args) {

        List<Person> persons = new ArrayList<>();
        IntStream.range(1, 10).mapToObj(i -> new Person(i + "", i)).forEach(persons::add);

        Optional.of(new Person("tom", 12)).flatMap(s -> Optional.ofNullable(s.getName()))
                .ifPresent(System.out::println);

        System.out.println(persons.stream().reduce(0, (i, p) -> i += p.getAge(), (sum1, sum2) -> sum1 + sum2));

        Arrays.asList("a1", "a2", "b1", "c2", "c1")
                .parallelStream()
                .filter(s -> {
                    System.out.format("filter: %s [%s]\n",
                            s, Thread.currentThread().getName());
                    return true;
                })
                .map(s -> {
                    System.out.format("map: %s [%s]\n",
                            s, Thread.currentThread().getName());
                    return s.toUpperCase();
                })
                .forEach(s -> System.out.format("forEach: %s [%s]\n",
                        s, Thread.currentThread().getName()));

        ExecutorService service = new ThreadPoolExecutor(2, 2, 10, TimeUnit.SECONDS, new LinkedBlockingDeque<>(12),new CustomizableThreadFactory("tzxylao"),new ThreadPoolExecutor.AbortPolicy());
        service.submit(() -> {
            System.out.println("hello");
        });
        service.shutdown();
        service.shutdownNow();
    }
}
