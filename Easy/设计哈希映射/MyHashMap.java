package Easy.设计哈希映射;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

public class MyHashMap {

    Node root;
    Map<Integer, Node> map;

    public MyHashMap() {
        root = new Node();
        map = new HashMap<>();
    }

    public void put(int key, int value) {
        map.put(key, new Node(value));
    }

    public int get(int key) {
        return map.getOrDefault(key,new Node(-1)).val;
    }

    public void remove(int key) {
        map.remove(key);
    }
}

class Node{
    int val;
    public Node(){}
    public Node(int val) {
        this.val = val;
    }
}

class MyHashMap2 {

    private static final int BASE = 769;
    private LinkedList<map>[] lists;

    public MyHashMap2() {
        lists = new LinkedList[BASE];
        for (int i=0; i<BASE; i++){
            lists[i] = new LinkedList<>();
        }
    }

    public void put(int key, int value) {
        int hash = hash(key);
        for (map next : lists[hash]) {
            if (next.key == key) {
                next.value = value;
                return;
            }
        }
        lists[hash].add(new map(key, value));
    }

    public int get(int key) {
        int hash = hash(key);
        for (map e: lists[hash]){
            if (e.key==key){
                return e.value;
            }
        }
        return -1;
    }

    public void remove(int key) {
        int hash = hash(key);
        map e = null;
        for (map next : lists[hash]) {
            if (next.key == key) {
                e = next;
                break;
            }
        }
        lists[hash].remove(e);
    }

    private int hash(int key){
        return key%BASE;
    }

}

class map{
    int key;
    int value;
    public map(){}

    public map(int key, int value) {
        this.key = key;
        this.value = value;
    }
}
