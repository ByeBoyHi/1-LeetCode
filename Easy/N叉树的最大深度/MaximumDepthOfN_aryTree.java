package Easy.N叉树的最大深度;

import java.util.List;

public class MaximumDepthOfN_aryTree {

    public int maxDepth(Node root) {
        if(root==null) return 0;
        int ans = 0;
        for (int i=0; i<root.children.size(); i++){
            int cur = maxDepth(root.children.get(i));  // 孩子的深度
            ans = Math.max(ans, cur);
        }
        // 所有孩子的深度的最大值，加上当前层
        return ans+1;
    }
}

class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};