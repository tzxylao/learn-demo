package com.tzxylao.java8.lambda;

import java.util.function.Consumer;

/**
 * 变量引用
 * @author laoliangliang
 * @since 19/4/17 下午10:17
 */
public class VarDemo {
    public static void main(String[] args) {
        //实际是final类型
        String str = "Hello";
        //为什么匿名内部类传变量要final类型，因为变量传递实际是传值而不是传引用
        Consumer<String> consumer = s -> System.out.println(s + str);
        consumer.accept("tom ");
    }
}
