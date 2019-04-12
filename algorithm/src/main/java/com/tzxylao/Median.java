package com.tzxylao;

/**
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * 你可以假设 nums1 和 nums2 不会同时为空。
 *
 * @author laoliangliang
 * @date 2019/4/12 16:12
 */
public class Median {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        boolean odd = false;
        if ((len1 + len2) % 2 == 1) {
            odd = true;
        }
        int midStart = (len1 + len2) / 2;
        if (len1 == 0) {
            if (odd) {
                return nums2[midStart];
            }else{
                return (nums2[midStart]+nums2[midStart-1])*1.0/2;
            }
        }
        if (len2 == 0) {
            if (odd) {
                return nums1[midStart];
            }else{
                return (nums1[midStart]+nums1[midStart-1])*1.0/2;
            }
        }
        int median = 0;
        int[] count = new int[2];
        int[] midMedian = new int[2];
        while (midStart >= 0) {

            if (nums1[count[0]] <= nums2[count[1]]) {
                median = nums1[count[0]];
                if (count[0] < len1 - 1) {
                    count[0]++;
                }
            } else {
                median = nums2[count[1]];
                if (count[1] < len2 - 1) {
                    count[1]++;
                }
            }
            if (odd) {
                if (midStart == 0) {
                    midMedian[0] = median;
                }
            } else {
                if (midStart == 1) {
                    midMedian[0] = median;
                } else if (midStart == 0) {
                    midMedian[1] = median;
                }
            }
            midStart--;
        }
        return midMedian[1]>0?(midMedian[0] + midMedian[1]) * 1.0 / 2 : midMedian[0];
    }
}
