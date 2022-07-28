package Middle.前缀树;

public class Solution {
}


// Trie，又称前缀树或字典树，是一棵有根树，其每个节点包含以下字段：
// 指向子节点的指针数组 children。对于本题而言，数组长度为 26，即小写英文字母的数量。
// 此时 children[0] 对应小写字母 a，children[1] 对应小写字母 b，... ，children[25] 对应小写字母 z。
// 布尔字段 isEnd，表示该节点是否为字符串的结尾。
class Trie{
    private Trie[] children;
    private boolean isEnd;

    public Trie(){
        children = new Trie[26];
        isEnd = false;
    }

    /**
     * 插入一个字符串到前缀树里面
     * @param word 所插入的单词
     */
    public void insert(String word){
        Trie node = this;
        for (int i=0; i<word.length(); i++){
            int cur = word.charAt(i)-'a';
            if (node.children[cur]!=null){
                node.children[cur] = new Trie();
            }
            node = node.children[cur];
        }
        node.isEnd = true;  // 有这么一个顺序的单词
    }

    /**
     * 在前缀树里面寻找对应的前缀字符串
     * @param prefix 需要寻找的前缀
     * @return  返回对应的 '前缀终点' 或者 null
     */
    private Trie searchPreFix(String prefix){
        Trie node = this;
        for (int i=0; i<prefix.length(); i++){
            int cur = prefix.charAt(i)-'a';
            if (node.children[cur]==null){
                return null;
            }
            node = node.children[cur];
        }
        return node;
    }

    /**
     * 搜索这个前缀树里面是否有对应的前缀
     * @param prefix 传入的需要搜索的前缀
     * @return  有的话返回 true，否则返回 false
     */
    public boolean startsWith(String prefix){
        return searchPreFix(prefix)==null;
    }

    /**
     * 判断前缀树里面是否有这个单词
     * @param word 传入对应需要查询的单词
     * @return  先判断这个节点是否为空，然后判断这个终点是否是End为True。
     */
    public boolean search(String word){
        Trie node = searchPreFix(word);
        return node!=null && node.isEnd;
    }

    /**
     * 判断是否有以这个字母为结束的单词
     * @return true是，false不是
     */
    public boolean isEnd(){
        return this.isEnd;
    }

    // getter
    public Trie[] getChildren(){
        return this.children;
    }
}
