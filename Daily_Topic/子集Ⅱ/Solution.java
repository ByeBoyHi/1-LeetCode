package Daily_Topic.子集Ⅱ;

import java.util.*;

/**
 * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
 * <p>
 * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
 * <p>
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,2]
 * 输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
 * <p>
 * 示例 2：
 * 输入：nums = [0]
 * 输出：[[],[0]]
 * <p>
 * 提示：
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 */
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] ints = new int[]{1,2,2};
        System.out.println(solution.subsetsWithDup(ints));
    }

    /**
     * 思路：里面的元素可以有重复，相同的元素，产生的一部分序列是相同的。
     * 1. 需要记录新元素开始的位置
     * 2. 用一个备用List<List<Integer>>在内层循环进行筛选添加，最后再全部放入主要的返回对象中
     * 3. 外面的主要返回对象用于记录元素有多少，进行映像添加
     *
     * @param nums int[] 可以有重复元素
     * @return 返回所有不重复子集
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(new ArrayList<>());  // 先把空集放进去
        Arrays.sort(nums);  // 对数组进行排序

        int start = 1;  // 记录之前的长度，用于判断重复元素

        for (int i = 0; i < nums.length; i++) {  // 外层用于取数组的元素，用于对外层的影响添加不重复子集
            List<List<Integer>> ans_tmp = new ArrayList<>();

            for (int j = 0; j < ans.size(); j++) {  // 内层用于取已经有记录的元素，用于外层进行映像
                List<Integer> list = ans.get(j);
                if (i > 0 && nums[i] == nums[i - 1] && j < start) {
                    continue;
                }

                List<Integer> tmp = new ArrayList<>(list);
                tmp.add(nums[i]);
                // 同样是往列表list里面添加元素nums[i]
                // 上面的两行代码为什么不能换成：list.add(nums[i])
                // 为了保证list不被改变 -- list存放的之前的子集

                ans_tmp.add(tmp);
            }

            /**
             * 如果有重复的元素挨着出现，那么他和前面的元素进行组合的时候，是他前一个重复元素已经组合过的次数
             * 也就是上一次记录的总的个数
             */
            start = ans.size();   // 更新新解开始的位置
            ans.addAll(ans_tmp);
        }

        return ans;
    }
}
