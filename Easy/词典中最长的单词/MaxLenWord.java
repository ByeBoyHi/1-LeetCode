package Easy.词典中最长的单词;

import java.util.*;

public class MaxLenWord {
    public String longestWord(String[] words) {
        Arrays.sort(words, (a, b)->{
            if (a.length()==b.length()){
                return b.compareTo(a);
            }else {
                return a.length()-b.length();
            }
        });
        Set<String> set = new HashSet<>();
        String ans = "";
        set.add(ans);

        for (String word : words) {
            if (set.contains(word.substring(0, word.length() - 1))) {
                set.add(word);
                ans = word;
            }
        }

        return ans;
    }
}

// 字典树学习
class Trie{
    Trie[] children;
    boolean isEnd;

    public Trie() {
        children = new Trie[26]; // 26个字母
        isEnd = false; // 默认不是结束节点
    }

    // 插入数据
    public void insert(String str){
        Trie node = this;  // 取到当前树，因为是在当前树插入数据
        for (int i=0; i<str.length(); i++){
            int child = str.charAt(i)-'a';
            if (node.children[child]==null){
                node.children[child] = new Trie();
            }
            node = node.children[child];
        }
        node.isEnd = true; // 走到孩子的最后一个节点
    }

    // 查询数据：返回是否查询到数据
    public boolean search(String str){
        Trie node = this;
        for (int i=0; i<str.length(); i++){
            int child = str.charAt(i)-'a';
            /*
                1. 没有这个孩子节点
                2. 这个孩子节点必须是结束节点
                    因为我们要找的是挨个递增存在的子序列，所以在找长的串的时候。
                    比他短的子串也要存在，那么这个长的串才符合条件
             */
            if (node.children[child]==null || !node.children[child].isEnd){
                return false;
            }
            node = node.children[child];
        }
        return true;
    }

}

class Test{
    public String longestWord(String[] words) {
        String ans = "";
        Trie trie = new Trie();
        for (String word: words){
            trie.insert(word);
        }

        for (String str: words){
            if (trie.search(str)){ // 在这个字符串合理的情况下
                /*
                    1. 这个字符串长度比ans长
                    2. 或者：
                        2.1 长度一样的情况下
                        2.2 这个字符串的字典序更小
                    以上情况更新ans
                 */
                if ((str.length()>ans.length())
                ||
                    (str.length()==ans.length() && str.compareTo(ans)>0))
                {
                    ans = str;
                }
            }
        }

        return ans;
    }
}
