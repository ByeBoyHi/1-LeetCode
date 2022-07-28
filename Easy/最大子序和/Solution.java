package Easy.最大子序和;

/**
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * <p>
 * 示例:
 * <p>
 * 输入: [-2,1,-3,4,-1,2,1,-5,4]
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * 进阶:
 * <p>
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 */

public class Solution {

    public static void main(String[] args) {
        int arr[] = {-1,6,7,-7,-2,-6,-1,3,4,2,6,-3,-8,-1,7};
        System.out.println(new Solution().maxSubArray(arr));
    }

    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        int res = nums[0];
        int temp = 0;
        int max = Integer.MIN_VALUE;
        int index = 1;
        for (int i = 1; i < nums.length; i++) {
            if (res>max){
                max = res;
            }
            if (nums[i] > res && res < 0) {
                index = i + 1;
                res = nums[i];
            } else {
                if (res >= 0) {
                    if ((nums[i]+res) >= 0) {
                        if (index == i) {
                            res += nums[i];
                            index = i + 1;
                        } else {
                            temp += nums[i];
                        }
                        if (temp>=res){
                            res = temp;
                            temp = 0;
                            index = i+1;
                        }
                    }
                }
            }
        }

        return Math.max(res, max);
    }
}
