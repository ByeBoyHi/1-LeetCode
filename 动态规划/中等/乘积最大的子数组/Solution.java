package 动态规划.中等.乘积最大的子数组;

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().maxProduct1(new int[]{
                -2,0,-1,1,-1,-1,135,1
        }));
    }

    public int maxProduct(int[] nums) {
        // 考虑有负负得正的情况，我们可以再维护一个保存最小值的数组，里面会有最大负数乘积
        // 需要考虑三个的最大值：nums[i]， 最大正数乘积乘以nums[i]， 最小负数的乘积乘以nums[i]
        // 需要考虑三个的最小值：nums[i]， 最大正数乘积乘以nums[i]， 最小负数的乘积乘以nums[i]
        int[] Fmax = new int[nums.length];
        int[] Fmin = new int[nums.length];
        Fmax[0] = nums[0];
        Fmin[0] = nums[0];
        for (int i=1; i<nums.length; i++){
            Fmax[i] = Math.max(Fmax[i-1]*nums[i], Math.max(Fmin[i-1]*nums[i], nums[i]));
            Fmin[i] = Math.min(Fmin[i-1]*nums[i], Math.min(Fmax[i-1]*nums[i], nums[i]));
        }
        int ans = Fmax[0];
        for (int i=1; i<Fmax.length; i++){
            ans = Math.max(ans, Fmax[i]);
        }
        return ans;
    }

    // 空间优化
    public int maxProduct1(int[] nums){
        int Fmax = nums[0];
        int Fmin = nums[0];
        int ans = Fmax;
        for (int i=1; i<nums.length; i++){
            int ma = Fmax;
            int mi = Fmin;
            Fmax = Math.max(ma*nums[i], Math.max(mi*nums[i], nums[i]));
            Fmin = Math.min(mi*nums[i], Math.min(ma*nums[i], nums[i]));
            ans = Math.max(Fmax, ans);
        }
        return ans;
    }
}
