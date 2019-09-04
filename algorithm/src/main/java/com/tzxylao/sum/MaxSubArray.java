package com.tzxylao.sum;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * @author laoliangliang
 * @date 2019/8/9 16:29
 */
public class MaxSubArray {
    public static void main(String[] args) {
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray(nums));
    }
    public static int maxSubArray(int[] nums) {
        int temp = nums[0];
        int sum = 0;
        List<Integer> list = new ArrayList<>();
        ArrayList<Integer> tempList = new ArrayList<>();
        for(int n : nums){
            if(sum > 0){
                sum+=n;
            }else{
                tempList.clear();
                sum = n;
            }
            tempList.add(n);
            if (temp < sum) {
//                list.add(n);
                temp = sum;
                list = (List<Integer>) tempList.clone();
            }
        }
        System.out.println(JSON.toJSONString(list));
        return temp;
    }
}
