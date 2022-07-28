package Middle.查找和最小的K对数字;

import java.util.*;

public class K_MinPair {
    // TLE
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        Map<Integer, List<List<Integer>>> sort = new TreeMap<>();
        for (int key : nums1) {
            for (int value : nums2) {
                int cur = key + value;
                List<List<Integer>> list;
                list = sort.getOrDefault(cur, new ArrayList<>());
                List<Integer> tmp = new ArrayList<>();
                tmp.add(key);
                tmp.add(value);
                list.add(tmp);
                sort.put(cur, list);
            }
        }

        int num = 0;
        for (List<List<Integer>> list : sort.values()) {
            for (List<Integer> cur : list) {
                ans.add(cur);
                num++;
                if (num == k) break;
            }
            if (num == k) break;
        }

        return ans;
    }

    // 优先队列
    public List<List<Integer>> kSmallestPairs2(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(k, (o1, o2) -> nums1[o1[0]] + nums2[o1[1]] - nums1[o2[0]] - nums2[o2[1]]);

        List<List<Integer>> ans = new ArrayList<>();
        int m = nums1.length;
        int n = nums2.length;
        // 第一个放的m的坐标，第二个放的n的坐标
        // 最多放入k组
        for (int i = 0; i < Math.min(m, k); i++) {
            pq.offer(new int[]{i, 0});
        }
        while (k-- > 0 && !pq.isEmpty()) {
            int[] idxPair = pq.poll();
            List<Integer> list = new ArrayList<>();
            list.add(nums1[idxPair[0]]);
            list.add(nums2[idxPair[1]]);
            ans.add(list);
            if (idxPair[1] + 1 < n) {
                // 每弹出一个，都放入一个，这次是从n这边开始放的
                // 并且最开始已经将m的数组中足够多的元素放进去
                // 这里只需要考虑n数组的元素，即可达到k组的标准
                pq.offer(new int[]{idxPair[0], idxPair[1] + 1});
            }
        }

        return ans;
    }
}
