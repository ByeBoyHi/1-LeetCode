package 动态规划.中等.单词拆分;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("leet");
        list.add("code");
        System.out.println(new Solution().wordBreak("leetcode", list));
    }

    /**
     * 思路：DFS实现，深度优先遍历
     * 从头遍历，然后挨从start到i进行单词的判断，如果在里面，就递归剩下的单词是否在里面。
     * 退出条件是当递归传入的值等于字符串的长度的时候，就说明已经递归结束了，没有别的字串了，可以返回true，否则返回false。
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        int[] flags = new int[s.length()];
//        return bfs(s, wordDict, flags);
        return dfs(s, wordDict, 0, flags);
    }

    /**
     * "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab"
     * ["a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"]
     * 上面的用例 DFS 超时
     * 加一个数组，进行记忆化处理。对于重复操作过的索引值进行存储。
     * int数组中：0表示初始态、1表示成功态、-1表示失败态。
     */
    public boolean  dfs(String s, List<String> wordDict, int start, int[] flags) {
        if (start == s.length()) {
            return true;
        }
        if (flags[start] != 0) {
            return flags[start] == 1;
        }
        for (int i = start; i < s.length(); i++) {
            String str = s.substring(start, i + 1);
            if (wordDict.contains(str) && dfs(s, wordDict, i + 1, flags)) {
                flags[start] = 1;
                return true;
            }
        }
        flags[start] = -1;
        return false;
    }

    // 广度优先遍历：带记忆性 ———— visited
    public boolean bfs(String s, List<String> wordDict, int[] visited) {
        List<Integer> queue = new ArrayList<>();
        queue.add(0);
        while (!queue.isEmpty()) {  // 所有可能性的标记都取完了，也没有新的标记进来，说明拼接失败。
            int start = queue.get(0);  // 获取当前标记后
            queue.remove(0);  // 就删除当前标记，这样下次取值就是新的标记，也可以在外面用一个遍历来记录取值索引
            // 如果访问过了，跳过本次循环，避免重复访问
            if (visited[start] == 1) continue;
            // 记录本次已经访问过
            visited[start] = 1;
            for (int i=start+1; i<=s.length(); i++){  // 因为i是用于单词的切割，所以要取等于，然后取到最后一个字母
                String str = s.substring(start, i);
                if (wordDict.contains(str)){
                    if (i<s.length()){
                        // 把单词位置记录下来
                        queue.add(i);
                    }else {  // 这里如果越界了，说明之前的划分都是单词，因为只有单词才能进入这个if里面。
                        return true;
                    }
                }
            }
        }
        // 如果所有的遍历结束都没有合理的单词，那么说明无法拼接成功
        return false;
    }

    /**
     * 动态规划：
     * 对于字串 0~i，可以分为 0~i 和 i+1~j。
     * 那么如果 0~i 为 true，且 i+1~j 为 true，那么 0~i 为 true。
     */
    public boolean wordBreak1(String s, List<String> wordDict){
        int[] flags = new int[s.length()+1];
        flags[0] = 1;
        for (int i=1; i<=s.length(); i++){  // 从头遍历所有子串，是否有可能单词，这里取1到len是因为取子串的函数是 ”左闭右开“ 的。
            for (int j=i-1; j>=0; j--){     // 从 i-1到0 的方向形成子串，判断是否是单词
                if (flags[i]==1) break;     // 如果i是可构成子串，那么j~i是没必要判断的 直接break。
                if (flags[j]!=1) continue;  // 如果j是无法构成子串的，那么if肯定进不去，这一次循环没必要进行。
                String str = s.substring(j, i);  // 取这部分子串，进行字典单词的判断
                if (wordDict.contains(str)){
                    flags[i] = 1;
                    break;  // 找到子单词，看下一个
                }
            }
        }
        // 最后只需要返回最后一个值是否是1，如果是1，说明前面的值都是可以构成子串的。
        return flags[flags.length-1]==1;
    }
}
