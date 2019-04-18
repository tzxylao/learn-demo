package com.tzxylao.sort;

/**
 * @author laoliangliang
 * @date 2019/4/17 14:19
 */
public abstract class BaseSort implements ISort {
    private boolean isShowResult;

    @Override
    public void showResult() {
        showResult(true);
    }

    @Override
    public void showResult(boolean isShowResult) {
        this.isShowResult = isShowResult;
    }

    @Override
    public boolean isShowResult() {
        return isShowResult;
    }

    void swapIter(int[] nums, int i) {
        if (i <= 0 || nums[i - 1] <= nums[i]) {
            return;
        }
        swap(nums, i - 1, i);
        i--;
        swapIter(nums, i);
    }

    void swap(int[] nums, int i, int j) {
        if (i == j) {
            return;
        }
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
