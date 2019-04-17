package com.tzxylao.java8.lambda;

import java.util.Arrays;
import java.util.OptionalInt;

/**
 * @author laoliangliang
 * @since 19/4/17 下午8:31
 */
public class MinDemo {

    public static void main(String[] args) {
        int[] nums = {331, 13, 4, 532, 645, 77, 234};
        OptionalInt min = Arrays.stream(nums).min();
        System.out.println(min.isPresent() ? min.getAsInt() : 0);
    }
}
