package Difficult.编辑距离;

public class EditDistance {

    public int minDistance(String word1, String word2) {
        char[] word1C = word1.toCharArray();
        char[] word2C = word2.toCharArray();
        return process(word1C, word1C.length - 1, word2C, word2C.length - 1);
    }

    // 递归：大量重复操作——TLE
    public int process(char[] word1, int i, char[] word2, int j) {
        if (i == 0 || j == 0) {
            return Math.max(i, j);
        }
        if (word1[i] == word2[j]) {
            return process(word1, i - 1, word2, j - 1);
        }
        return 1 +
                Math.min(
                        Math.min(
                                // 在word1后面插入第i+1的字符使得和word2的第j个匹配
                                process(word1, i, word2, j - 1),
                                // 删除word1的最后一个字符，比较剩下的
                                process(word1, i - 1, word2, j)
                        ),
                        // 替换word1最后一个字符和word2的最后一个字符匹配
                        // 所以剩下的比较长度都减一
                        process(word1, i - 1, word2, j - 1)
                );
    }

    //DP
    /*
        根据上面的递归
            如果 word1[i]==word2[j]，那么有 op[i][j]=op[i-1][j-1]
            否则 op[i][j] = min( op[i-1][j], op[i][j-1], op[i-1][j-1] )
            初始条件即base case：
            当i==0的时候，op[0][j] = j;
            当j==0的时候，op[i][0] = i;
            其他位置初始值都是 0。
     */
    public int minDistance2(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        // 有一个字符串为空串
        if (n * m == 0) {
            return n + m;
        }

        // DP 数组
        int[][] D = new int[n + 1][m + 1];

        // 边界状态初始化
        for (int i = 0; i < n + 1; i++) {
            D[i][0] = i;
        }
        for (int j = 0; j < m + 1; j++) {
            D[0][j] = j;
        }

        // 计算所有 DP 值
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                int left = D[i - 1][j] + 1;  // 左边：删除操作
                int down = D[i][j - 1] + 1;  // 下边：插入操作
                int left_down = D[i - 1][j - 1];
                // 这个位置是最后一个字符，最后一个字符不一样，那么需要进行替换，所以操作数+1
                // 如果不需要替换，这个if语句就不会执行，也就不会+1
                // 这时候只需要考虑把前面的字符通过插入删除换成一样的就行了
                if (word1.charAt(i - 1) != word2.charAt(j - 1)) {
                    left_down += 1;
                }
                D[i][j] = Math.min(left, Math.min(down, left_down));
            }
        }
        // 直到全部完成
        return D[n][m];
    }

    // 上面官解的递归是把dp取值的过程简略了，对于最后一个是否相同进行了判断
    // 以此判断是否需要替换操作
    public int minDistance3(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        if (m * n == 0) return m + n;

        int[][] op = new int[m + 1][n + 1];
        // base case
        for (int i = 0; i < m + 1; i++) {
            op[i][0] = i;
        }
        for (int j = 0; j < n + 1; j++) {
            op[0][j] = j;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {  // 最后一个字符一样的，那么直接取值
                    op[i][j] = op[i - 1][j - 1];
                } else {
                    // 否则进行插入、删除、替换，并且操作数+1
                    op[i][j] = Math.min(op[i - 1][j], Math.min(op[i - 1][j - 1], op[i][j - 1])) + 1;
                }
            }
        }
        return op[m][n];
    }
}
