package com.tzxylao.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 * <p>
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回其自底向上的层次遍历为：
 * <p>
 * [
 * [15,7],
 * [9,20],
 * [3]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author laoliangliang
 * @date 2019/8/14 10:51
 */
public class LevelOrderBottom {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        res = levelOrderBottom(root, res, 0);
        Collections.reverse(res);
        return res;
    }

    private List<List<Integer>> levelOrderBottom(TreeNode root, List<List<Integer>> res, int height) {
        if (root == null) {
            return res;
        }
        if (res.size() <= height) {
            res.add(new ArrayList<>());
        }
        res.get(height).add(root.val);
        height++;

        levelOrderBottom(root.left, res, height);
        levelOrderBottom(root.right, res, height);
        return res;
    }

}
