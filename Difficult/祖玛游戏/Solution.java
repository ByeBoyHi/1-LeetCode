package Difficult.祖玛游戏;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().findMinStep("WWRRBBWW", "RB"));
    }

    private int ans = -1;
    public int findMinStep(String board, String hand) {
        char[] chars = hand.toCharArray();
        Arrays.sort(chars);
        dfs(board, new String(chars), 0, 0, hand.length());
        return this.ans;
    }

    /**
     *
     * @param board  桌面上球的排序字符串，被插入新的球之后的新的顺序，会被更新到下一层递归
     * @param hand   手上的球
     * @param index  当前插入的位置
     * @param curNum 目前已经使用的桌球数
     * @param sum    总共需要插入多少个
     */
    private void dfs(String board, String hand, int index, int curNum, int sum){
        // 终止条件
        if (curNum==sum||board.equals("")){
            if (board.equals("")) {
                if (this.ans != -1) {
                    this.ans = Math.min(curNum, this.ans);
                } else {
                    this.ans = curNum;
                }
            }
            return;
        }
        for (int j=0; j<hand.length(); j++) {  // 手球
            if (j>0 && hand.charAt(j)!=hand.charAt(j-1)) {  // 相邻的球如果相同，递归下去的结果肯定一样。略过
                for (int i = 0; i < board.length(); i++) {  // 桌球
                    if (hand.charAt(j)==board.charAt(i)) {  // 颜色相同的位置插入球
                        // 插入球之后的处理操作
                        String newHand = hand.substring(0, j) + hand.substring(j + 1);
                        String newBroad = clear(board, "" + hand.charAt(j), i);
                        dfs(newBroad, newHand, i, curNum + 1, sum);
                    }
                }
            }
        }
    }

    // 消除操作
    private String clear(String board, String ball, int index){
        String newBroad = board.substring(0,index)+ball+board.substring(index);
        int start;
        int end;
        boolean flag;
        do {
            start = end = 0;
            flag = false;
            for (int i = 1; i < newBroad.length(); i++) {
                if (newBroad.charAt(i) == newBroad.charAt(i - 1)) {
                    end++;
                } else {
                    if (end - start >= 2) {
                        newBroad = newBroad.substring(0, start) + newBroad.substring(end + 1);
                        flag = true;
                        break;
                    }
                    start = end = i;
                }
            }
        } while (flag);
        return newBroad;
    }
}


class Solution2 {
    int INF = 0x3f3f3f3f;
    String b;
    int m;
    Map<String, Integer> map = new HashMap<>();
    public int findMinStep(String a, String _b) {
        b = _b;
        m = b.length();
        int ans = dfs(a, 1 << m);
        return ans == INF ? -1 : ans;
    }
    int dfs(String a, int cur) {
        if (a.length() == 0) return 0;
        if (map.containsKey(a)) return map.get(a);
        int ans = INF;
        int n = a.length();
        for (int i = 0; i < m; i++) {
            if (((cur >> i) & 1) == 1) continue;
            int next = (1 << i) | cur;
            for (int j = 0; j <= n; j++) {
                StringBuilder sb = new StringBuilder();
                sb.append(a.substring(0, j)).append(b.substring(i, i + 1));
                if (j != n) sb.append(a.substring(j));
                int k = j;
                while (0 <= k && k < sb.length()) {
                    char c = sb.charAt(k);
                    int l = k, r = k;
                    while (l >= 0 && sb.charAt(l) == c) l--;
                    while (r < sb.length() && sb.charAt(r) == c) r++;
                    if (r - l - 1 >= 3) {
                        sb.delete(l + 1, r);
                        k = l >= 0 ? l : r;
                    } else {
                        break;
                    }
                }
                ans = Math.min(ans, dfs(sb.toString(), next) + 1);
            }
        }
        map.put(a, ans);
        return ans;
    }
}

