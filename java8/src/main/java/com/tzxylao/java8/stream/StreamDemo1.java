package com.tzxylao.java8.stream;

import java.util.stream.IntStream;

/**
 * @author laoliangliang
 * @since 19/4/18 下午7:40
 */
public class StreamDemo1 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        int sum = 0;
        for (int num : nums) {
            sum+=num;
        }
        System.out.println("结果为："+sum);

        //使用steam内部迭代
        //map就是中间操作（返回stream的操作 ）
        //sum就是终止操作
        System.out.println("结果为："+IntStream.of(nums).map(StreamDemo1::doubleNum).sum());

        System.out.println("惰性求值就是终止没有调用的情况下，中间操作不会执行");
        IntStream.of(nums).map(StreamDemo1::doubleNum);
    }

    public static int doubleNum(int i) {
        System.out.println("执行乘2操作");
        return i*2;
    }
}
