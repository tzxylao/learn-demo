package com.tzxylao.java8.lambda;

/**
 * @author laoliangliang
 * @since 19/4/17 下午8:48
 */

/**
 * 表示lambda表达式，默认实现方法
 */
@FunctionalInterface
interface Interface1{
    int doubleNum(int i);

    default int add(int x, int y) {
        return x + y;
    }
}


public class LambdaDemo1 {
    public static void main(String[] args) {
        Interface1 i1 = i -> i*2;
        Interface1 i2 = (int i) -> i*2;
        Interface1 i3 = (int i) ->  {
            return i*2;
        };


        System.out.println(i1.doubleNum(20));
    }
}
