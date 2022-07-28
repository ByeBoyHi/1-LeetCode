package Middle.打乱数组;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

public class ShuffleArray {
    static class Solution {
        int[] nums;
        Random random;

        public Solution(int[] nums) {
            this.nums = nums;
            this.random = new Random();
        }

        public int[] reset() {
            return this.nums;
        }

        public int[] shuffle() {
            int[] res = new int[this.nums.length];
              int num = 0;
            int n = res.length;
            HashSet<Integer> set = new HashSet<>();
            while (num<n){
                int rand = this.random.nextInt(n);  // 生成的随机数是开区间
                if (!set.contains(nums[rand])){
                    res[num++] = this.nums[rand];
                    set.add(this.nums[rand]);
                }
            }
            return res;
        }
    }
}
