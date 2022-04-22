package 动态规划.中等.环形子数组的最大和;

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().maxSubarraySumCircular(new int[]{
                -2,4,-5,4,-5,9,4
        }));
    }

    public int maxSubarraySumCircular(int[] nums) {
        if (nums.length == 1) return nums[0];

        /**
         * 1. 先求出正常数组的最大子序和，用于后面的比较
         * 2. 然后从后往前求累加和，因为我们要考虑尾部和头部相接的情况
         * 3. 计算完累加和之后，从后往前，计算当前位置的最大连续和
         * 4. 然后该从头遍历求和，并且加上尾部的值-->A[0]+...+A[i] + R(i+2)
         *      R(i+2)表示后i+2项的最大值，从A.length往前加的一个计算。
         *      不能取i+1，因为i+1和i挨着的，这时候其实就是原数组，没有必要取考虑首尾相连了。
         */

        // 1 -- O(n)时间复杂度
        int ans = nums[0];
        int cur = nums[0];
        for(int i=1; i<nums.length; i++){
            cur = nums[i] + Math.max(0, cur);  // 判断之前的子序和是否有增益效果，并且加上当前值
            ans = Math.max(ans, cur);  // 判断更新后的值和之前的子序和谁更好
        }

        // 2 -- O(n)时间和空间的复杂度
        int[] rightSum = new int[nums.length];
        rightSum[rightSum.length-1] = nums[nums.length-1];
        for (int i=nums.length-2; i>=0; i--){
            rightSum[i] = nums[i] + rightSum[i+1];  // 不断的从后往前的累加求和
        }

        // 3 -- O(n)时间和空间的复杂度
        int[] maxSum = new int[nums.length];
        maxSum[maxSum.length-1] = nums[nums.length-1];
        for (int i=maxSum.length-2; i>=0; i--){
            maxSum[i] = Math.max(maxSum[i+1], rightSum[i]);  // 是之前的子序和大还是整体求和大。无论哪种判断，都算上了最后一个元素的，便于链接
        }

        // 4 -- O(n)的时间复杂度
        int leftNumber = 0; // 记录左边的数字
        for (int i=0; i<nums.length-2; i++){
            leftNumber+=nums[i];
            ans = Math.max(ans, leftNumber+maxSum[i+2]);
        }

        return ans;
    }

    // 大佬题解
    /**
     * 题目有两种情况：
     *  1. 最大子序和在中间，那么就是正常解
     *  2. 最大子序和要跨越头尾，那么就是两端往中间走是最大的，直接找中间的最小子序和，然后减去就行了
     */
    public int maxSubarraySumCircular2(int[] nums){
        int sum = nums[0];
        int max = nums[0];
        int dp = nums[0];
        int min = 0;

        // 1
        for (int i=1; i<nums.length; i++){
            sum+=nums[i];
            dp = nums[i] + Math.max(dp, 0);
            max = Math.max(max, dp);
        }

        // 2
        dp = nums[0];
        // 如果全是负数的话
        // 上面的循环会找出一个最小的负数，这里的循环少1，会把最后一个负数留出来进行比较，防止出现0的情况
        // 找最小子序和是不需要考虑首尾相连的
        for (int i=1; i<nums.length-1; i++){
            dp = nums[i] + Math.min(0, dp); // 这时候找最小子序和，为负则有增益
            min = Math.min(min, dp);
        }

        return Math.max(sum-min, max);
    }
}
