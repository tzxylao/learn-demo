package com.tzxylao.java8.stream;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author laoliangliang
 * @since 19/4/18 下午7:52
 */
public class StreamDemo2 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        //从集合创建
        list.stream();
        list.parallelStream();

        //从数组创建
        Arrays.stream(new int[]{2, 3, 4});

        //创建数字流
        IntStream.of(1, 2, 3);
        System.out.println(IntStream.rangeClosed(1, 10).average().getAsDouble());

        //使用random创建一个无限流
        new Random().ints().limit(10);
        Random random = new Random();
        List<Integer> collect = Stream.generate(() -> random.nextInt()).limit(10).collect(Collectors.toList());
        for (Integer integer : collect) {
            System.out.print(integer+" ");
        }
        List<String> strings = Arrays.asList("hello", "wolrd", "java8");
        String collect1 = strings.stream().collect(Collectors.joining(","));
        System.out.println(collect1);

        long count = Arrays.stream(new String[]{"12","2","3"})
                .mapToInt(String::length)
                .sum();
        System.out.println(count);
    }
}
