package com.tzxylao;

/**
 * 解决方法类，统一入口
 * @author laoliangliang
 * @date 2019/4/12 16:13
 */
public class Solution {
    public static void main(String[] args) {
        //求中位数
//        testMedian();

        //整数反转
//        testReverse();

        //字符串转整数
        testAtoi();
    }

    private static void testMedian() {
        int[] num1 = {1,2};
        int[] num2 = {3,4};
        Median median = new Median();
        double medianSortedArray = median.findMedianSortedArrays(num1, num2);
        System.out.println(medianSortedArray);
    }

    private static void testReverse(){
        Reverse reverse = new Reverse();
        int reverse1 = reverse.reverse(1534236469);
        System.out.println(reverse1);
    }

    private static void testAtoi() {
        Atoi atoi = new Atoi();
        System.out.println(atoi.myAtoi("-+2"));
    }
}
