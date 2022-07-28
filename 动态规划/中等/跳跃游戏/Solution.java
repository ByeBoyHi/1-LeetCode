package 动态规划.中等.跳跃游戏;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().canJump2(new int[]{2,3,1,1,4}));
    }

    /**
     * 对于当前位置 x，他可以活动的范围是 x ~ x+nums[x]。
     * 在这里面找到一个 y，使得 nums[y] 走出的距离是最长的：nums[y]-nums[x]+y。
     * 并且要保证这个距离是超过了nums[x]，如果没有超过，直接返回false。
     * 再判断这个距离是否超过了终点的位置，如果超过了，那么返回true，否则继续往后找。
     * <p>
     * 对于这个算法：初始值 --> x = 0，范围是 0~nums[0]
     * 定义一个变量，用来记录最长距离的下标 y。
     */
    public boolean canJump(int[] nums) {
        int x = 0;
        int y = x;
        while (true) {
            boolean flag = false;
            int i;
            for (i = x + 1; i <= nums[x] + x && i < nums.length; i++) {
                if ((nums[i] + i - nums[x] - x) > 0) { // 至少要走出x+nums[x]范围
                    if ((nums[i] - nums[x] + i) > (nums[y] - nums[x] + y)) {  // 能比y走更远
                        y = i; // 记录新的起点
                        flag = true;
                    }
                }
            }
            // 已经有点可以走到终点了
            if (nums[y] + y >= nums.length - 1 || i == nums.length) {
                return true;
            }
            if (flag) {  // 如果有新的起点，重新挪位
                x = y;
            } else {  // 没有一个点能走出 x+nums[x]
                return false;
            }
        }
    }

    /**
     * 思路：
     * 从后往前遍历，找第一个能到最后一个点的位置，然后再往前找，找第一个能到当前点的位置，
     * 以此类推，如果最后能走到起点，就 return true，否则 false
     */
    public boolean canJump2(int[] nums) {
        int x = nums.length-1;
        for (int i = x-1; i >= 0; i--) {
            if (nums[i]+i>=x){
                x = i;
            }
        }
        return x==0;
    }
}
