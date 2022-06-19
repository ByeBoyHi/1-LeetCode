package Daily_Topic.二叉搜索树中第K小的元素;

public class JavaDemo {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode treeA = new TreeNode(3);
        TreeNode treeB = new TreeNode(6);
        TreeNode treeC = new TreeNode(2);
        TreeNode treeD = new TreeNode(4);
        TreeNode treeE = new TreeNode(1);
        root.left = treeA;
        root.right = treeB;
        treeA.left = treeC;
        treeA.right = treeD;
        treeC.left = treeE;
        System.out.println(new Solution().kthSmallest(root, 3));
    }
}

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    int num = 0;
    int ans = -1;

    public int kthSmallest(TreeNode root, int k) {
        // 访问左子树
        if (root.left != null && num<k) {
            kthSmallest(root.left, k);
        }

        //访问自己
        num++;
        if (num == k) {
            ans = root.val;
        }
        // 访问右子树
        if (root.right != null && num<k) {
            kthSmallest(root.right, k);
        }
        return ans;
    }
}

class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode() {}
     TreeNode(int val) { this.val = val; }
     TreeNode(int val, TreeNode left, TreeNode right) {
         this.val = val;
         this.left = left;
         this.right = right;
     }
 }