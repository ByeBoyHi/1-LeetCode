package Daily_Topic.第三大的数;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().thirdMax2(new int[]{
                -2147483648, 1, 1
        }));
    }

    public int thirdMax(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        ArrayList<Integer> list = new ArrayList<>(set);
        list.sort(Comparator.comparingInt(a -> a));
        if (list.size() >= 3) {
            return list.get(list.size() - 3);
        }
        return list.get(list.size() - 1);
    }

    public int thirdMax2(int[] nums) {
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);

        int[] max = new int[3];
        int num = 0;
        // 大于等于3的情况
        for (int i=0; i<nums.length; i++){
            for (int j=i+1; j<nums.length; j++){
                if (nums[i]<nums[j]){
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
            }
            if (num==0) {
                max[0] = nums[i];
                num++;
            }else {
                if (max[num - 1] > nums[i]) {
                    max[num++] = nums[i];
                }
            }
            if (num==3){
                break;
            }
        }
        if (num<3)  // 去重排序后，剩下的数字小于3
            return max[0];
        return max[2];
    }
}
