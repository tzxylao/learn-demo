package com.tzxylao.tree;

/**
 * @author laoliangliang
 * @date 2019/7/30 9:49
 */
public class TreeTraversing {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    int rootVal = 28;

    private void init() {
        TreeNode treeNode = new TreeNode(rootVal);
        treeNode.left = new TreeNode(16);
        treeNode.left.left = new TreeNode(13);
        treeNode.left.right = new TreeNode(22);
        treeNode.right = new TreeNode(30);
        treeNode.right.left = new TreeNode(29);
        treeNode.right.right = new TreeNode(43);
        printBefore(treeNode);
        System.out.println();
        printMiddle(treeNode);
        System.out.println();
        printAfter(treeNode);

        System.out.println();
        System.out.println(judgeBefore(treeNode, null, null));

        treeNode = new TreeNode(rootVal);
        treeNode.left = new TreeNode(16);
        treeNode.left.left = new TreeNode(13);
        treeNode.left.right = new TreeNode(22);
        treeNode.right = new TreeNode(16);
        treeNode.right.left = new TreeNode(22);
        treeNode.right.right = new TreeNode(13);
        System.out.println(vaildSymmetry(treeNode, treeNode));
    }


    public static void main(String[] args) {
        TreeTraversing treeTraversing = new TreeTraversing();
        treeTraversing.init();
    }

    /**
     * 前序遍历
     */
    public void printBefore(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.print(node.val + " ");
        printBefore(node.left);
        printBefore(node.right);
    }

    /**
     * 中序遍历
     */
    public void printMiddle(TreeNode node) {
        if (node == null) {
            return;
        }
        printMiddle(node.left);
        System.out.print(node.val + " ");
        printMiddle(node.right);
    }

    /**
     * 后序遍历
     */
    public void printAfter(TreeNode node){
        if (node == null) {
            return;
        }
        printAfter(node.left);
        printAfter(node.right);
        System.out.print(node.val + " ");
    }

    /**
     * 前序遍历变种，验证二叉搜索树
     */
    public boolean judgeBefore(TreeNode node, Integer lower, Integer upper){
        if (node == null) {
            return true;
        }
        int val = node.val;
        if(lower != null && val <= lower){
            return false;
        }
        if (upper != null && val >= upper) {
            return false;
        }

        if(!judgeBefore(node.right, node.val, upper)){
            return false;
        }

        if(!judgeBefore(node.left, lower, node.val)){
            return false;
        }
        return true;
    }

    /**
     * 验证对称二叉树
     */
    public boolean vaildSymmetry(TreeNode t1, TreeNode t2){
        if (t1 == null && t2 == null) {
            return true;
        }
        if (t1 == null || t2 == null) {
            return false;
        }
        return (t1.val == t2.val) && vaildSymmetry(t1.left, t2.right) && vaildSymmetry(t1.right, t2.left);

    }
}
