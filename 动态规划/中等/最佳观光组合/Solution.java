package 动态规划.中等.最佳观光组合;

public class Solution {
    /**
     * 需要找出两个景点 i 和 j，使得： values[i] + i + values[j] - j 最大。
     * 思路：
     * 1. 暴力破解就是找出所有景点的最优值，然后针对所有景点再找一次最优值。时间复杂度O(n^2)
     * 2. 可以找出最大的 vales[i]+i 和 最大的 values[j]-j；他俩加在一起就是最大的了。且 i != j。
     */
    // 贪心算法
    public int maxScoreSightseeingPair(int[] values) {
        int ans = 0;
        int max = values[0];
        for (int i = 1; i < values.length; i++) {
            // 更新差值
            ans = Math.max(ans, max + values[i] - i);
            // 维护到和的最大值
            max = Math.max(max, values[i] + i);
        }
        return ans;
    }

    /**
     * 动态规划，用vi表示使用当前下标i配对的最优解。
     * vi有两种情况:
     * 1. i和i-1配对，即values[i]+values[i-1]-1
     * 2. i和i-1之前的某个j配对，即values[i]+values[j]-(i-j)，
     * 如果是此种情况则i-1必然也和j配对才能产生对于i-1的最优解，也就是上一个状态的vi，由此可将上式简化：
     * values[i]+values[j]-(i-j)-(values[i-1]+values[j]-(i-1-j)) = values[i]-values[i-1]-1
     * 即 values[i]+values[j]-(i-j) = values[i]-values[i-1]-1+vi (此vi为更新状态之前，即i-1状态的vi)
     * 将 vi 更新为 1,2 的最大值，然后更新结果即可
     */
    // 动态规划
    public int maxScoreSightseeingPair2(int[] values) {
        int vi = values[1] + values[0] - 1;
        int res = vi;
        for (int i = 2; i < values.length; i++) {
            vi = Math.max(values[i - 1], vi - values[i - 1]) - 1 + values[i];
            res = Math.max(vi, res);
        }
        return res;
    }
}
