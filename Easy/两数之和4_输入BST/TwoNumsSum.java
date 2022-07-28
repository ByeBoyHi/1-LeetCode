package Easy.两数之和4_输入BST;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TwoNumsSum {

    // 存放所有比target小的数字
    Set<Integer> set = new HashSet<>();
    // 中序遍历
    List<Integer> list = new ArrayList<>();

    public boolean findTarget(TreeNode root, int k) {
        process(root, k);
        for (int i:set){
            if (i*2!=k && set.contains(k-i)){
                return true;
            }
        }
        return false;
    }

    public void process(TreeNode node, int target){
        if (node==null) return;
        set.add(node.val);
        process(node.left, target);
        process(node.right, target);
    }

    // 双指针
    public boolean findTarget2(TreeNode root, int k) {
        process2(root, k);
        int left = 0, right = list.size()-1;
        while (left<right){
            if (list.get(left)+list.get(right)==k){
                return true;
            }else if (list.get(left)+list.get(right)>k){
                right--;
            }else {
                left++;
            }
        }
        return false;
    }
    public void process2(TreeNode node, int target){
        if (node==null) return;
        process2(node.left, target);
        list.add(node.val);
        process2(node.right, target);
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
