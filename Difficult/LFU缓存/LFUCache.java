package Difficult.LFU缓存;

import java.util.*;

public class LFUCache {

    public static void main(String[] args) {
        Set<Integer> list = new TreeSet<>((a, b) -> b - a);
        list.add(5);
        list.add(6);
        list.add(1);
        list.add(8);
        System.out.println(list);
    }

    /*
        1. 记录当前容量多少
        2. 记录当前时间戳
        3. 总容量
        4. 表一：存储key value
        5. 表二：存储count,timestamp key
        6. 表三：存储key count,timestamp
     */
    private int size;
    private int timestamp;
    private final int capacity;
    private final Map<Integer, Integer> kv;
    private final TreeMap<Info, Integer> kc;
    private final HashMap<Integer, Info> ck;

    public LFUCache(int capacity) {
        size = 0;
        timestamp = 0;
        this.capacity = capacity;
        kv = new HashMap<>();
        // kc的第一个元素一定是使用次数最少的，或者使用次数一样少的里面，最久没使用的
        /*
            按照count升序，timestamp升序
         */
        kc = new TreeMap<>((a, b) -> {
            if (a.count == b.count) {
                return a.timestamp - b.timestamp;  // 升序：最久未使用的就是timestamp最小的
            }
            return a.count - b.count;  // 升序
        });
        ck = new HashMap<>();
    }

    public int get(int key) {
        timestamp++;
        if (kv.containsKey(key)) {
            Info info = ck.get(key);
            Info newInfo = new Info(info.count + 1, timestamp);
            ck.put(key, newInfo);
            kc.remove(info);
            kc.put(newInfo, key);
            return kv.get(key);
        }
        return -1;
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
        timestamp++;
        if (kv.containsKey(key)) {  // 如果已经有这个值，那么直接更新
            kv.put(key, value);  // 更新值
            Info info = ck.get(key);
            Info newInfo = new Info(info.count + 1, timestamp);
            kc.remove(info);
            kc.put(newInfo, key);
            ck.put(key, newInfo);

        } else { // 如果没有这个值
            if (size == capacity) {  // 如果空间满了，需要弹出最近最久未使用的值
                Info info = kc.firstKey();
                int k = kc.get(info);
                ck.remove(k);
                kv.remove(k);
                kc.remove(info);

            } else {
                size++;
            }
            // 否则直接进去
            Info info = new Info(1, timestamp);
            kc.put(info, key);
            ck.put(key, info);
            kv.put(key, value);
        }
    }
}

class Info {
    int count, timestamp;

    public Info() {
    }

    public Info(int count, int timestamp) {
        this.count = count;
        this.timestamp = timestamp;
    }
}

