package 动态规划.简单.最大子序和;

public class Solution {

    public int maxSubArray(int[] nums) {
        if (nums.length==1) return nums[0];
        int pre = nums[0];
        int sum;
        int ans = nums[0];
        for (int i=1; i<nums.length; i++){
            // 置为0的时候，就是新的起点
            // 只要pre还是大于0，就表示有buff，就接着往后加，否则就取新的起点
            sum = Math.max(0, pre) + nums[i];
            // 不断取最大值，可以保证在这个子序列里面的最大和。
            // 这个就是取的之前一段子序和的最大值和当前子序和的最大值中的一个，实现不断的筛选
            ans = Math.max(sum,ans);
            // pre记录这一次的子序和
            pre = sum;
        }
        return ans;
    }
}
