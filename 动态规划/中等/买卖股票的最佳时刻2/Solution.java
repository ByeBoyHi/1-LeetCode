package 动态规划.中等.买卖股票的最佳时刻2;

public class Solution {
    /**
     * 思路：
     * 1. 找到一个最低价minPrice，然后往后找比他高的，如果比他还低，就更新。
     * 2. 找到第一个比他高的，首先判断下一个是否比当前高，如果比当前低，就卖出。
     * 3. 然后从卖出后的第一天开始找下一个最低价minPrice，重复 1 2。
     * 4. 定义一个 flag 来标记是否已经卖出上一个最低价。
     * 5. 定义一个 profit 来存储所有的收益。
     */
    public int maxProfit(int[] prices) {
        int minPrice = prices[0];
        int profit = 0;
        boolean flag = false;
        for (int i = 1; i < prices.length - 1; i++) {
            if (flag) { // 判断上一个最低价是否已经卖出了
                minPrice = prices[i];
                flag = false;
                continue;
            }
            // 如果当前价格比最低价还低，我们就更新最低价
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else {
                if (prices[i + 1] < prices[i]) {  // 如果当前价格比最低价高，并且比下一个价格高，我们就存储收益
                    profit += (prices[i] - minPrice);
                    flag = true;
                }
            }
        }

        // 避免后面一段是单调递增情况，但是又没有把收益存进去。
        if (!flag) {  // 如果循环走完，flag为false，说明倒数第二个价格不是比flag小，就是最后一个价格更大
            if (prices[prices.length - 1] > minPrice) {
                profit += (prices[prices.length - 1] - minPrice);
            }
        }
        return profit;
    }

    // 大佬题解
    /**
     * 挨着计算价格差，只要当天的价格比昨天的价格高，我就当作买入了，然后把钱算到利润里面。
     * 如果挨着下一天又比今天高，照样加入，这就相当于昨天买的，明天卖出。
     * "贪心算法"：只要今天赚了，就加到利润里面。
     */
    public int maxProfit2(int[] prices) {
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            int tmp = prices[i] - prices[i - 1];
            if (tmp > 0) profit += tmp;
        }
        return profit;
    }
}
