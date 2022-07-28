package PrimaryAlgorithm.删除有序数组中的重复项;

public class Solution {
    public int removeDuplicates(int[] nums) {
        if(nums.length == 0) return 0;
        int cur = 1;
        for(int i=1; i<nums.length; i++){
            if(nums[i] == nums[i-1]){
                continue;
            }
            nums[cur] = nums[i];
            cur++;
        }
        return cur;
    }
}
