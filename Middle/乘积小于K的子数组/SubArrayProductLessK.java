package Middle.乘积小于K的子数组;

public class SubArrayProductLessK {
    // 1000ms
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                if (i == j) {
                    if (nums[i] < k) {
                        ans++;
                        sum = nums[i];
                    } else {
                        break;
                    }
                } else {
                    if (sum * nums[j] < k) {
                        ans++;
                        sum *= nums[j];
                    } else {
                        break;
                    }
                }
            }
        }
        return ans;
    }

    /*
        要计算连乘小于k，那么可以取对数，实现求连续对数和小于 log k。
     */
    
}
