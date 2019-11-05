package com.tzxylao.sort;

/**
 * @author laoliangliang
 * @date 2019/4/17 11:22
 */
public interface ISort {
    /**
     * 设置显示打印结果
     */
    void showResult();

    /**
     * 设置是否打印结果
     */
    void showResult(boolean isShowResult);

    /**
     * 返回是否打印排序结果
     *
     * @return
     */
    boolean isShowResult();

    /**
     * 选择排序
     */
    int[] selectSort(int[] nums);

    /**
     * 插入排序
     */
    int[] insertSort(int[] nums);

    /**
     * 冒泡排序
     */
    int[] bubblingSort(int[] nums);

    /**
     * 快速排序
     */
    int[] quickSort(int[] nums);

    /**
     * 归并排序
     */
    int[] mergerSort(int[] nums);

    /**
     * 堆排序
     */
    int[] heapSort(int[] nums);

    /**
     * 堆排序 大到小
     */
    int[] heap2Sort(int[] nums);

    /**
     * 希尔排序
     */
    int[] shellSort(int[] nums);

    /**
     * 计数排序
     */
    int[] countingSort(int[] nums, int range);

    /**
     * 基数排序
     */
    int[] radixSort(int[] nums);

    /**
     * 二叉树排序
     */
    int[] binaryTreeSort(int[] nums);
}
