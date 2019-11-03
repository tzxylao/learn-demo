package com.tzxylao.sort;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author laoliangliang
 * @date 2019/4/17 10:09
 */
public class MainSort extends BaseSort {

    /**
     * 选择排序
     */
    @Override
    public int[] selectSort(int[] nums) {
        int min = -1;
        int length = nums.length;
        int minIndex = 0;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                if (j - i == 1) {
                    minIndex = nums[i] <= nums[j] ? i : j;
                    min = nums[i] <= nums[j] ? nums[i] : nums[j];
                } else {
                    minIndex = min <= nums[j] ? minIndex : j;
                    min = min <= nums[j] ? min : nums[j];
                }
                if (j == length - 1) {
                    if (i < minIndex) {
                        swap(nums, i, minIndex);
                    }
                }
            }
        }
        return nums;
    }

    /**
     * 插入排序
     *
     * @param nums
     * @return
     */
    @Override
    public int[] insertSort(int[] nums) {
        int length = nums.length;
        for (int i = 1; i < length; i++) {
            swapIter(nums, i);
        }
        return nums;
    }

    /**
     * 冒泡排序
     *
     * @param nums
     * @return
     */
    @Override
    public int[] bubblingSort(int[] nums) {
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
                }
            }
        }
        return nums;
    }


    /**
     * 快速排序
     */
    @Override
    public int[] quickSort(int[] nums) {
        qSort2(nums, 0, nums.length - 1);
        return nums;
    }

    /**
     * 从大到小
     * @param nums
     * @param l
     * @param r
     */
    public void qSort2(int[] nums, int l, int r) {
        int p = nums[l];
        int left = l;
        int right = r;
        if (left == right) {
            return;
        }
        while (left < right) {
            while (left < right) {
                if (nums[left] < p) {
                    break;
                }
                left++;
            }

            while (left < right) {
                if (nums[right] > p) {
                    break;
                }
                right--;
            }

            if (left < right) {
                swap(nums, left, right);
            }
        }
        if (nums[right] > p) {
            swap(nums, right, l);
        }
        qSort2(nums, l, right - 1);
        qSort2(nums, right, r);
    }

    private void qSort(int[] nums, int l, int r) {
        int p = nums[l];
        int left = l;
        int right = r;
        if (left == right) {
            return;
        }
        while (left < right) {
            while (left < right) {
                if (nums[left] > p) {
                    break;
                }
                left++;
            }
            while (left < right) {
                if (nums[right] < p) {
                    break;
                }
                right--;
            }
            if (left < right) {
                swap(nums, left, right);
            }
        }
        if (nums[right] < p) {
            swap(nums, l, right);
        }
        qSort(nums, l, right - 1);
        qSort(nums, right, r);
    }

    /**
     * 归并排序
     */
    @Override
    public int[] mergerSort(int[] nums) {
        int length = nums.length;
        int mid = length / 2;
        if (mid == 0) {
            return nums;
        }
        int[] left = Arrays.copyOfRange(nums, 0, mid);
        int[] right = Arrays.copyOfRange(nums, mid, length);
        return merge(mergerSort(left), mergerSort(right));
    }

    private int[] merge(int[] lefts, int[] rights) {
        int[] sums = new int[lefts.length + rights.length];
        int l = 0;
        int r = 0;
        for (int i = 0; i < sums.length; i++) {
            if (l >= lefts.length) {
                sums[i] = rights[r++];
            } else if (r >= rights.length) {
                sums[i] = lefts[l++];
            } else if (lefts[l] <= rights[r]) {
                sums[i] = lefts[l++];
            } else {
                sums[i] = rights[r++];
            }
        }
        return sums;
    }

    @Override
    public int[] heapSort(int[] nums) {
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            //堆化
            int i1 = (length - i - 2) / 2;
            ajustHeap(nums, i1, length - i);
            swap(nums, 0, length - i - 1);
        }
        return nums;
    }

    private void ajustHeap(int[] nums, int i, int length) {
        int left = i * 2 + 1;
        int right = left + 1;
        //左右子节点最大值
        int max;
        if (right < length) {
            max = nums[left] > nums[right] ? left : right;
        } else {
            max = left;
        }
        if (nums[max] > nums[i]) {
            swap(nums, max, i);
        }
        if (left == 1) {
            return;
        }
        i--;
        ajustHeap(nums, i, length);
    }

    @Override
    public int[] shellSort(int[] nums) {
        int length = nums.length;
        int half = length / 2;
        while (half > 0) {
            for (int i = half; i < length; i++) {
                if (nums[i - half] > nums[i]) {
                    swap(nums, i - half, i);
                }
            }

            half = half / 2;
        }
        return nums;
    }

    /**
     * 计数排序
     */
    @Override
    public int[] countingSort(int[] nums, int range) {
        int[] temps = new int[range];
        for (int i = 0; i < nums.length; i++) {
            temps[nums[i]]++;
        }
        int count = 0;
        for (int i = 0; i < temps.length; i++) {
            for (int j = 0; j < temps[i]; j++) {
                nums[count++] = i;
            }
        }
        return nums;
    }

    /**
     * 基数排序
     */
    @Override
    public int[] radixSort(int[] nums) {
        List<List<Integer>> temps = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            temps.add(new ArrayList<Integer>());
        }
        int depth = 1;
        for (int k = 0; k < depth; k++) {
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] > Math.pow(10, depth)) {
                    depth++;
                }
                int num = (int) (nums[i] / Math.pow(10, k));
                //先取末尾数排序
                int remainder = num % 10;
                temps.get(remainder).add(nums[i]);
            }
            int count = 0;
            for (int i = 0; i < temps.size(); i++) {
                List<Integer> integers = temps.get(i);
                for (Integer j : integers) {
                    nums[count++] = j;
                }
            }
            for (List<Integer> temp : temps) {
                temp.clear();
            }
        }
        return nums;
    }

    /**
     * 二叉树排序
     */
    @Override
    public int[] binaryTreeSort(int[] nums) {
        Node node = new Node();
        node.val = nums[0];
        //树化
        for (int i = 1; i < nums.length; i++) {
            distNode(node, nums[i]);
        }
        //中序遍历
        return iterTree(node);
    }

    private List<Integer> result = new ArrayList<>();

    private int[] iterTree(Node node) {
        if (node != null) {
            iterTree(node.left);
            result.add(node.val);
            iterTree(node.right);

        }
        return result.stream().mapToInt(Integer::valueOf).toArray();
    }

    private void distNode(Node node, int val) {
        if (val < node.val) {
            if (node.left == null) {
                node.left = new Node();
                node.left.val = val;
            } else {
                distNode(node.left, val);
            }
        } else {
            if (node.right == null) {
                node.right = new Node();
                node.right.val = val;
            } else {
                distNode(node.right, val);
            }
        }
    }

    class Node {
        Node left;
        Node right;
        int val;
    }

    public static void main(String[] args) {
        //随机数的个数
        int range = 100;
//        int range = 10;
        MainSort s = new MainSort();
        //是否打印结果
        s.showResult(true);
        ISort sort = (ISort) Proxy.newProxyInstance(s.getClass().getClassLoader(), new Class[]{ISort.class}, new SortHandler(s));
        //选择排序
        int[] nums = getRandomVal(range);
        sort.selectSort(nums);

        //插入排序（递归容易栈溢出）
        nums = getRandomVal(range);
        sort.insertSort(nums);

        //冒泡排序
        nums = getRandomVal(range);
        sort.bubblingSort(nums);

        //快速排序
        nums = getRandomVal(range);
//        nums = new int[]{9, 8, 7, 6, 5};
        sort.quickSort(nums);

        //归并排序
        nums = getRandomVal(range);
        sort.mergerSort(nums);

        //堆排序
        nums = getRandomVal(range);
        sort.heapSort(nums);

        //希尔排序
        nums = getRandomVal(range);
        sort.shellSort(nums);

        //计数排序
        nums = getRandomVal(range);
        sort.countingSort(nums, range);

        //基数排序
        nums = getRandomVal(range);
        sort.radixSort(nums);

        //二叉树排序
        nums = getRandomVal(range);
        sort.binaryTreeSort(nums);
    }


    /**
     * 获取随机值
     */
    private static int[] getRandomVal(int range) {
        Random random = new Random();
        int[] nums = new int[range];
        for (int i = 0; i < range; i++) {
            int randomVal = random.nextInt(range);
            nums[i] = randomVal;
        }
        return nums;
    }
}
