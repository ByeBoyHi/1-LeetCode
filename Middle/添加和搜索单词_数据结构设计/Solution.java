package Middle.添加和搜索单词_数据结构设计;


public class Solution {

}

class WordDictionary {
    // 使用前缀是实现
    Trie trie;
    public WordDictionary() {
        trie = new Trie();
    }

    public void addWord(String word) {
        trie.insert(word);
    }

    public boolean search(String word) {
        return dfs(word, 0, trie);
    }

    private boolean dfs(String word, int index, Trie node){
        if (index == word.length()){  // 递归到word长度的深度了，可以直接返回是否是结束节点了
            return node.isEnd();
        }

        char ch = word.charAt(index);
        if (Character.isLetter(ch)){  // 如果这是一个字母
            Trie child = node.getChildren()[ch-'a'];
            // 这里递归传入的是child，因为你接下来是在child为根的这棵树里面找下一个字母是否存在
            // 如果还是在root这个位置查找字母的话，显然和word的字母需求是不对应的
            if (child != null && dfs(word,index+1, child)){  // 递归下一个字母
                return true;
            }
        }else{  // 如果这是一个小数点
            // 因为小数点可以是任意字母，所以要递归26个位置
            for (int i=0; i<26; i++){
                Trie child = node.getChildren()[i];
                if (child!=null && dfs(word, index+1, child)){
                    return true;
                }
            }
        }
        return false;
    }
}

class Trie{
    private Trie[] children;
    private boolean isEnd;

    public Trie(){
        children = new Trie[26];
        isEnd = false;
    }

    public Trie[] getChildren() {
        return children;
    }

    public void setEnd(boolean end) {
        isEnd = end;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void insert(String word){
        Trie node = this;
        for (int i=0; i<word.length(); i++){
            int index = word.charAt(i)-'a';
            /**
             * 这里的 node.getChildren()[index] 不能直接先用 Trie 接收。
             * 为 null 的时候接受不就是一个 null吗？？为什么不能
             */
            if (node.getChildren()[index]==null){
                node.getChildren()[index] = new Trie();
            }
            node = node.getChildren()[index];
        }
        node.setEnd(true);
    }
}