package com.tzxylao.tree;

import java.util.*;

/**
 * 广度优先遍历
 *
 * @author laoliangliang
 * @date 2019/7/30 14:30
 */
public class Breadth {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return this.val + "";
        }
    }

    public void traversing(Queue<TreeNode> queue, List<TreeNode> nodes) {
        if (queue.isEmpty()) {
            return;
        }
        TreeNode node = queue.poll();
        nodes.add(node);
        if (node.left != null) {
            queue.add(node.left);
        }
        if (node.right != null) {
            queue.add(node.right);
        }
        traversing(queue, nodes);
    }

    private static List<List<Integer>> list = new ArrayList<>();

    private void traversing2(TreeNode node, int level) {

        if (list.size() == level) {
            list.add(new ArrayList<>());
        }

        list.get(level).add(node.val);
        if (node.left != null) {
            traversing2(node.left, level + 1);
        }
        if (node.right != null) {
            traversing2(node.right, level + 1);
        }
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(28);
        treeNode.left = new TreeNode(16);
        treeNode.left.left = new TreeNode(13);
        treeNode.left.right = new TreeNode(22);
        treeNode.right = new TreeNode(30);
        treeNode.right.left = new TreeNode(29);
        treeNode.right.right = new TreeNode(43);

        Breadth breadth = new Breadth();
        List<TreeNode> treeNodes = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(treeNode);
        breadth.traversing(queue, treeNodes);
        System.out.println(Arrays.toString(treeNodes.toArray(new TreeNode[]{})));
        breadth.traversing2(treeNode, 0);
        System.out.println(Arrays.toString(list.toArray(new List[]{})));
    }
}
