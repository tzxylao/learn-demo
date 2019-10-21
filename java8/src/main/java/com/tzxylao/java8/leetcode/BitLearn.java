package com.tzxylao.java8.leetcode;

/**
 * @author laoliangliang
 * @date 2019/10/16 11:17
 */
public class BitLearn {

    public static void main(String[] args) {
//        int[] nums = {1,150,1,16};
//        System.out.println(JSON.toJSONString(sinleNumber(nums)));
        int[] nums = {1,1,1,16,3,3,3};
        System.out.println(sinleNumber2(nums));
    }

    public static int sinleNumber2(int[] nums) {
        int[] params = new int[3];
        for (int i = 0; i < nums.length; i++) {
            params[1] |= params[0] & nums[i];
            params[0] ^= nums[i];
            params[2] = params[0] & params[1];
            params[0] &= ~params[2];
            params[1] &= ~params[2];
        }
        return params[0];
    }

    public static int[] sinleNumber(int[] nums){
        int res = 0;
        for (int num : nums) {
            res = res ^ num;
        }

        int i = 0;
        while (((res >> i) & 1) == 0) {
            i++;
        }
        int res1 = 0,res2 =0;
        for (int num : nums) {
            if(((num >> i) & 1) == 1){
                res1 = res1 ^ num;
            }else{
                res2 = res2 ^ num;
            }
        }
        int[] result = new int[2];
        result[0] = res1;
        result[1] = res2;
        return result;
    }

}
