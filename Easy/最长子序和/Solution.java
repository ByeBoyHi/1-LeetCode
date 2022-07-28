package Easy.最长子序和;

/**
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * <p>
 * 示例 1：
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
 * <p>
 * 示例 2：
 * 输入：nums = [1]
 * 输出：1
 * <p>
 * 示例 3：
 * 输入：nums = [0]
 * 输出：0
 * <p>
 * 示例 4：
 * 输入：nums = [-1]
 * 输出：-1
 */

public class Solution {
    public static void main(String[] args) {
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray1(nums));
    }

    // 来自官网：动态规划
    public static int maxSubArray1(int[] nums) {
        int pre = 0, maxAns = nums[0];
        for (int x : nums) {
            pre = Math.max(pre + x, x); // pre在不断的累加
            maxAns = Math.max(maxAns, pre); //maxAns则在不断的判断，累加的值和之前的大值，到底谁更大，然后更新
        }
        return maxAns;
    }

    public static int maxSubArray(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int maxTotal = Integer.MIN_VALUE;
        int temp;
        for (int i = 0; i < nums.length; i++) {
            temp = nums[i];
            maxTotal = Math.max(temp, maxTotal);
            for (int j = i + 1; j < nums.length; j++) {
                if (temp + nums[j] >= 0) {
                    maxTotal = Math.max(Math.max(temp, temp + nums[j]), maxTotal);
                    temp = temp + nums[j];
                } else {
                    break;
                }
            }
        }
        return maxTotal;
    }

    public int maxSubArray2(int[] nums) {
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

// 使用一种新的数据结构：线段树进行解决问题
// 官网求解：分治法。
// 对于一个区间[l, r]，求最长子序和，可以分成两半，m=l+((r-l)>>1);
// 左子区间：[l, m]。右子区间：[m+1, r]。
// 分别求出两个子区间的最长子序和 lSum 和 rSum，以及各自的总和 iSum，和该区间[l, r]的最大子序和 mSum。
// 那么在计算[l, r]区间的最长子序和的时候，就是考虑这个子序和是否跨域中点 m。
/**
 * 如果不跨越 m，则取左子区间的 mSum 和右子区间的 mSum 的最大值。
 * 如果跨越 m，则取 左子区间的rSum + 右子区间的lSum 。
 */
// 当分治递归到 [i, i] 的时候，lSum,rSum,iSum,mSum都是 1。
// 并且有：
// lSum = max(左子区间的lSum，左子区间的iSum+右子区间的lSum) -->也就是取左部分的最大子序和
// rSum = max(右子区间的rSum，右子区间的iSum+左子区间的rSum) -->也就是取右部分的最大子序和
// iSum = l.iSum+r.iSum  --> 该区段的总和等于左子区间的总和加右子区间的总和
// mSum = max(左子区间的mSum， 右子区间的mSum， 左子区间的rSum+右子区间的lSum)  --> 最大子序和
class Status{
    // 区间[l, r]内
    int iSum; // 总和
    int mSum; // 最大子序和
    int lSum; // 以 l 为左端点的最大子段和
    int rSum; // 以 r 为右端点的最大子段和

    public static void main(String[] args) {
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray(nums));
    }
    public Status(int iSum, int mSum, int lSum, int rSum){
        this.iSum = iSum;
        this.mSum = mSum;
        this.lSum = lSum;
        this.rSum = rSum;
    }

    // 获取最大子序和
    public static int maxSubArray(int[] nums) {
        return getInfo(0,nums.length-1, nums).mSum;
    }
    public static Status getInfo(int l, int r, int[] nums){
        if (l==r){ // 递归到只有一个数字的时候
            return new Status(nums[l],nums[l],nums[l],nums[l]);
        }
        int m = l + ((r-l)>>1); // 计算中值
        Status left = getInfo(l, m, nums);  // 左递归，然后得到一个子区间对象
        Status right = getInfo(m+1, r, nums);  // 右递归，然后得到一个子区间对象

        // 递归结束后进行层层合并
        return merge(left, right);
    }
    public static Status merge(Status left, Status right){
        int iSum = left.iSum + right.iSum;
        int lSum = Math.max(left.lSum, left.iSum+ right.lSum);
        int rSum = Math.max(left.rSum+ right.iSum, right.rSum);
        int mSum = Math.max(Math.max(left.mSum, right.mSum), left.rSum+ right.lSum);
        return new Status(iSum, mSum, lSum, rSum);
    }

}
