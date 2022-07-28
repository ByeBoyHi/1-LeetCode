package 动态规划.简单.买卖股票的最佳时机;

public class Solution {
    /**
     * 思路：假设自己是在 "当前时段的最低点" 买入的股票，那么这之后就可以尽量赚的多。
     * 1. 从头遍历挨着遍历，存储最低值。
     * 2. 每次存储判断的同时，都用当前值股票值和最低值的差值比较一下，是否赚的更多。
     * 3. 实现了不断的维护最低价，和赚的最大差价。
     */
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxRes = 0;  // 如果没有赚的，那就是0了
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minPrice) {  // 维护最低价
                minPrice = prices[i];
            }
            if (prices[i] - minPrice > maxRes) {  // 维护最大差价
                maxRes = prices[i] - minPrice;
            }
        }
        return maxRes;
    }

    // 动态规划

    /**
     * 思路：
     * 1. 第 i 天可能持股或者不持股。给两个状态表示第 i 天持股(1)和不持股(0)
     * 2. 第 i 天持股的话，那么就是 dp[i][1] = Math.max(dp[i-1][1], -prices[i])，
     *      也就是昨天持股的钱和买今天的股的钱要花的钱谁更少。
     * 3. 第 i 天不持股的话，那么就是 dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i])
     */
    public int maxProfit2(int[] prices) {
        int len = prices.length;
        int[][] dp = new int[len][2];
        // 第一天持股和不持股
        dp[0][0] = 0;  // 不持股，不花钱也不收钱
        dp[0][1] = -prices[0];  // 持股。就买入了第一天的股，要花钱
        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
        }
        // 最后返回肯定是最后一天不持股的钱，也就是要卖出去
        return dp[len - 1][0];
    }

    // 动态规划：优化空间
    public int maxProfit3(int[] prices){
        int hold = -prices[0];
        int noHold = 0;
        for (int i=1; i<prices.length; i++){
            int temp = hold;
            hold = Math.max(hold, -prices[i]);
            noHold = Math.max(noHold, temp+prices[i]);
        }
        return noHold;
    }
}
