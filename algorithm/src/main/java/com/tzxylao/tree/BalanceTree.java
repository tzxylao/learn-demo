package com.tzxylao.tree;

/**
 * 判断是否是平衡二叉树
 *
 * @author laoliangliang
 * @date 2019/8/8 15:44
 */
public class BalanceTree {
     static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private class ReturnNode {
        boolean isB;

        int depth;

        public ReturnNode(boolean isB, int depth) {
            this.isB = isB;
            this.depth = depth;
        }
    }

    public boolean isBalanced(TreeNode root) {
        return isBST(root).isB;
    }

    private ReturnNode isBST(TreeNode root) {
        if (root == null) {
            return new ReturnNode(true, 0);
        }

        ReturnNode left = isBST(root.left);
        ReturnNode right = isBST(root.right);
        if (!left.isB || !right.isB) {
            return new ReturnNode(false, 0);
        }
        if (Math.abs(left.depth - right.depth) > 1) {
            return new ReturnNode(false, 0);
        }
        return new ReturnNode(true, Math.max(left.depth, right.depth) + 1);
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(1);
        node.left.left = new TreeNode(1);
        node.right = new TreeNode(1);
        BalanceTree tree = new BalanceTree();
        System.out.println(tree.isBalanced(node));
    }
}
