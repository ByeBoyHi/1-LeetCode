package Middle.出现次数最多的子树元素和;

import java.util.*;

public class MostSubtreeSum {
    Map<Integer, Integer> cache = new HashMap<>();
    public int[] findFrequentTreeSum(TreeNode root) {
        process(root); // 预处理
        int max = -1;
        List<Integer> ans = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry: cache.entrySet()){
            if (entry.getValue()>max){
                ans.clear();
                ans.add(entry.getKey());
                max = entry.getValue();
            }else if (entry.getValue()==max){
                ans.add(entry.getKey());
            }
        }
        int[] ret = new int[ans.size()];
        int idx = 0;
        for (int i: ans){
            ret[idx++] = i;
        }
        return ret;
    }

    public int process(TreeNode node){
        if (node==null) return 0;
        int left = process(node.left);  // 左子树的节点总和
        int right = process(node.right); // 右子树的节点总和
        int total = node.val + left + right;
        cache.put(total, cache.getOrDefault(total, 0)+1);  // 当前总和存进缓存里面
        return total;
    }
}


class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
    TreeNode(int val) { this.val = val; }
}
