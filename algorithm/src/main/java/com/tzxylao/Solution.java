package com.tzxylao;

/**
 * 解决方法类，统一入口
 * @author laoliangliang
 * @date 2019/4/12 16:13
 */
public class Solution {
    public static void main(String[] args) {
        //求中位数
        testMedian();
    }

    private static void testMedian() {
        int[] num1 = {1,2};
        int[] num2 = {3,4};
        Median median = new Median();
        double medianSortedArray = median.findMedianSortedArrays(num1, num2);
        System.out.println(medianSortedArray);
    }
}
