package 周赛.第262场周赛.至少在两个数组中出现的值;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 至少在两个数组中出现的值 {
    public static void main(String[] args) {
        int[] nums1 = new int[]{1,3};
        int[] nums2 = new int[]{6};
        int[] nums3 = new int[]{5};
        System.out.println(new Solution().twoOutOfThree(nums1, nums2, nums3));
    }
}

class Solution {
    public List<Integer> twoOutOfThree(int[] nums1, int[] nums2, int[] nums3) {
        List<Integer> ans = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();

        for (int num: nums1){
            map.put(num, 1);
        }

        for (int num:nums2){
            if (map.containsKey(num) && !ans.contains(num)){
                ans.add(num);
            }
        }
        for (int num: nums2){
            map.put(num, 1);
        }
        for (int num:nums3){
            if (map.containsKey(num) && !ans.contains(num)){
                ans.add(num);
            }
        }

        return ans;
    }

    public int getMax(int[] arr){
        int max = arr[0];
        for (int i=1; i<arr.length; i++){
            if (arr[i]>max){
                max = arr[i];
            }
        }
        return max;
    }

    public void initArr(int[] arr, int[] nums){
        for (int num : nums) { // 遍历所有的nums，把nums的对应的arr[i]加+1表示出现过
            arr[num]=1;
        }
    }
}