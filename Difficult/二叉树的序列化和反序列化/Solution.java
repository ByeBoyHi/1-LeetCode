package Difficult.二叉树的序列化和反序列化;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node4.left = node6;
        node4.right = node7;

        Codec codec = new Codec();
        String s = codec.serialize(node1);
        System.out.println(s);
        TreeNode node = codec.deserialize(s);
        TreeNode.printTree(node);
    }

    public static class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root == null) {
                return "#_";
            }
            String ans = root.val + "_";
            ans += serialize(root.left);
            ans += serialize(root.right);
            return ans;
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            String[] strings = data.split("_");
            Queue<String> queue = new LinkedList<>(Arrays.asList(strings));
            return process(queue);
        }

        private TreeNode process(Queue<String> queue) {
            String str = queue.poll();  // 要把poll取出来判断，否则下面会poll两次
            if (Objects.equals(str, "#")) {
                return null;
            }
            TreeNode node = new TreeNode(Integer.parseInt(Objects.requireNonNull(str)));
            node.left = process(queue);
            node.right = process(queue);
            return node;
        }
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }

    public static void printTree(TreeNode node){
        if (node!=null){
            System.out.println(node.val);
            printTree(node.left);
            printTree(node.right);
        }
    }
}