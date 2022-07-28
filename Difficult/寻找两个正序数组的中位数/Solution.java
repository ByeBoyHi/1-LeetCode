package Difficult.寻找两个正序数组的中位数;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 题目：
 * 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的中位数。
 * <p>
 * 进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？
 * ==========================================================================
 * 示例 1：
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 * <p>
 * 示例 2：
 * 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：2.50000
 * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 *
 * @author 唐
 * @date 2020/10/14 0014
 * @time 19:43
 */
public class Solution {

    public static void main(String[] args) {
        int[] a = new int[]{};
        int[] b = new int[]{1,2,3,4,5};
        System.out.println(new Solution().findMedianSortedArrays(a, b));
    }

    /**
     * @param nums1 数组1
     * @param nums2 数组2
     * @return 返回两个数组合并的中位数。
     * <p>
     * 用两个堆，一个大根堆，一个小根堆
     * 1. 要求两个堆的元素之差不能超过1
     * 2. 大根堆的所有元素都小于小根堆的元素
     * 3. 先填充大根堆，如果相差超过1，那么让大根堆的堆顶元素进入小根堆
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        PriorityQueue<Integer> huge = new PriorityQueue<>((x, y) -> y - x); // 大根堆
        PriorityQueue<Integer> small = new PriorityQueue<>(); // 默认小根堆
        int i1 = 0;
        int i2 = 0;
        int len1 = nums1.length;
        int len2 = nums2.length;
        while (i1 < len1 || i2 < len2) {
            if (i1 < len1 && i2 < len2) {
                if (nums1[i1] < nums2[i2]) {
                    huge.add(nums1[i1]);
                    i1++;
                } else {
                    huge.add(nums2[i2]);
                    i2++;
                }
            } else if (i1 == len1) {
                huge.add(nums2[i2++]);
            } else if (i2 == len2) {
                huge.add(nums1[i1++]);
            }
            if (huge.size() - small.size() > 1) {
                small.add(huge.poll());
            }else if (!huge.isEmpty() && !small.isEmpty() && huge.peek() > small.peek()){
                int t1 = huge.poll();
                int t2 = small.poll();
                huge.add(t2);
                small.add(t1);
            }

        }
        assert !huge.isEmpty();
        assert !small.isEmpty();
        if (((huge.size() + small.size()) & 1) == 0) { // 偶数
            return (huge.peek() + small.peek()) / 2.0;
        } else {
            return huge.peek()*1.0;
        }
    }

    // 直接合并然后找中间值
    public double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        int l1 = nums1.length;
        int l2 = nums2.length;
        int[] ans = new int[l1+l2];
        int idx = 0;
        for (int k : nums1) {
            ans[idx++] = k;
        }
        for (int j : nums2) {
            ans[idx++] = j;
        }
        Arrays.sort(ans);
        if (((l1+l2)&1)==0){ // 偶数
            int l = ans.length;
            return (ans[l/2]+ans[l/2-1])/2.0;
        }
        return ans[ans.length/2]*1.0;
    }

    // 划分数组：也就是每个元素都可以划分为左边和右边两个集合，左边的都比右边的小
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays2(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;
        int left = 0, right = m;
        // median1：前一部分的最大值
        // median2：后一部分的最小值
        int median1 = 0, median2 = 0;

        while (left <= right) {
            // 前一部分包含 nums1[0 .. i-1] 和 nums2[0 .. j-1]
            // 后一部分包含 nums1[i .. m-1] 和 nums2[j .. n-1]
            int i = (left + right) / 2;
            int j = (m + n + 1) / 2 - i;

            // nums_im1, nums_i, nums_jm1, nums_j 分别表示 nums1[i-1], nums1[i], nums2[j-1], nums2[j]
            int nums_im1 = (i == 0 ? Integer.MIN_VALUE : nums1[i - 1]);
            int nums_i = (i == m ? Integer.MAX_VALUE : nums1[i]);
            int nums_jm1 = (j == 0 ? Integer.MIN_VALUE : nums2[j - 1]);
            int nums_j = (j == n ? Integer.MAX_VALUE : nums2[j]);

            if (nums_im1 <= nums_j) {
                median1 = Math.max(nums_im1, nums_jm1);
                median2 = Math.min(nums_i, nums_j);
                left = i + 1;
            } else {
                right = i - 1;
            }
        }

        return (m + n) % 2 == 0 ? (median1 + median2) / 2.0 : median1;
    }

    // 二分查找
    public double findMedianSortedArrays3(int[] nums1, int[] nums2) {
        int length1 = nums1.length, length2 = nums2.length;
        int totalLength = length1 + length2;
        if (totalLength % 2 == 1) {
            int midIndex = totalLength / 2;
            double median = getKthElement(nums1, nums2, midIndex + 1);
            return median;
        } else {
            int midIndex1 = totalLength / 2 - 1, midIndex2 = totalLength / 2;
            double median = (getKthElement(nums1, nums2, midIndex1 + 1) + getKthElement(nums1, nums2, midIndex2 + 1)) / 2.0;
            return median;
        }
    }

    public int getKthElement(int[] nums1, int[] nums2, int k) {
        /* 主要思路：要找到第 k (k>1) 小的元素，那么就取 pivot1 = nums1[k/2-1] 和 pivot2 = nums2[k/2-1] 进行比较
         * 这里的 "/" 表示整除
         * nums1 中小于等于 pivot1 的元素有 nums1[0 .. k/2-2] 共计 k/2-1 个
         * nums2 中小于等于 pivot2 的元素有 nums2[0 .. k/2-2] 共计 k/2-1 个
         * 取 pivot = min(pivot1, pivot2)，两个数组中小于等于 pivot 的元素共计不会超过 (k/2-1) + (k/2-1) <= k-2 个
         * 这样 pivot 本身最大也只能是第 k-1 小的元素
         * 如果 pivot = pivot1，那么 nums1[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums1 数组
         * 如果 pivot = pivot2，那么 nums2[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums2 数组
         * 由于我们 "删除" 了一些元素（这些元素都比第 k 小的元素要小），因此需要修改 k 的值，减去删除的数的个数
         */

        int length1 = nums1.length, length2 = nums2.length;
        int index1 = 0, index2 = 0;
        int kthElement = 0;

        while (true) {
            // 边界情况
            if (index1 == length1) {
                return nums2[index2 + k - 1];
            }
            if (index2 == length2) {
                return nums1[index1 + k - 1];
            }
            if (k == 1) {
                return Math.min(nums1[index1], nums2[index2]);
            }

            // 正常情况
            int half = k / 2;
            int newIndex1 = Math.min(index1 + half, length1) - 1;
            int newIndex2 = Math.min(index2 + half, length2) - 1;
            int pivot1 = nums1[newIndex1], pivot2 = nums2[newIndex2];
            if (pivot1 <= pivot2) {
                k -= (newIndex1 - index1 + 1);
                index1 = newIndex1 + 1;
            } else {
                k -= (newIndex2 - index2 + 1);
                index2 = newIndex2 + 1;
            }
        }
    }
}
