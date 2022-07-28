package Easy.相同的树;

public class SameTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (q==null && p==null){
            return true;
        }
        if (q==null||p==null){
            return false;
        }
        if (p.val==q.val){
            if (isSameTree(p.left, q.left)){
                return isSameTree(p.right, q.right);
            }
        }
        return false;
    }
}

class TreeNode{
    int val;
    TreeNode left, right;

    public TreeNode() {}

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
