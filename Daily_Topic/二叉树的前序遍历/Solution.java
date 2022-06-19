package Daily_Topic.二叉树的前序遍历;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * 给定一个二叉树，返回它的 前序 遍历。
 * <p>
 * 示例：
 * <p>
 * 输入: [1,null,2,3]
 * 1
 * \
 * 2
 * /
 * 3
 * <p>
 * 输出: [1,2,3]
 *
 * 根左右。
 *
 * @author 唐
 * @date 2020/10/27 0027
 * @time 8:47
 */
public class Solution {

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = null;
        TreeNode t3 = new TreeNode(2);
        TreeNode t4 = new TreeNode(3);
        t1.left=  t2;
        t1.right = t3;
        t3.left = t4;
        List<Integer> list = preorderTraversal(null);
        System.out.println(list);
    }

    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        preOrder(root, res);
        return res;
    }
     public static void preOrder(TreeNode root, List<Integer> res){
         if (root==null) return;
         res.add(root.val);
         // 上面有判空操作，不需要再进行二次判断
         preOrder(root.left, res);
         preOrder(root.right, res);
     }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {}

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}