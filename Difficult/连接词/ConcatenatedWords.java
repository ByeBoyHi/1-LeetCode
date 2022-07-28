package Difficult.连接词;

import java.util.*;
import java.util.stream.Collectors;

// 字典树+深度优先
public class ConcatenatedWords {

    Trie trie = new Trie();  // 字典树成员，里面的内容是通过insert更新的

    /**
     * 把words里面是连接词的单词都放入结果集合
     * @param words     单词表
     * @return          结果集合
     * 思路：
     *  1. 对所有单词按照长度从小到大排序
     *  2. 保证短的单词一定比长的单词先判断
     *  3. 如果一个单词是连接词，放入结果集合，否则放入字典树
     *  4. 通过dfs的方式，判断这个单词是否是连接词，即在字典树里面是否能找到多个子单词把他拼接上
     */
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        if (words.length<2){
            return new ArrayList<>();
        }
        Arrays.sort(words, Comparator.comparingInt(String::length));
        List<String> ans = new ArrayList<>();
        for (String word: words){
            if (word.length()==0){
                continue;
            }
            // 加一个记忆化
            // 如果已经访问过，就不再访问
            // 避免重复回溯
            boolean[] visited = new boolean[word.length()];
            if (dfs(word, 0, visited)){
                ans.add(word);
            }else {
                insert(word);
            }
        }
        return ans;
    }

    /**
     * 从一个单词的开头开始往后判断，在字典树中是否有这个单词的子成员
     * @param word      判断的单词
     * @param start     单词的开始位置
     * @param visited   记录该单词是否有访问过，避免重复回溯
     * @return          返回是否有子单词
     */
    private boolean dfs(String word, int start, boolean[] visited){
        if (start==word.length()){
            return true;
        }
        if (visited[start]){
            return false;
        }
        visited[start] = true;
        Trie node = trie;
        for (int i=start; i<word.length(); i++){
            char ch = word.charAt(i);
            int index = ch-'a';
            if (node.children[index]==null){
                return false;
            }
            node = node.children[index];  // 链接到新的字符
            if (node.isEnd){  // 找到当前连接词
                if (dfs(word, i+1, visited)){
                    return true;
                }
            }
        }
        // 上面整个过程都没有找到合适的连接子词，说明这个单词从头到start都不是连接词
        return false;
    }


    // 插入到字典树中
    private void insert(String word){
        Trie node = trie;
        for (int i=0; i<word.length(); i++){
            char ch = word.charAt(i);
            int index = ch-'a';
            if (node.children[index]==null){
                node.children[index] = new Trie();
            }
            node = node.children[index];
        }
        node.isEnd = true;
    }

}

// 字典树
class Trie{
    Trie[] children;
    boolean isEnd;

    public Trie(){
        children = new Trie[26];
        isEnd = false;
    }
}


// 朴素解法：哈希+dfs+记忆化
class Solution{

    public List<String> findAllConcatenatedWordsInADict(String[] words){
        List<String> ans = new ArrayList<>();
        Set<String> set = Arrays.stream(words).collect(Collectors.toSet());
        Map<Integer, Boolean> memo = new HashMap<>();
        for (String word:words){
            memo.clear();
            if (word.length()!=0 && dfs(word, memo, set, 0)){
                ans.add(word);
            }
        }
        return ans;
    }

    /**
     * 判断word是否能在set集合里面找到多个子单词拼接为当前单词，从start挨个往后判断
     * @param word  需要判断的单词
     * @param memo  记忆化的集合列表
     * @param set   存储的子单词
     * @param start 当前单词的开始位置
     * @return      返回是否能找到多个子单词
     */
    private boolean dfs(String word, Map<Integer, Boolean> memo, Set<String> set, int start){
        if (start==word.length()) return true; // 匹配到结尾都没有false，所以为true
        if (memo.containsKey(start)) return memo.get(start); // 在回溯的时候可能再次来到start位置

        int len = word.length();
        int max = start==0?len-1:len;  // 如果start还是开头，那么就不取到最后，否则取到最后，因为我们始终要留至少两个单词的位置进行拼接
        // 循环判断从start到i的子串是否存在
        for (int i=start+1; i<=max; i++){
            if (set.contains(word.substring(start, i)) && dfs(word, memo, set, i+1)){
                memo.put(start, true);
                return true;
            }
        }
        memo.put(start, false);
        return false;
    }
}


// 哈希值+DP
class Solution2 {
    Set<Long> set = new HashSet<>();
    int P = 131, OFFSET = 128;
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        int n = words.length;
        for (String word : words) {
            long hash = 0;
            for (char c : word.toCharArray()) hash = hash * P + (c - 'a') + OFFSET;
            set.add(hash);
        }
        List<String> ans = new ArrayList<>();
        for (String s : words) {
            if (check(s)) ans.add(s);
        }
        return ans;
    }
    boolean check(String s) {
        int n = s.length();
        int[] f = new int[n + 1];
        Arrays.fill(f, -1);
        f[0] = 0;
        for (int i = 0; i <= n; i++) {
            if (f[i] == -1) continue;
            long cur = 0;
            for (int j = i + 1; j <= n; j++) {
                cur = cur * P + (s.charAt(j - 1) - 'a') + OFFSET;  // 对当前字符不断累加哈希值
                if (set.contains(cur)) f[j] = Math.max(f[j], f[i] + 1);  // 找到一个子单词，就会+1
                if (f[n] > 1) return true;  // 如果最后找到了两个及以上，那么直接返回true，说明是连接词，并且最后一个字符也拼接完成
            }
        }
        return false;
    }
}
