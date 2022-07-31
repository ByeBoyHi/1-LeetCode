package Middle.最大层内元素和;

import java.util.ArrayDeque;
import java.util.Queue;

public class maxNumOfLevel {
    /**
     * 层序遍历，每次都记录这一层的总数，更新最大值和层号
     * 需要记录如下数据：
     *      1. 当前层的终点
     *      2. 下一层的终点
     *      3. 当前层的数量
     *      4. 最大的数量
     *      5. 最大数量的层号
     *      6. 当前层号
     * @param root
     * @return
     */
    public int maxLevelSum(TreeNode root) {
        if (root.left==null && root.right==null) return 1;
        TreeNode curEnd = root;
        TreeNode nextEnd = null;
        int curNum = 0;
        int maxNum = Integer.MIN_VALUE;
        int maxLevel = 0;
        int curLevel = 1;

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()){
             TreeNode node = queue.poll();
             curNum+=node.val;
             if (node.left!=null){
                 nextEnd = node.left;
                 queue.offer(node.left);
             }
             if (node.right!=null){
                 nextEnd = node.right;
                 queue.offer(node.right);
             }
             if (node==curEnd){
                 curEnd = nextEnd;  // 更新下一层的终点
                 if (curNum>maxNum){
                     maxNum = curNum;
                     maxLevel = curLevel;  // 当前层是最大数量的层，更新max
//                     curLevel++;
                 }
                 curLevel++; // 来到新的一层（和max是否更新无关）
                 curNum = 0; // 来到新的一层，需要情况上一层的数据
             }
        }

        return maxLevel;

    }
}

class TreeNode{
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
