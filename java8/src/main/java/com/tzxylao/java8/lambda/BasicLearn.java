package com.tzxylao.java8.lambda;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author laoliangliang
 * @date 2019/9/29 10:40
 */
public class BasicLearn {

    public static void main(String[] args) {
        // 消费者
        Consumer<String> consumer = s -> System.out.println(s);
        consumer.accept("12345");

        // 生产者
        Supplier<String> supplier = () -> "hello world";
        System.out.println(supplier.get());



    }
}
