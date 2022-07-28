package Easy.根据二叉树创建字符串;

public class BuildByTreeToStr {
    // 树的遍历问题
    /*
        第一次来到这个节点，加右括号和这个数字
        第二次来到这个数字，加左括号
     */
    public String tree2str(TreeNode root) {
        if (root == null) return "";
        return root.val + process(root.left) + process(root.right) ;
    }

    public String process(TreeNode root) {
        String ans = "(" + root.val;
        if (root.left == null && root.right != null) {
            ans += "()";
        } else if (root.left != null) {
            ans += process(root.left);
        }
        if (root.right != null) {
            ans += process(root.right);
        }
        ans += ")";
        return ans;
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
