package com.tzxylao.java8.stream;

import java.util.Random;
import java.util.stream.Stream;

/**
 * @author laoliangliang
 * @since 19/4/18 下午8:18
 */
public class StreamDemo3 {
    public static void main(String[] args) {
        String str = "my name is 008";

        //打印每个长度大于2单词的长度
        Stream.of(str.split(" ")).filter(s -> s.length()>2).map(String::length).forEach(System.out::println);

        //flatMap A->B属性（是个集合），最终得到所有的A元素里面的所有B属性集合
        //intStream/longStream并不是Stream的子类，所以要进行装箱boxed
        //这里A表示每个分割后的单词，B则是单词分割后的集合
        Stream.of(str.split(" ")).flatMap(s -> s.chars().boxed()).forEach(s -> System.out.println((char)s.intValue()));

        //peek用于debug，是个中间操作，而forEcah是终止操作
        Stream.of(str.split(" ")).peek(System.out::print).forEach(System.out::print);
        System.out.println("-------");

        //limit使用，主要用于无限流
        new Random().ints().filter(i -> i>100 && i<1000).limit(10).forEach(System.out::println);
    }

}
