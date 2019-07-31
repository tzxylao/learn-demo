package com.tzxylao.tree;

import java.util.LinkedList;

/**
 * 验证二叉搜索树
 * @author laoliangliang
 * @date 2019/7/29 17:15
 */
public class VaildTree {

    LinkedList<TreeNode> stack = new LinkedList();
    LinkedList<Integer> uppers = new LinkedList(),
            lowers = new LinkedList();

    public void update(TreeNode root, Integer lower, Integer upper) {
        stack.add(root);
        lowers.add(lower);
        uppers.add(upper);
    }

    public boolean helper(TreeNode node, Integer lower, Integer upper) {
        if (node == null) {
            return true;
        }

        int val = node.val;
        if (lower != null && val <= lower) {
            return false;
        }
        if (upper != null && val >= upper) {
            return false;
        }

        if (! helper(node.right, val, upper)) {
            return false;
        }
        if (! helper(node.left, lower, val)) {
            return false;
        }
        return true;
    }

    public boolean isValidBST(TreeNode root) {
        return helper(root, null, null);
    }
    public boolean isValidBST2(TreeNode root) {
        Integer lower = null, upper = null, val;
        update(root, lower, upper);

        while (!stack.isEmpty()) {
            root = stack.poll();
            lower = lowers.poll();
            upper = uppers.poll();

            if (root == null) continue;
            val = root.val;
            if (lower != null && val <= lower) return false;
            if (upper != null && val >= upper) return false;
            update(root.right, val, upper);
            update(root.left, lower, val);
        }
        return true;
    }



    public static void main(String[] args) {
        VaildTree vaildTree = new VaildTree();
        TreeNode node = new TreeNode(5);
        node.left = new TreeNode(1);
        node.right = new TreeNode(7);
        node.right.left = new TreeNode(6);
//        node.right.left.left = new TreeNode(3);
        node.right.right = new TreeNode(10);
        node.right.right.left = new TreeNode(4);
        vaildTree.isValidBST(node);
//        vaildTree.isValidBST2(node);
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


}
