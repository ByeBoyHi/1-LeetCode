package 动态规划.中等.等差数列划分;

import java.util.Arrays;

public class Solution {
    /**
     * 首先遍历原数组 nums，用数组 diffs 存储相邻两个元素之间的差值。
     * 然后遍历 diffs，用数组 cons 存储其中连续相同的差值的数目，
     * 比如有 33 个 33 连在一起，意味着原数组中这个位置存在一个最大长度为 44 的等差数列。
     * 然后遍历 cons，对于长度为 n 的等差数列，其所有的长度大于等于 33 的子数列都是等差数列，则一共有 (n-2)(n-1)/2 个等差数列。
     * 全部相加得到结果。
     * ！！！
     * 这道题要求的是任意两个相邻元素之差相同，才能称之为等差数列，必须相邻！！！！！，不能排序之后再找！！！！
     */
    public int numberOfArithmeticSlices(int[] nums) {
        if (nums.length<3) return 0;  // 长度小于3
        int diff = nums[1]-nums[0];
        int cnt = 2;
        int ans = 0;
        for (int i=2; i<nums.length; i++){
            if (nums[i]-nums[i-1]==diff){  // 相邻元素依然等差，计数加一
                cnt++;
            }else{  // 否则更新
                ans += (cnt-2)*(cnt-1)/2;
                cnt = 2;
                diff = nums[i]-nums[i-1];
            }
        }
        // 区间链接到结尾，或者结尾不构成等差数列
        ans+=(cnt-2)*(cnt-1)/2;
        return ans;
    }
}
