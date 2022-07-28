package Easy.平衡二叉树;

public class BalanceBiTree {

    public static final int BASE = 0x3f3f3f;

    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        int left = getHeight(root.left);
        if (left == BASE) {
            return false;
        }
        int right = getHeight(root.right);
        if (right == BASE) {
            return false;
        }
        return Math.abs(left - right) <= 1;
    }

    // 递归取左右子树的高度
    public int getHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = getHeight(node.left);
        int right = getHeight(node.right);
        if (left == BASE || right == BASE) {
            return BASE;
        }
        if (Math.abs(left - right) > 1) {
            return BASE;
        }
        // 因为前面要判断是否不平衡，且取了特殊值作为判断标准
        // 因此当前层次+1应该放在返回的时候，而不是取值的时候
        // 这会使得我们错过这个判断标准
        return Math.max(left, right)+1;
    }

}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
