package Easy.N叉树的后序遍历;

import java.util.*;

public class NTree{

    // 后序遍历：先走孩子，孩子顺子从左往右。
    public List<Integer> postorder(Node root) {
        List<Integer> ans = new LinkedList<>();
        if (root==null) return ans;
        // 这个遍历只会把他的所有孩子加进去
        process(ans, root);
        ans.add(root.val);
        return ans;
    }

    public void process(List<Integer> ans, Node root){
        if (root==null) return;
        for (Node child: root.children){
            process(ans, child);
            // 如果这个孩子已经没有孩子节点了，那么会马上回来
            ans.add(child.val);
        }
    }

    // 逆转实现
    /*
        例如 v有v1 v2 v3三个节点
        那么 正常前序遍历：v v1 child_v1 v2 child_v2 v3 child_v3
        我们需要： child_v1 v1 child_v2 v2 child_v3 v3 v
        我们可以在前序遍历的时候把根左右换成根右左，利用栈的特性
            v v3 child_v3 v2 child_v2 v1 child_v1
        然后对结果进行逆序即可：
            child_v1 v1 child_v2 v2 child_v3 v3 v
     */
    public List<Integer> postorder2(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<Node> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            res.add(node.val);  // 根部进去
            // 节点从左往右入栈
            // 出来的时候，存储顺序其实就是从右往左
            for (Node item : node.children) {
                stack.push(item);
            }
        }
        // 逆序
        Collections.reverse(res);
        return res;
    }

    // 非递归：有时间研究
    public List<Integer> postorder3(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Map<Node, Integer> map = new HashMap<>();
        Deque<Node> stack = new ArrayDeque<>();
        Node node = root;
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                stack.push(node);
                List<Node> children = node.children;
                if (children != null && children.size() > 0) {
                    map.put(node, 0);
                    node = children.get(0);
                } else {
                    node = null;
                }
            }
            node = stack.peek();
            int index = map.getOrDefault(node, -1) + 1;
            assert node != null;
            List<Node> children = node.children;
            if (children != null && children.size() > index) {
                map.put(node, index);
                node = children.get(index);
            } else {
                res.add(node.val);
                stack.pop();
                map.remove(node);
                node = null;
            }
        }
        return res;
    }
}

class Node{
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int val) {
        this.val = val;
    }

    public Node(int val, List<Node> children) {
        this.val = val;
        this.children = children;
    }
}
