package Difficult.猫和老鼠;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CatAndMouse {

    static final int MOUSE_WIN = 1;
    static final int CAT_WIN = 2;
    static final int DRAW = 0;
    int n;
    int[][] graph;
    int[][][] dp;

    public int catMouseGame(int[][] graph) {
        this.n = graph.length;
        this.graph = graph;
        dp = new int[n][n][2 * n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        return getResult(1, 2, 0);
    }

    public int getResult(int mouse, int cat, int turn) {
        if (turn == 2 * n) {
            return DRAW;
        }
        if (dp[mouse][cat][turn] < 0) {  // 还没来过
            if (mouse == 0) {
                dp[mouse][cat][turn] = MOUSE_WIN;
            } else if (cat == mouse) {
                dp[mouse][cat][turn] = CAT_WIN;
            } else {
                getNextResult(mouse, cat, turn);
            }
        }
        return dp[mouse][cat][turn];
    }

    public void getNextResult(int mouse, int cat, int turn) {
        // 确定移动的人是谁
        // 偶数老鼠移动，奇数猫猫移动
        int curMove = (turn & 1) == 0 ? mouse : cat;

        //如果存在一个节点，猫到达该节点之后，猫可以获胜，
        // 则猫到达该节点之后的状态为猫的必胜状态，老鼠的必败状态，
        // 因此在猫移动之前的当前状态为猫的必胜状态。
        int defaultResult = curMove == mouse ? CAT_WIN : MOUSE_WIN;

        // 存储这次位置的最终结果
        int result = defaultResult;
        // 存储之后能到达的节点
        int[] nextNodes = graph[curMove];
        for (int next : nextNodes) {
            if (curMove == cat && next == 0) { // 猫不能到达洞口
                continue;
            }
            // 判断猫鼠的下一次位置
            int nextMouse = curMove == mouse ? next : mouse;
            int nextCat = curMove == cat ? next : cat;
            // 得到这次移动的结果
            int nextResult = getResult(nextMouse, nextCat, turn + 1);
            if (nextResult != defaultResult) {
                // 如果新的结果和这次不同，那么更新，如果不是求和，那么就是必然的一个结果，退出循环，返回结果
                result = nextResult;
                if (result != DRAW) {
                    break;
                }
            }
        }
        dp[mouse][cat][turn] = result;
    }
}

/**
 * 猫赢，+1分
 * 老鼠赢，-1分
 * 平局，0分
 * <p>
 * 那么对于猫来讲，分数要尽量的高
 * 对于老鼠来讲，分数要尽量的低
 */
class Solution {

    private Map<Integer, Map<Integer, Integer>> cache;
    private int[][] graph;

    public int catMouseGame(int[][] graph) {
        cache = new HashMap<>();
        this.graph = graph;
        int ans = minMax(0, 1, 2);
        if (ans == -1) {  // 老鼠赢
            return 1;
        } else if (ans == 1) {  // 猫赢
            return 2;
        } else {  // 平局
            return 0;
        }
    }

    public int minMax(int i, int m, int c) {
        if (i > 2 * graph.length) {
            return 0;
        }
        if (m == 0) {
            return -1;
        }
        if (c == m) {
            return 1;
        }
        Map<Integer, Integer> memo = cache.getOrDefault(i, new HashMap<>());
        int key = m * graph.length + c; // 加密密钥
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        // 偶数，该老鼠走，让猫赢
        // 奇数，该猫走，让老鼠赢
        int res = (i & 1) == 0 ? 1 : -1;
        if ((i & 1) == 0) {
            for (int next : graph[m]) {
                // 老鼠取低分
                res = Math.min(res, minMax(i + 1, next, c));
                if (res == -1) {  // 老鼠走，然后老鼠赢了，直接退出，后面没必要比较了
                    break;
                }
            }
        } else {
            for (int next : graph[c]) {
                // 猫走的时候，要把0略过
                if (next!=0) {
                    // 猫取高分
                    res = Math.max(res, minMax(i + 1, m, next));
                    if (res == 1) {
                        break;
                    }
                }
            }
        }
        memo.put(key, res);
        cache.put(i, memo);
        return res;
    }
}
