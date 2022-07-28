package 动态规划.中等.买卖股票的最佳时机含手续费;

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().maxProfit(new int[]{
                1, 3, 2, 8, 4, 9
        }, 2));
    }

    /**
     * 动态规划：持股或者不持股
     * 1. 不持股：昨天就不持股，或者今天卖出了
     * dp0 = max(dp0, prices[i]+dp1-fee)
     * 2. 持股：昨天就持股，或者昨天不持股今天买入
     * dp1 = max(dp1, dp0-prices[i])
     */
    public int maxProfit(int[] prices, int fee) {
        int dp0 = 0;
        int dp1 = -prices[0];
        int temp;
        for (int i = 1; i < prices.length; i++) {
            temp = dp0;
            dp0 = Math.max(dp0, prices[i] + dp1 - fee);
            dp1 = Math.max(dp1, temp - prices[i]);
        }
        return dp0;
    }

    /**
     * 贪心算法：在最大化利益的前提下，如果我们手里拥有一支股票，那么考虑它的最低买入价格是多少
     * 买入 buy = prices[0] + fee  (把手续费也当作买入的费用)
     * 如果当前价格大于 buy，我们就可以考虑卖出了。
     * 但是防止下一个价格的利润可能会更大，我们可以让buy=prices[i]，然后prices[i+1]卖出。
     * 就相当于 prices[i-1]+fee买入，prices[i+1]卖出，中间的prices[i]在前后加减就抵消了，这一天什么也没做。
     * 而对于小于buy的价格，我们都可以不用考虑，我们还要考虑prices[i]+fee小于buy的时候，更新最低价格
     */
    public int maxProfit2(int[] prices, int fee) {
        int buy = prices[0] + fee;
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (buy>prices[i]+fee){  // 贪心于找最低价格
                buy = prices[i]+fee;
            }
            if (prices[i]>buy){  // 贪心于赚钱
                profit+=(prices[i]-buy);
                buy = prices[i];  // 贪心于下一个价格万一赚的更多
            }
        }
        return profit;
    }
}