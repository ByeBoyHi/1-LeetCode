package 动态规划.中等.打家劫舍;

public class Solution {
    public static void main(String[] args) {
        int[] nums = new int[]{
//                1,2,3,1
                2,7,9,3,1
        };
        System.out.println(new Solution().rob(nums));
    }

    public int rob(int[] nums) {
        if(nums.length==1) return nums[0];
        if (nums.length==2) return Math.max(nums[0], nums[1]);

        // 当有3间及以上的房间个数的时候。
        int[] money = new int[nums.length];
        money[0] = nums[0];
        money[1] = Math.max(nums[0], nums[1]);
        money[2] = money[0] + nums[2];
        for (int i=3; i<money.length; i++){
            money[i] = Math.max(money[i-2], money[i-3]) + nums[i];
        }
        return Math.max(money[money.length-1], money[money.length-2]);
    }
}
