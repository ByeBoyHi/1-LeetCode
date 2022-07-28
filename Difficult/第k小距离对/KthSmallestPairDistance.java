package Difficult.第k小距离对;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

public class KthSmallestPairDistance {

    /**
     * 找到相差第k小的数字对。
     * 暴力破解。
     * MLE
     */
    public int smallestDistancePair(int[] nums, int k) {
        TreeMap<Integer, List<Node>> treeMap = new TreeMap<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                Node node = new Node(nums[i], nums[j], Math.abs(nums[i] - nums[j]));
                if (treeMap.containsKey(node.distance)) {
                    treeMap.get(node.distance).add(node);
                } else {
                    List<Node> list = new ArrayList<>();
                    list.add(node);
                    treeMap.put(node.distance, list);
                }
            }
        }
        int ans = 0;
        for (Integer cur : treeMap.keySet()) {
            ans += treeMap.get(cur).size();
            if (ans >= k) {
                return cur;
            }
        }
        return -1;
    }

    static class Node {
        int x;
        int y;
        int distance;

        public Node(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }

    /**
     * 用二分查找的方式进行搜索
     * 1. 对数组进行排序。
     * 2. 把当前数字减去，得到剩下的，然后从开头到当前数字这个范围内，去找二分找符合小于等于剩下数字的数量
     * 3. 每次都会对数字进行统计求和，如果大于k，说明我们找的范围大了，减少一半
     * 4. 如果小于等于k，说明我们找的范围小了，就往上找
     */
    public int smallestDistancePair2(int[] nums, int k) {
        Arrays.sort(nums); // 先进行排序
        int left = 0;  // 最小差值
        int right = nums[nums.length - 1] - nums[0];  // 最大差值
        while (left <= right) {
            int mid = (left + right) / 2;  // 取到本次的中间值
            int cnt = 0;
            for (int j = 0; j < nums.length; j++) {
                cnt += binarySearch(nums, j, nums[j] - mid);
            }
            if (cnt < k) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    // 在 0~end 范围上，找小于等于target的数字的个数
    public int binarySearch(int[] arr, int end, int target) {
        int left = 0;
        int right = end;
        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    // 还可以把二分查找换成双指针
    // 左边不动，右边动，直到num[right]-num[left]<=mid
    // 那么right-left就是这之间所有的小于等于mid的数量
    public int smallestDistancePair3(int[] arr, int k) {
        Arrays.sort(arr);
        int n = arr.length;
        int left = 0;
        int right = arr[n - 1] - arr[0];
        while (left <= right) {
            int cnt = 0;
            int mid = (left + right) / 2;
            for (int j = 0, i = 0; j < n; j++) {  // 每次for循环，i都会来到新的位置，也就避免了我们已经统计的位置重复统计
                while (arr[j] - arr[i] > mid) {
                    i++;
                }
                cnt += j - i;
            }
            // 当大于等于的时候，说明我们找的范围大了，
            if (cnt >= k) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}
