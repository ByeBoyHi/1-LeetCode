package Middle.求众数2;

import java.util.*;

public class Solution {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> res = new ArrayList<>();
        Arrays.sort(nums);
        int level = nums.length/3;
        int k = 1;
        for (int i=1; i<nums.length; i++){
            if (nums[i]== nums[i-1]){
                k++;
            }else{
                if (k>level){
                    res.add(nums[i-1]);
                }
                k=1;
            }
        }
        if (k>level){
            res.add(nums[nums.length-1]);
        }
        return res;
    }
}
