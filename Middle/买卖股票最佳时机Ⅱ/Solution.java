package Middle.买卖股票最佳时机Ⅱ;

/**
 第一种情况：当天有股票的收益
 1. 当天没有进行交易，股票是昨天就有的。 hold
 2. 当天新买入的股票，则昨天是没有股票的。noHold - price[i]
 取上述两种情况的max。
 第二种情况：当天没有股票的收益
 1. 前一天就没有股票  noHold
 2. 前一天有股票，但是今天卖出去了 hold + price[i]
 取上述两种情况的max。
 */
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] prices = new int[]{5,8,8,6,10};
        System.out.println(solution.maxProfit(prices));
        System.out.println(solution.maxProfit2(prices));
    }

    // 动态规划
    public int maxProfit(int[] prices) {
        if(prices.length<2)  // 如果天数小于2，则无法实现买入卖出两个操作，返回0
            return 0;
        int len = prices.length;  // 记录天数
        int[][] dp = new int[len][2];  //有price天，每天有2种情况
        dp[0][1] = -prices[0];  //第一天买入了股票
        dp[0][0] = 0;  // 第一天没有买入股票
        for(int i=1; i<len; i++){
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]+prices[i]);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0]-prices[i]);
        }
        // 最后的最大收益肯定是最后一天没有股票
        return dp[len-1][0];
    }
    //优化：在取消二维数组而选择两个变量
    //在for循环中：
    //  noHold用的前一天的hold和noHold。
    //  hold用的前一天的hold和今天的noHold。
    //  对于hold取的"今天的noHold"进行讨论：
    //      1. 如果"今天的noHold"=前一天的noHold，那么无误
    //      2. 如果"今天的noHold"=hold+prices[i]，那么今天的hold=hold(前一天的)
    //  当第二种情况出现时：noHold<hold+prices[i]，则有noHold-prices[i]<hold
    //  所以这时候，今天的hold===昨天的hold，无误
    public int maxProfit2(int[] prices) {
        if(prices.length<2)  // 如果天数小于2，则无法实现买入卖出两个操作，返回0
            return 0;
        int len = prices.length;  // 记录天数
        int hold = -prices[0];  // 第一天买入了股票
        int noHold = 0;  // 第一天没有买入股票
        for(int i=1; i<len; i++){
            noHold = Math.max(noHold, hold+prices[i]);
            hold = Math.max(hold, noHold-prices[i]);
        }
        // 最后的最大收益肯定是最后一天没有股票
        return noHold;
    }
}