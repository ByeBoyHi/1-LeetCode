package 动态规划.中等.打家劫舍2;

public class Solution {
    /**
     * 思路如下：
     *      1. 当只有一间房的时候，直接返回这个返回金额。
     *      2. 当有两间房的时候，返回这两个房间的最大值。
     *      3. 当有多余两间房的时候：考虑首位相连问题。
     *          取了第一间房，就不能要最后一间房：[0, n-2]
     *          取了最后一间房，就不能要第一间房：[1, n-1]
     *      对这两段用普通的打家劫舍算法，再取这两段的最大值返回。
     */
    public int rob(int[] nums) {
        if (nums.length==1) return nums[0];
        if (nums.length==2) return Math.max(nums[0], nums[1]);

        return Math.max(
                robHelp(nums, 0, nums.length-2),  //[0, n-2]
                robHelp(nums, 1, nums.length-1)   //[1, n-1]
        );
    }

    public int robHelp(int[] arr, int start, int end){
        // 初始化：first是只有一间房的值
        // second是有两间房的值
        int first=arr[start];
        int second=Math.max(arr[start],arr[start+1]);
        for (int i=start+2; i<=end; i++){
            int temp = first;  // 记录f(i-2)
            first = second;  //往后移一步
            // f(i) = max( f(i-2)+arr[i], f(i-1) )
            second = Math.max(temp+arr[i], first);
        }

        return second;
    }
}
