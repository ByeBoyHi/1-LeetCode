package Difficult.全O1的数据结构;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Designing a structure which store a count of string, and returning maximum and minimum.
 * About count and character sorted.
 */
public class AllOne {

    Map<String, Node> map;  // 用来记录这个字符串在双向链表中的位置，存储他所在的节点的信息
    Node root = new Node();  // 头节点作为空，来指向前后

    public AllOne() {
        map = new HashMap<>();
        root.nxt = root;
        root.pre = root;
    }

    // the count of 'key' plus one.
    public void inc(String key) {
        if (map.containsKey(key)) {
            Node cur = map.get(key);
            Node nxt = cur.nxt;
            // 如果下一个节点是根或者下一个节点的计数更大
            if (nxt==root || nxt.count > cur.count + 1) {
                map.put(key, cur.insert(new Node(key, cur.count+1)));
            } else {
                nxt.keys.add(key);
                map.put(key, nxt);
            }
            cur.keys.remove(key);
            if (cur.keys.isEmpty()){
                cur.remove();
            }
        } else {
            if (root.nxt == root || root.nxt.count > 1) {  // 如果是一个空表，或者第一个节点的计数不是1
                map.put(key, root.insert(new Node(key, 1)));
            } else {
                root.nxt.keys.add(key);
                map.put(key, root.nxt);
            }
        }
    }

    // the count of 'key' minus one.
    public void dec(String key) {
        Node cur = map.get(key);
        if (cur.count==1){
            map.remove(key);
        }else {
            if (cur.pre==root || cur.pre.count< cur.count-1) {
                map.put(key, cur.pre.insert(new Node(key, cur.count - 1)));
            } else {
                cur.pre.keys.add(key);
                map.put(key, cur.pre);
            }
        }
        cur.keys.remove(key);
        if (cur.keys.isEmpty()){
            cur.remove();
        }
    }

    // max count
    public String getMaxKey() {
        return root.pre == root ? "" : root.pre.keys.iterator().next();
    }

    // min count
    public String getMinKey() {
        return root.nxt == root ? "" : root.nxt.keys.iterator().next();
    }
}

/*
    双向链表：
        从小到大排序，用一个哨兵root来链接头和尾。
    1. 记录计数为count的所有字符串的集合
    2. 指向下一个节点和上一个节点
 */
class Node {
    Set<String> keys = new HashSet<>();
    int count;
    Node pre, nxt;

    public Node() {
        this("", 0);
    }

    public Node(String key, int count) {
        keys.add(key);
        this.count = count;
    }
    // 在当前节点后面插入node节点
    public Node insert(Node node){
        node.pre = this;
        node.nxt = this.nxt;
        node.pre.nxt = node;
        node.nxt.pre = node;
        return node;
    }

    // 当自己的集合里面没有元素的时候，删除自己
    public void remove(){
        this.pre.nxt = this.nxt;
        this.nxt.pre = this.pre;
    }
}

