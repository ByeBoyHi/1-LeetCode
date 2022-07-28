package Easy.二叉搜索树中的搜索;

public class SearchinABinarySearchTree {
    // 递归实现
    public TreeNode searchBST(TreeNode root, int val) {
        if(root==null){
            return null;
        }
        if(root.val>val){
            return searchBST(root.left, val);
        }
        if(root.val<val){
            return searchBST(root.right, val);
        }
        return root;
    }
    // 非递归实现
    public TreeNode searchBST2(TreeNode root, int val) {
        TreeNode node = root;
        while (node!=null){
            if (node.val>val){
                node = node.left;
            }else if (node.val<val){
                node = node.right;
            }else {
                return node;
            }
        }
        return null;
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
