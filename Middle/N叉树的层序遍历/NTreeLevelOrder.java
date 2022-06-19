package Middle.N叉树的层序遍历;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class NTreeLevelOrder {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root==null) return ans;

        Queue<Node> queue = new ArrayDeque<>();
        Node end = root;
        queue.add(root);
        while (!queue.isEmpty()){
            List<Integer> cur = new ArrayList<>();
            Node newEnd = null;
            while (!queue.isEmpty()){
                Node node = queue.poll();
                cur.add(node.val);

                List<Node> children = node.children;

                if (node.children.size()!=0) {
                    newEnd = node.children.get(children.size()-1);
                }
                queue.addAll(children);

                if (node==end) break;
            }
            end = newEnd;
            ans.add(cur);
        }
        return ans;
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
