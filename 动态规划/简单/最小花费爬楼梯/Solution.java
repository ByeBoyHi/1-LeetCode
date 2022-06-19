package 动态规划.简单.最小花费爬楼梯;

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().minCostClimbingStairs(new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1}));
    }

    public int minCostClimbingStairs(int[] cost) {
        int len = cost.length;
        int[] res = new int[len+1];
        res[0] = 0;
        res[1] = cost[0];
        for (int i=2; i<len+1; i++){
            res[i] = Math.min(res[i-1], res[i-2])+cost[i-1];
        }
        return Math.min(res[len-1],res[len]);
    }
}
