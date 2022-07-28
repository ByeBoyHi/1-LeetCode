package Middle.最长定差子序列;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    /**
     * 从数组arr中找出一个子序列，子序列相邻元素只差等于difference的最长子序列长度
     * 子序列相相邻不要求在原序列也相邻
     * @param arr         目标数组
     * @param difference  指定相邻元素之差
     * @return            返回连续元素之差等于difference的最长子序列长度
     *
     * 元素之间步要求相邻，但是有前后顺序关系。
     * 尝试dp解决：
     *  1. dp[0]就是第一个元素，初始值为 dp[0]=1
     *  2. dp[i]：终点为i的最长子序和长度
     *  3. 定义一个 HashMap用来寻找前面的数字k，比如k=arr[i]-difference，记录已经出现的数字 k，且记录他的下标，用于取dp值
     */
    public int longestSubsequence(int[] arr, int difference) {
        int len = arr.length;
        if (len<1) return 0;
        int[] dp = new int[len];  // 初值为0，意思是所有数字在自身拼接自身的时候，是没有+1的
        Map<Integer, Integer> map = new HashMap<>();
        int ans = 1;
        for (int i=0; i<len; i++){
            int pre_digit = arr[i]-difference;
            if (map.containsKey(pre_digit)){
                int pre_index = map.get(pre_digit);
                dp[i] = dp[pre_index]+1; // 和之前的数字进行拼接，长度+1
                // 这里的dp+1只是用于计算，并不会给dp修改值，我们每次拼接，也只是加上了当前的数字，
                // 但是最开始的第一个数字加进来的时候是没有计数的，所以这里需要+1
                ans = Math.max(ans, dp[i]+1);
            }
            map.put(arr[i], i);
        }
        return ans;
    }

    public int longestSubsequence2(int[] arr, int difference) {
        // 穷举了全部的可能
        // arr有20000的可能，difference有20000的可能
        // 把负数往后移动20000个到正数部分，便于数组保存
        int res[]=new int[40001];
        int max=0;
        for(int x:arr){
            // 这个这个差值的前一个进行拼接
            int i=res[x-difference+20000]+1;
            // 把值赋值给当前值，用于后面的拼接
            res[x+20000]=i;
            // 计算最长子序列长度
            max=Math.max(max,i);
        }
        return max;
    }
}
