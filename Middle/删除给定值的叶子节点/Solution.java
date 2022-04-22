package Middle.删除给定值的叶子节点;

public class Solution {
    // 删除所有叶子节点为target的节点
    public TreeNode removeLeafNodes(TreeNode root, int target) {
        if (root.left!=null) {
            root.left = removeLeafNodes(root.left, target);  // 把运行结果重新赋值给左子树，才算更新删除
        }
        if (root.right!=null){
            root.right = removeLeafNodes(root.right, target);
        }
        if (root.left==null && root.right==null && root.val==target){  // 叶子
            return null;
        }
        return root;
    }
}

class TreeNode{
    int val;
    TreeNode left, right;
    TreeNode(){}
    TreeNode(int val){ this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right){
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
