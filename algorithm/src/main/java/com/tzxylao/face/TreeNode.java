package com.tzxylao.face;

/**
 * 给定一个二叉搜索树(BST)，找到树中第 K 小的节点。
 * Definition for a binary tree node.
 **/

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(50);
        treeNode.left = new TreeNode(30);
        treeNode.right = new TreeNode(60);
        treeNode.left.left=new TreeNode(20);
        treeNode.left.right=new TreeNode(40);
        treeNode.left.left.left=new TreeNode(10);
        Solution solution = new Solution();
        System.out.println(solution.kthSmallest(treeNode, 3));
    }
}

class Solution {
    private class ResultType {
    
        boolean found;  // 是否找到
        
        int count;  // 节点数目
        ResultType(boolean found, int count) {
            this.found = found;
            this.count = count;
        }
    }

    public int kthSmallest(TreeNode root, int k) {
        return kthSmallestHelper(root, k).count;
    }

    private ResultType kthSmallestHelper(TreeNode root, int k) {
        if (root == null) {
            return new ResultType(false, 0);
        }

        ResultType left = kthSmallestHelper(root.left, k);

        // 左子树找到，直接返回
        if (left.found) {
            return new ResultType(true, left.count);
        }

        // 左子树的节点数目 = K-1，结果为 root 的值
        if (k - left.count == 1) {
            return new ResultType(true, root.val);
        }

        // 右子树寻找
        ResultType right = kthSmallestHelper(root.right, k - left.count - 1);
        if (right.found) {
            return new ResultType(true, right.count);
        }

        // 没找到，返回节点总数
        return new ResultType(false, left.count + 1 + right.count);
    }
}