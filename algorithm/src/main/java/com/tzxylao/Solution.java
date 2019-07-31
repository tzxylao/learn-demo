package com.tzxylao;

/**
 * 解决方法类，统一入口
 *
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
//        testAtoi();

        //回文整数
//        testPalindrome();

        //算最大面积 https://leetcode-cn.com/problems/container-with-most-water/
        Solution solution = new Solution();
        int[] height = {1,8,6,2,5,4,8,3,7};
        System.out.println(solution.maxArea(height));

    }

    private int area;

    public int maxArea(int[] height) {
        int length = height.length - 1;
        compare(height, 0, length);
        return area;
    }

    private void compare(int[] height, int left, int right) {
        if (left >= right) {
            return;
        }
        int leftVal = height[left];
        int rightVal = height[right];
        int temp;
        if (leftVal > rightVal) {
            temp = rightVal*(right-left);
            right--;
        }else{
            temp = leftVal*(right-left);
            left++;
        }
        if (temp > area) {
            area = temp;
        }
        compare(height, left, right);
    }

    private static void testPalindrome() {
        int x = 182281;
        Palindrome palindrome = new Palindrome();
        System.out.println("is Palindrome?" + (palindrome.isPalindrome(x) ? "yes" : "no"));
    }

    private static void testMedian() {
        int[] num1 = {1, 2};
        int[] num2 = {3, 4};
        Median median = new Median();
        double medianSortedArray = median.findMedianSortedArrays(num1, num2);
        System.out.println(medianSortedArray);
    }

    private static void testReverse() {
        Reverse reverse = new Reverse();
        int reverse1 = reverse.reverse(1534236469);
        System.out.println(reverse1);
    }

    private static void testAtoi() {
        Atoi atoi = new Atoi();
        System.out.println(atoi.myAtoi("-+2"));
    }
}
