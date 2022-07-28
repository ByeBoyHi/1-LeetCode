package Difficult.统计不同回文子序列;

import java.util.Arrays;

/**
 * <功能描述>
 * Time: 2022/6/10
 * User: HeyBoy
 */
public class DiffPalindSubsequence {

    final static int MOD = 1000000007;

    /**
     * dp[x][i][j] 表示在区间 [i..j] 之间，以 x 开头和结尾的回文子串有多少。
     * 对于一个整体的字符串S。找出其中任意一个字符C，然后计算以C开头和结尾的回文子串有多少。
     * 1. S[i]==S[j]==C。那么 dp[C][i][j] = 2 + sum(dp[x][i+1][j-1]) (x为其他字符) +2是以头尾两个C自成一个回文子串
     * 2. S[i]!=C,S[j]==C，那么 dp[C][i][j] = dp[C][i+1][j] 以j结尾的数量
     * 3. S[i]==C,S[j]!=C，那么 dp[C][i][j] = dp[C][i][j-1] 以i开头的数量
     * 4. S[i]!=C,S[j]!=C，那么 dp[C][i][j] = dp[C][i+1][j-1] [i+1...j-1]之间以C开头结尾的数量
     * 因为我们的 dp 规定记录的数据就是 一个区间内的 以 X 结尾和开头的回文子串的数量。
     * 所以在整个存储过程中，我们只对对应的数据进行整合。
     * <p>
     * 边界条件：
     * 1. 当 i==j 的时候，dp[c][i][i]=1。
     * 2. 当 i>j  的时候，dp[c][i][j]=0。
     * <p>
     * 我需要 dp[x][0][n-1]
     *
     * @param s 传入的目标字符串
     * @return 返回这个目标字符串中回文子串的个数
     */
    public int countPalindromicSubsequences(String s) {

        int n = s.length();
        char[] chs = s.toCharArray();
        // 这个题只有4个字符 a b c d
        // 那么只需要返回 0~n-1之间的回文子串
        // 这里取long，后面多次取模，可能导致数值的不准确，为了方便我们一次加上，这里取long，然后在结束的时候进行取模运算
        long[][][] dp = new long[4][n][n];

        // 初始化
        for (int i = 0; i < n; i++) {
            dp[chs[i] - 'a'][i][i] = 1;  // i==j的时候，对应字符回文子串为1，其他位置为0
        }

        // dp构造过程
        // 从下往上，从左往右
        for (int row = n - 2; row >= 0; row--) {  // 最后一行已经初始化完了的
            for (int col = row + 1; col < n; col++) {  // 倒三角关系
                for (char c = 'a'; c <= 'd'; c++) {
                    int idx = c - 'a';
                    if (chs[row] == c && chs[col] == c) { // 情况1
                        dp[idx][row][col] = 2;
                        for (int i = 0; i < 4; i++) {
                            dp[idx][row][col] += (dp[i][row + 1][col - 1]);
                        }
                        dp[idx][row][col] %= MOD;
                    } else if (chs[row] == c && chs[col] != c) {  // 情况2
                        dp[idx][row][col] = dp[idx][row][col - 1];
                    } else if (chs[row] != c && chs[col] == c) {  // 情况3
                        dp[idx][row][col] = dp[idx][row + 1][col];
                    } else if (chs[row] != c && chs[col] != c) {  // 情况4
                        dp[idx][row][col] = dp[idx][row + 1][col - 1];
                    }
                }
            }
        }
        int res = 0;

        for (int i = 0; i < 4; i++) {
            res += dp[i][0][n - 1];
            res %= MOD;
        }
        return res;
    }


    // 二维dp解决

    /**
     * 对于二维求解。
     * dp[i][j] 表示 i..j 区间上的回文子串个数，也就不在考虑是以谁开头
     * 对于 dp[i][j]，
     * 1. 当头尾相等的时候，就是 dp[i+1][j-1]分别是 i和j 开头结尾的回文子串总数。
     * 这时候，我们需要考虑重复问题，当 i+1...j-1 里面有 i j 这两个字符的时候，我们需要找出最大区间的那个，然后减掉加多余的这部分。
     * 所以我们需要一个 next 和一个 pre 数组，分别记录每个字符的下一个最近相同字符和上一个最近相同字符。
     * 假设分别预处理后，得到两个位置分别是 low 和 high。
     * 1.1 当 low > high 的时候，也就是位置错开，不存在子区间需要去重，那么就是 2+(dp[i+1][j-1]*2)
     * 1.2 当 low = high 的时候，也就是重复子区间只有一个字符的时候，那么就是 1+(dp[i+1][j-1]*2)
     * 1.3 当 low < high 的时候，此时的重复子区间就是一大块了，那么就是 (dp[i+1][j-1]*2 - dp[low+1][high-1])
     * 在每次计算的时候，都不要忘记可能越界的地方 %MOD。
     * 2. 当头尾不相等的时候，此时就是 dp[i+1][j] 和 dp[i][j-1]，此时还需要考虑重复部分 dp[i+1][j-1]。
     * 3. 在取模的时候，可能会有最后取整的问题，可以加上MOD，最后整体取模的时候就是向上取整。
     */
    public int countPalindromicSubsequences2Dim(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        int[][] pre = new int[4][n];
        int[][] next = new int[4][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        int[] pos = new int[4];  // 记录对应字符上一次出现的位置，也就可以得到当前字符前一次出现的位置
        Arrays.fill(pos, -1);

        for (int i = 0; i < n; i++) {
            int idx = s.charAt(i) - 'a';
            pre[idx][i] = pos[idx];
            pos[idx] = i;
        }

        Arrays.fill(pos, n);

        for (int i = n - 1; i >= 0; i--) {
            int idx = s.charAt(i) - 'a';
            next[idx][i] = pos[idx];
            pos[idx] = i;
        }

        for (int row = n - 2; row >= 0; row--) {
            for (int col = row + 1; col < n; col++) {
                if (s.charAt(row) == s.charAt(col)) {  // 两端相等
                    int low = next[s.charAt(row) - 'a'][row];
                    int high = pre[s.charAt(col) - 'a'][col];
                    if (low > high) {
                        dp[row][col] = (2 + dp[row + 1][col - 1] * 2) % MOD;
                    } else if (low == high) {
                        dp[row][col] = (1 + dp[row + 1][col - 1] * 2) % MOD;
                    } else {
                        dp[row][col] = (dp[row + 1][col - 1] * 2 % MOD - dp[low + 1][high - 1] + MOD) % MOD;
                    }
                } else {
                    dp[row][col] = ((dp[row][col - 1] + dp[row + 1][col]) % MOD - dp[row + 1][col - 1] + MOD) % MOD;
                }
            }
        }
        return dp[0][n - 1];
    }

}
