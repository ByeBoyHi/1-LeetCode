package Middle.旋转函数;

import java.util.Arrays;

public class RotateFunc {

    // 无论是怎样实现转换，都会超时，所以这里是模拟翻转，从而找出里面的规律
    /*
        一共k个元素。
        F(0) = 0*nums[0] + 1*nums[1] + 2*nums[2] + ... + （k-1）*nums[k-1]
        F(1) = 1*nums[0] + 2*nums[1] + 3*nums[2] + ... + 0*nums[k]
        F(2) = 2*nums[0] + 3*nums[1] + 4*nums[2] + ... + 1*nums[k]
        ...
        第x次轮换的总和，等于第x-1次轮换的总和，让第k-(x-1)的位置清零，然而这个在前一次的系数是满的。(k-1)
        因此公式是：F(x) = F(x-1)+sum-k*del
        因为sum里面加了一次del，所以删除的是k*del
     */

    public int maxRotateFunction(int[] nums) {
        int n = nums.length;
        if (n == 1) return 0;
        int sum = Arrays.stream(nums).sum();
        int ans = 0;
        int f = 0;
        // 第一次求和
        for (int i=0; i<n; i++){
            f+=i*nums[i];
            ans = f;
        }
        // 轮换n-1次
        // 从最后面开始轮换，所以清零也是从最后开始的
        for (int i=n-1; i>0; i--){
            f+=sum-n*nums[i];
            ans = Math.max(ans, f);
        }
        return ans;
    }
}
