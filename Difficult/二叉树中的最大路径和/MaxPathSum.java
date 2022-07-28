package Difficult.二叉树中的最大路径和;

public class MaxPathSum {
    int val = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        getMax(root);
        return val;
    }

    // 给一个全局变量来记录最大路径和
    public int getMax(TreeNode root) {
        if (root == null) return 0;

        // 之前的路径和小于0，则不会选定，在这里实现重新选路径
        int left = Math.max(0, getMax(root.left));
        int right = Math.max(0, getMax(root.right));

        int im = root.val + left + right; // 左中右这样的路径

        // 更新
        val = Math.max(val, im);

        // 每次都链接这个点
        return root.val + Math.max(left, right);
    }
}

class TreeNode {
    int val;
    TreeNode left, right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

