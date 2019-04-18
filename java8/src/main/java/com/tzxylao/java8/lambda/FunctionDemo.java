package com.tzxylao.java8.lambda;

import java.util.function.*;

/**
 * @author laoliangliang
 * @since 19/4/17 下午9:23
 */
public class FunctionDemo {
    public static void main(String[] args) {
        Predicate<Integer> predicate = i -> i > 5;
        System.out.println(predicate.test(19));

        Consumer<String> consumer = i -> System.out.println(i);
        consumer.accept("consumer");

        Supplier<String> supplier = () -> "producer";
        System.out.println(supplier.get());

        UnaryOperator<String> unaryOperator = i -> i + "-operator";
        System.out.println(unaryOperator.apply("test"));

        BiFunction<Integer, Integer, String> biFunction = (i, j) -> "年龄总共：" + (i + j) + "岁";
        System.out.println(biFunction.apply(10, 16));

        BinaryOperator<String> binaryOperator = (s1, s2) -> s1 + s2;
        System.out.println(binaryOperator.apply("我的就是", "你的"));

    }
}
