package Middle.三数之和;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sum3 {
    /**
     * 思路：使用双指针和双层循环进行寻找。
     * <p>
     * 1. 首先对数组进行排序，这样可以保证我们判断元素的时候，有大小关系
     * <p>
     * 2. 因为有大小关系，当有相同的元素挨着的时候，我们应该跳过后面的相同元素。
     * <br>
     * 比如：0 1 2 2 -->跳过第二个2，可以保证不会有重复（两个 0 1 2）
     * <p>
     * 3. 外层循环进行所有元素的放入，从 0 到 n-3，最后两个元素不放入，
     * 因为我们的排序关系，第一层放入最后两个，到最后也凑不够三个
     * <p>
     * 4. 外层+内层循环，有三个数字，假设外层 a， 内层 b 和 c，使得 a+b+c=0
     * 那么在最外层 a 确定下来之后，当得到一个 b，存在一个 c'使得 a+b+c'=0
     * <p>
     * 由于 b 和 c存在一个相对关系，
     * 即：假如 b 从小到大，c 从大到小，那么对于最里层只需要判断 a+b+c>0，就循环，否则判断是否等于 0
     * 同理，b 和 c 都从小到大，那么需要判断 a+b+c<0，就循环，否则判断是否等于0
     * 这个判断是由于c的运动方向决定的。
     * <p>
     * 以第一个假设为下面算法的使用，必须保证c在b的右侧，如果跑到左侧，会出现重复元素的情况
     * <p>
     * 对数组里面的元素，选出里面的三个元素，要求三个元素之和为0，返回其所有可能且不重复
     * @param nums 需要寻找结果的数组
     * @return 返回的符合条件的三元素集合
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        for (int a = 0; a < nums.length - 2; a++) {
            if (a > 0 && nums[a] == nums[a - 1]) {  // 重复元素
                continue;
            }
            int c = nums.length - 1; // 从最后一个开始往前走
            for (int b = a + 1; b < nums.length - 1; b++) {
                if (b > a + 1 && nums[b] == nums[b - 1]) {  // 重复元素
                    continue;
                }

                if (b == c) {  // 相遇了
                    break;
                }

                // 保证在右侧
                // 这里b+1使用因为，如果不+1，c=b+1的时候进入循环，然后c--，就等于b了，相当于b这个数字用了两次
                while (b + 1 < c && nums[a] + nums[b] + nums[c] > 0) {
                    c--;
                }
                if (check(nums[a], nums[b], nums[c])) {
                    List<Integer> cur = new ArrayList<>();
                    cur.add(nums[a]);
                    cur.add(nums[b]);
                    cur.add(nums[c]);
                    ans.add(cur);
                }
            }
        }
        return ans;
    }

    public boolean check(int a, int b, int c) {
        return a + b + c == 0;
    }


    /*
        双指针的优化在于：
            原本是双重循环的问题，需要n^2去实现。
            但是通过两头往中间走，就会被优化成O(n)的时间复杂度。
        既然是双指针方案，我们可以两头一起走，官解的双指针只走了一头。
     */
    public List<List<Integer>> threeSum2(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        int len = nums.length;
        for (int a=0; a<len; a++){
            while (a>0 && a<len && nums[a]==nums[a-1]){ // 去重
                a++;
            }

            int target = -nums[a];
            int start = a+1;
            int end = len-1;
            while (start<end){
                if (nums[start]+nums[end]<target){  // 如果太小了，左边的小值往大的方向走
                    start++;
                } else if (nums[start]+nums[end]>target){  // 如果太大了，右边的大值往小的方向走
                    end--;
                }else {
                    List<Integer> cur = new ArrayList<>();
                    cur.add(nums[a]);
                    cur.add(nums[start]);
                    cur.add(nums[end]);
                    ans.add(cur);

                    while (start < end && nums[start] == nums[start + 1]) start++;
                    while (start < end && nums[end] == nums[end - 1]) end--;
                    // 上面两个数字是和当前a已经组合完后的结果
                    // 下一次循环，需要两个都往中间移动一步
                    start++;
                    end--;
                }
            }
        }

        return ans;
    }
}
