package Daily_Topic.打家劫舍Ⅱ;


/**
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，
 * 这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的
 * 房屋在同一晚上被小偷闯入，系统会自动报警 。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下 ，能够偷窃到的最高金额。
 *
 *
 * 示例1：
 * 输入：nums = [2,3,2]
 * 输出：3
 * 解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
 *
 * 示例 2：
 * 输入：nums = [1,2,3,1]
 * 输出：4
 * 解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
 *    偷窃到的最高金额 = 1 + 3 = 4 。
 *
 * 示例 3：
 * 输入：nums = [0]
 * 输出：0
 *
 *
 * 提示：
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 1000
 */
public class Solution {
    public static void main(String[] args) {
        int[] nums = {1,2,3,1};
        System.out.println(new Solution().rob(nums));
    }
    /**
     * 动态规划
     * 1. 如果只有一间屋子，则只有一种可能。
     * 2. 如果有两间屋子，则选择其中大的那个。
     * 3. 如果大于两间，则有：
     *      1）偷第一间，则不偷最后一间
     *      2）偷最后一间则不偷第一间
     *
     * dp[i]表示[0,i]间之间的最大金额，则有dp[i]=max(dp[i-1], dp[i-2]+nums[i])
     * 第一种：不偷第i间，取前i-1间的最大金额。
     * 第二种：偷第i间，取前i-2间的最大金额。
     *
     * 边界：
     * dp[start]=nums[start]  // 只有一间
     * dp[start+1]=max(nums[start], start[nums+1])  // 两间屋，取两者的较大值
     *
     * dp[end]为[start, end]范围的最大金额。
     * 分别取(start, end) = (0, n-2) 和 (1, n-1)
     *
     * @param nums 住户金额
     * @return 最大金额
     */
    public int rob(int [] nums) {
        if (nums.length==1){
            return nums[0];
        }else if (nums.length==2){
            return Math.max(nums[0], nums[1]);
        }
        // 头和尾不可能同时用上。
        return Math.max(robRange(0, nums.length-2, nums),
                robRange(1, nums.length-1, nums));
    }

    // 这里的first second用了之前的最优买股票策略的方式，把递归的数组换成了两个变量
    // first是嵌套的最大金额，second是昨天的，nums[i]是今天的
    public int robRange(int start, int end, int[] nums){
        int first = nums[start];
        int second = Math.max(nums[start], nums[start+1]);
        for (int i=start+2; i<=end; i++){  // end是要走到的
            int temp = second;
            // first+nums[i]求的是隔间和与原来的谁更大
            second = Math.max(first+nums[i], second);
            // first记录之前的second
            first = temp;
        }
        return second;
    }
}
