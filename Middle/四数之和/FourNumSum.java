package Middle.四数之和;

import java.util.*;

public class FourNumSum {
    // 双指针可以去掉一个N的时间复杂度
    public List<List<Integer>> fourSum(int[] nums, int target) {
        int len = nums.length;
        Arrays.sort(nums);
        Set<List<Integer>> ans = new HashSet<>();
        for (int one=0; one<len-1; one++){
            for (int two = one+1; two < len; two++) {
                int three = two+1;
                int four = len-1;
                while (three<four){
                    int less = target-nums[one]-nums[two]-nums[three]-nums[four];
                    if (less==0){
                        List<Integer> cur = new ArrayList<>();
                        cur.add(nums[one]);
                        cur.add(nums[two]);
                        cur.add(nums[three]);
                        cur.add(nums[four]);
                        ans.add(cur);
                        three++;
                        four--;
                        // 略过当前的所有重复数字，避免重复相加
    //                    while (three< four && nums[three]==nums[three-1]) three++;
    //                    while (three< four && nums[four]==nums[four+1]) four--;
                    }else if (less<0){
                        four--;
                    }else {
                        three++;
                    }
                }
    //            two++;
    //            while (two<len && nums[two]==nums[two-1]) two++;
            }
        }
        return new ArrayList<>(ans);
    }
}
