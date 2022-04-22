package 动态规划.中等.最佳买卖股票时机含冷冻期;

public class Solution {
    /**
     * 讨论今天 "是否买入，是否卖出"
     * 1. 昨天不持股，肯定是冻结期
     * 1.1 今天持股：买入
     * 2. 昨天持股
     * 2.1 今天持股：可能手里的股票更新，今天的股价更低
     * 2.2 今天不持股：可以卖出，有赚到差价 —— 明天冻结期
     *
     * 思路一：
     * 1. 今天持股，说明是昨天就持股，或者昨天不持股且不是卖出，今天买入
     * 2. 今天不持股，卖出去了。
     * 就有三种状态：普通的不持股、卖出的不持股、持股
     * 第一种：dp0 = max(dp0, dp1)
     * 第二种：dp1 = prices[i]+dp2
     * 第三种：dp2 = max(dp2, dp0-prices[i])
     * 取最大值都是为了保证收益最大。
     */
    public int maxProfit(int[] prices) {
        int dp0 = 0;
        int dp1 = 0;
        int dp2 = -prices[0];
        int temp;
        for (int i=1; i<prices.length; i++){
            temp = dp0;
            dp0 = Math.max(dp0, dp1);
            dp1 = prices[i]+dp2;
            dp2 = Math.max(dp2, temp-prices[i]);
        }
        return Math.max(dp0, dp1);
    }
}
