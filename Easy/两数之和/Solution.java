package Easy.两数之和;

import java.util.*;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * <p>
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 * <p>
 * 示例:
 * 给定 nums = [2, 7, 11, 15], target = 9
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 */
public class Solution {
    public static void main(String[] args) {
        int[] nums = {3, 2, 4};
        System.out.println(Arrays.toString(new Solution().twoSum(nums, 6)));
    }

    public int[] twoSum(int[] nums, int target) {
        int[] n = new int[2];
        List<Integer> res = new ArrayList<>();
        for (int num : nums) {
            res.add(num);
        }
        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            int index = res.indexOf(target - cur);
            if (index != -1 && index != i) {
                n[0] = i;
                n[1] = index;
                break;
            }
        }
        if (n[0] == 0 && n[1] == 0)
            return new int[0];
        else
            return n;
    }

    // map
    public int[] twoSum2(int[] nums, int target) {
        int[] n = new int[2];
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.get(nums[i]).add(i);
            } else {
                List<Integer> cur = new ArrayList<>();
                cur.add(i);
                map.put(nums[i], cur);
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                // 两个相同的数字
                if (nums[i] * 2 == target && map.get(nums[i]).size() > 1) {
                    List<Integer> cur = map.get(nums[i]);
                    n[0] = cur.get(0);
                    n[1] = cur.get(1);
                    break;
                } else {  // 两个不同的数字
                    n[0] = i;
                    n[1] = map.get(target - nums[i]).get(0);
                    break;
                }
            }
        }
        return n;
    }

    // 双指针
    public int[] twoSum3(int[] nums, int target) {
        int[] n = new int[2];
        Map<Integer, Stack<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.get(nums[i]).push(i);
            } else {
                Stack<Integer> cur = new Stack<>();
                cur.add(i);
                map.put(nums[i], cur);
            }
        }

        Arrays.sort(nums);
        int left = 0, right = nums.length - 1;
        while (left < right) {
            if (nums[left] + nums[right] == target) {
                if (nums[left] == nums[right]) {
                    Stack<Integer> stack = map.get(nums[left]);
                    n[0] = stack.pop();
                    n[1] = stack.pop();
                } else {
                    n[0] = map.get(nums[left]).pop();
                    n[1] = map.get(nums[right]).pop();
                }
                break;
            } else if (nums[left] + nums[right] < target) {
                left++;
            } else {
                right--;
            }
        }
        return n;
    }
}
