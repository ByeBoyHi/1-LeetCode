package 动态规划.中等.跳跃游戏2;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().jump(new int[]{
//                2,3,1,1,4
                1,1,1,1
        }));
    }

    // 这道题一定能走到终点
    public int jump(int[] nums) {
        if (nums.length==1) return 0;
        int x = 0;
        int y = x;
        int num = 0;
        int i = x + 1;
        while (x+nums[x]<nums.length-1) {
            for (; i <= nums[x]+x && i < nums.length; i++) {
                if (nums[i] + i-nums[y]-y>0) {
                    y = i;
                }
            }
            num++;
            i = nums[x]+1+x;
            x = y;
        }
        return num+1;
    }
}
