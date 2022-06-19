package Middle.键值映射;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Solution {

}

// 暴力破解
class MapSum {
    Map<String, Integer> map;
    public MapSum() {
        map = new HashMap<>();
    }

    public void insert(String key, int val) {
        map.put(key, val);
    }

    public int sum(String prefix) {
        Set<String> strings = map.keySet();
        int ans = 0;
        for (String str: strings){
            if (str.startsWith(prefix)){
                ans+=map.get(str);
            }
        }
        return ans;
    }
}

// 字典树
class MapSum2{
    static class TrieNode{
        int val = 0;
        TrieNode[] next = new TrieNode[26];  // 26个字母
    }

    TrieNode node;
    Map<String, Integer> map;
    public MapSum2() {
        node = new TrieNode();
        map = new HashMap<>();
    }

    public void insert(String key, int val) {
        int delta = val - map.getOrDefault(key, 0);  // 如果有覆盖，计算出差值
        map.put(key, val);
        TrieNode root = node;
        for (char c: key.toCharArray()){
            if (root.next[c-'a']==null){
                root.next[c-'a'] = new TrieNode();
            }
            root = root.next[c-'a'];
            root.val += delta;
        }
    }

    public int sum(String prefix) {
        // 去字典树里面找
        TrieNode root = node;
        for (char c:prefix.toCharArray()){
            if (root.next[c-'a']==null){
                return 0;
            }
            root = root.next[c-'a'];
        }
        return root.val;  // 找到最后一个字母，进行返回对应的值
    }
}

