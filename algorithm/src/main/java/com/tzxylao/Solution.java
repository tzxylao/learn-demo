package com.tzxylao;

import java.util.LinkedList;
import java.util.List;

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

        System.out.println(solution.generateTrees(3));

    }
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return val+"";
        }
    }

    public LinkedList<TreeNode> generate_trees(int start, int end) {
        LinkedList<TreeNode> all_trees = new LinkedList<TreeNode>();
        if (start > end) {
            all_trees.add(null);
            return all_trees;
        }

        // pick up a root
        for (int i = start; i <= end; i++) {
            // all possible left subtrees if i is choosen to be a root
            LinkedList<TreeNode> left_trees = generate_trees(start, i - 1);

            // all possible right subtrees if i is choosen to be a root
            LinkedList<TreeNode> right_trees = generate_trees(i + 1, end);

            // connect left and right trees to the root i
            for (TreeNode l : left_trees) {
                for (TreeNode r : right_trees) {
                    TreeNode current_tree = new TreeNode(i);
                    current_tree.left = l;
                    current_tree.right = r;
                    all_trees.add(current_tree);
                }
            }
        }
        return all_trees;
    }

    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new LinkedList<TreeNode>();
        }
        return generate_trees(1, n);
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
