package Middle.奇偶树;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

public class EvenOddTree {

    public boolean isEvenOddTree(TreeNode root) {

        // 用一个队列，两个指针，一个指针记录当前层的最后一个节点，另一个指针用来记录下一层的节点的最后一个
        // 队列来存储节点，指针指向当前层的最后一个节点
        // 在到达最后一个节点之前，判断当前层是否符合规则

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int level = 0;
        TreeNode end = root;
        TreeNode nextEnd = null;
        while (!queue.isEmpty()){
            TreeNode cur = queue.poll();

            if ((level&1)==0){  // 偶数层：奇数且严格递增
                // 在不是当前层的最后一个节点的时候，我们才有必要去判断是否和下一个相邻节点有关系
                if ((cur.val&1)==0 || (!queue.isEmpty() && cur!=end && queue.peek().val<=cur.val)){
                    return false;
                }
            }

            if ((level&1)==1){  // 奇数层：偶数且严格递减
                if ((cur.val&1)==1 || (!queue.isEmpty() && cur!=end && queue.peek().val>=cur.val)){
                    return false;
                }
            }

            if (cur.left!=null){
                queue.add(cur.left);
                nextEnd=cur.left;
            }
            if (cur.right!=null){
                queue.add(cur.right);
                nextEnd=cur.right;
            }
            if (cur==end){
                level++;
                end = nextEnd;
            }

        }
        return true;
    }

    // 广度优先搜索
    // 最开始的第一层只有一个节点
    // 用一个变量来存储当前层的节点个数，每次只访问这么多个节点
    // 对于当前层的节点，应当遵守的当前层的规则
    // 用一个队列来存储
    // 偶数层：严格递增，奇数
    // 奇数层：严格递减，偶数
    public boolean isEvenOddTree2(TreeNode root){
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int level = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            int prev = (level&1)==0?Integer.MIN_VALUE:Integer.MAX_VALUE;
            for (int i=0; i<size; i++){
                TreeNode cur = queue.poll();
                assert cur != null;
                if ((level&1)==(cur.val&1)){  // 层数和数字同奇偶
                    return false;
                }
                if (((level&1)==0 && cur.val<=prev)  // 偶数层
                        ||
                        ((level&1)==1 && cur.val>=prev)){  // 奇数层
                    return false;
                }
                prev = cur.val;
                if (cur.left!=null){
                    queue.offer(cur.left);
                }
                if (cur.right!=null){
                    queue.offer(cur.right);
                }
            }
            level++;
        }
        return true;
    }

    // 用一个数组来代替上面的Deque，速度更快
    public boolean isEvenOddTree3(TreeNode root) {
        TreeNode[] nodeStack = new TreeNode[100000];
        int level = -1;
        int top = -1;
        nodeStack[++top] = root;
        int currPos = -1;
        while (currPos < top) {
            int size = top - currPos;
            int lastVal = -1;
            level++;
            while ((--size) >= 0) {
                TreeNode node = nodeStack[++currPos];
                assert node != null;
                if ((level + node.val) % 2 == 0)
                    return false;
                if (lastVal != -1) {
                    if (level % 2 == 0) {
                        if (lastVal >= node.val)
                            return false;
                    } else {
                        if (lastVal <= node.val)
                            return false;
                    }
                }
                lastVal = node.val;
                if (node.left != null)
                    nodeStack[++top] = node.left;
                if (node.right != null)
                    nodeStack[++top] = node.right;
            }
        }
        return true;
    }

    static class TreeNode {
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

}


