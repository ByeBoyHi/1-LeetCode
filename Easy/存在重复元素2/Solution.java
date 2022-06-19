package Easy.存在重复元素2;

import java.util.*;

public class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            List<Integer> list;
            list = map.getOrDefault(nums[i], new ArrayList<>());
            if (!list.isEmpty()) {
                for (int n : list) {
                    if (Math.abs(n - i) <= k) return true;
                }
            }
            list.add(i);
            map.put(nums[i], list);
        }
        return false;
    }

    public boolean containsNearbyDuplicate2(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], i);
            } else {
                if (Math.abs(map.get(nums[i]) - i) <= k) return true;
                map.put(nums[i], i);
            }
        }
        return false;
    }

    // LSE
    public boolean containsNearbyDuplicate3(int[] nums, int k) {
        int[] arr = new int[2000000005];
        for (int i = 0; i < nums.length; i++) {
            int cur = arr[i + 1000000000];
            if (cur != 0) {
                if (Math.abs(cur - i) <= k) return true;
                arr[i + 1000000000] = i;
            }
        }
        return false;
    }

    // 滑动窗口
    public boolean containsNearbyDuplicate4(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        k = Math.min(k, nums.length - 1);  // 确定k的最小范围
        for (int i = 0; i <= k; i++) {  // 在k个范围内出现重复数字，那么都是符合条件的
            if (!set.add(nums[i])) {
                return true;
            }
        }
        // 每当末尾有一个元素进入k范围的时候，头部就需要去除一个元素
        // 始终保持这窗口长度为k的范围，只要这个范围出现了重复元素
        // 那么就说明一定有两个元素，他们的索引值的差值是小于等于k的
        for (int i = k + 1; i < nums.length; i++) {
            set.remove(nums[i - k - 1]);
            if (!set.add(nums[i])) {
                return true;
            }
        }
        return false;
    }
}
