package Middle.旋转函数;

public class RotateFunc {

    /*
        F(0) = 0*nums[0] + 1*nums[1] + 2*nums[2] + ... + k*nums[k]
        F(1) = 1*nums[0] + 2*nums[1] + 3*nums[2] + ... + 0*nums[k]
        F(2) = 2*nums[0] + 3*nums[1] + 4*nums[2] + ... + 1*nums[k]
        ...
        第x次轮换的总和，等于第x-1次轮换的总和，让第
     */

    public int maxRotateFunction(int[] nums) {
        int n = nums.length;
        if (n == 1) return 0;

        int ans = 0;
        int k = 0;

    }
}
