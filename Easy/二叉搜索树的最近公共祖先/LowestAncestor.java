package Easy.二叉搜索树的最近公共祖先;

public class LowestAncestor {
    /*
        对于BST：
            1. 如果当前节点在 p q 之间，那么当前节点就是LCA。
            2. 如果当前节点大于 p q，那么去左子树。
            3. 如果当前节点小于 p q，那么去右子树。
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root.val>=p.val && root.val<=q.val
        ||  root.val>=q.val && root.val<=p.val) {  // 1. 在两点之间
            return root;
        }
        if (root.val>q.val) {  // 2
            return lowestCommonAncestor(root.left, p, q);
        }
        else {  // 3
            return lowestCommonAncestor(root.right, p, q);
        }
    }
}

class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }
}
