package Difficult.三个无重叠子数组的最大和;

public class MaximumSumOf3Non_OverlappingSubArrays {

    //2ms
    public static int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int N = nums.length;

        //从左边遍历
        int[] range = new int[N]; //以i位置结尾的子数组之和
        int[] left = new int[N];  // i位置及之前：和最大的子数组的结尾下标

        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        range[k-1] = sum;
        left[k-1] = k-1;

        int max = sum;
        for (int i = k; i < N; i++) { //从左边遍历数组
            sum = sum - nums[i-k] + nums[i]; //子数组之和
            range[i] = sum; // 存放到range数组

            // 处理left
            left[i] = left[i-1];//假设和最大的数组没变
            if (sum > max) {//变了。小于号保证字典序
                max = sum;
                left[i] = i;
            }
        }

        //从右边遍历
        sum = 0;
        for (int i = N - 1; i >= N - k; i--) {
            sum += nums[i];
        }
        max = sum;
        int[] right = new int[N]; //i及i之后：和最大的子数组开始下标
        right[N-k] = N-k; //最后一个数组 N-k到N-1
        for (int i = N - k - 1; i >= 0; i--) {
            sum = sum - nums[i + k] + nums[i];

            right[i] = right[i+1];
            if (sum >= max) { //大于等于号保证字典序
                max = sum;
                right[i] = i;
            }
        }

        int a = 0;
        int b = 0;
        int c = 0;
        max = 0;
        for (int i = 2*k-1; i < N-k; i++) { // 中间一块的起始点 (0...k-1)选不了 i == N-1
            int part1 = range[left[i-k]]; //左边的子数组最大和
            int part2 = range[i]; //中间数组之和
            int part3 = range[right[i+1]+(k-1)];//right[i+1]表示第三个子数组（和最大）的开始坐标，
            if (part1 + part2 + part3 > max) {
                max = part1 + part2 + part3;
                a = left[i-k]-(k-1);
                b = i-(k-1);
                c = right[i+1];
            }
        }
        return new int[] { a, b, c };
    }

    // 滑动窗口
    /*
         这道题有三个窗口，用三个变量分别记录三个窗口的目前的窗口总和
         因为在第一个窗口最大和的时候，第二个窗口也最大和的时候，这两个窗口是不会相交的。
         （根据滚动窗口，显然）
         要计算三个无重叠子数组的最大和，我们可以枚举第三个子数组的位置，
            同时维护前两个无重叠子数组的最大和及其位置。
        要计算两个无重叠子数组的最大和，我们可以枚举第二个子数组的位置，
            同时维护第一个子数组的最大和及其位置。
        因此，我们首先来解决单个子数组的最大和问题，然后解决两个无重叠子数组的最大和问题，
            最后解决三个无重叠子数组的最大和问题
     */
    static class Solution {
        public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
            int[] ans = new int[3];
            int sum1 = 0, maxSum1 = 0, maxSum1Idx = 0;
            int sum2 = 0, maxSum12 = 0, maxSum12Idx1 = 0, maxSum12Idx2 = 0;
            int sum3 = 0, maxTotal = 0;
            for (int i = k * 2; i < nums.length; ++i) {
                sum1 += nums[i - k * 2];
                sum2 += nums[i - k];
                sum3 += nums[i];
                if (i >= k * 3 - 1) {
                    if (sum1 > maxSum1) {
                        maxSum1 = sum1;
                        maxSum1Idx = i - k * 3 + 1;
                    }
                        if (maxSum1 + sum2 > maxSum12) {
                        maxSum12 = maxSum1 + sum2;
                        maxSum12Idx1 = maxSum1Idx;
                        maxSum12Idx2 = i - k * 2 + 1;
                    }
                    if (maxSum12 + sum3 > maxTotal) {
                        maxTotal = maxSum12 + sum3;
                        ans[0] = maxSum12Idx1;
                        ans[1] = maxSum12Idx2;
                        ans[2] = i - k + 1;
                    }
                    sum1 -= nums[i - k * 3 + 1];
                    sum2 -= nums[i - k * 2 + 1];
                    sum3 -= nums[i - k + 1];
                }
            }
            return ans;
        }
    }
}
