package Middle.二叉树的锯齿形层序遍历;

import java.util.*;

public class ZigZagTraverse {
    /*
        1. 按行存储在每一个list里面
        2. 按照需求对偶数行进行逆序
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root==null) return ans;
        TreeNode end;  // 记录这一层的开始节点和结束节点位置
        Queue<TreeNode> stack = new ArrayDeque<>();
        stack.add(root);
        end = root;
        while (!stack.isEmpty()){
            TreeNode newEnd = null;
            TreeNode cur;
            List<Integer> list = new ArrayList<>();
            do {
                cur = stack.poll();
                if (cur.left!=null){
                    newEnd = cur.left;
                    stack.add(cur.left);
                }
                if (cur.right!=null){
                    newEnd = cur.right;
                    stack.add(cur.right);
                }
                list.add(cur.val);
                if (stack.isEmpty()) break;
            }while (cur!=end);  // do while：只要这个条件成立，就不断执行！！！！
            ans.add(list);
            end = newEnd;
        }
        boolean isOK = false;
        for (List<Integer> c: ans){
            if (isOK){
                Collections.reverse(c);
            }
            isOK = !isOK;
        }
        return ans;
    }

    // 双端队列
    public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root==null) return ans;

        Deque<TreeNode> link = new LinkedList<>();
        boolean isLeft = false;
        link.offer(root);
        while (!link.isEmpty()){
            int size = link.size();
            LinkedList<Integer> cur = new LinkedList<>();
            for (int i=0; i<size; i++){
                TreeNode node = link.poll();
                assert node!=null;
                if (isLeft){
                    cur.offerLast(node.val);
                }else {
                    cur.offerFirst(node.val);
                }
                if (node.left!=null){
                    link.add(node.left);
                }
                if (node.right!=null){
                    link.add(node.right);
                }
            }
            isLeft = !isLeft;
            ans.add(cur);
        }
        return ans;
    }
}

class TreeNode{
    int val;
    TreeNode left, right;
    public TreeNode(){}

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
