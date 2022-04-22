package Easy.对称二叉树;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Symmetric {
    List<Integer> left = new ArrayList<>();
    List<Integer> right = new ArrayList<>();
    public boolean isSymmetric(TreeNode root) {
        process1(root.left);
        process2(root.right);
        if (left.size()!=right.size()) return false;
        for (int i=0; i<left.size(); i++){
            if (!Objects.equals(left.get(i), right.get(i))) return false;
        }
        return true;
    }

    // 直接一棵树当作两棵树都传进去
    // 判断这个树的左边是否等于另一个树的右边即可
    public boolean isMirror(TreeNode node1, TreeNode node2){
        if (node1==null && node2==null) return true;
        if (node1==null || node2==null) return false;
        return node1.val== node2.val
                && isMirror(node1.left, node2.right)
                && isMirror(node1.right, node2.left);
    }

    // 左右子树进行相反遍历
    // 左子树：根左右
    // 右子树：根右左
    // 判断两个序列是否相等
    // 左子树遍历
    public void process1(TreeNode node){
        if (node == null) {  // 空节点也要处理一下
            left.add(-1);
            return;
        }
        left.add(node.val);
        // 左子树
        process1(node.left);
        // 右子树
        process1(node.right);
    }
    // 右子树遍历
    public void process2(TreeNode node){
        if (node==null) {
            right.add(-1);
            return;
        }
        right.add(node.val);
        // 右子树
        process2(node.right);
        // 左子树
        process2(node.left);
    }
}

class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;

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
