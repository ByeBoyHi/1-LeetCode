package Easy.设计哈希集合;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

public class MyHashSet {

    Set<Integer> list;

    public MyHashSet() {
        list = new HashSet<>();
    }

    public void add(int key) {
        list.add(key);
    }

    public void remove(int key) {
        list.remove(key);
    }

    public boolean contains(int key) {
        return list.contains(key);
    }
}

class MyHashSet1 {

    private static final int BASE = 769; // 一个合理的质数，把对应的数字放入对应的桶里面
    private LinkedList<Integer>[] data = new LinkedList[BASE];

    public MyHashSet1() {
        for (int i = 0; i < BASE; i++) {
            data[i] = new LinkedList<Integer>();
        }
    }

    public void add(int key) {
        int hash = hash(key);
        for (Integer integer : data[hash]) {
            if (integer == key) {
                return;
            }
        }
        data[hash].add(key);
    }

    public void remove(int key) {
        data[hash(key)].remove((Object)key);
    }

    public boolean contains(int key) {
        return data[hash(key)].contains((Object)key);
    }

    private int hash(int key) {
        return key % BASE;
    }
}
