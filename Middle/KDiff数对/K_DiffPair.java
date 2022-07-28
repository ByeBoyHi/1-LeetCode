package Middle.KDiff数对;

import java.util.Arrays;

public class K_DiffPair {

    public static void main(String[] args) {
        int[] arr = new int[]{
//                1,3,1,5,4
//                3,1,4,1,5
                1,2,4,4,3,3,0,9,2,3
        };
        System.out.println(findPairs(arr, 3));
    }

    public static int findPairs(int[] nums, int k) {
        if (nums.length==1) return 0;
        Arrays.sort(nums);
        int ans = 0;
        int n = nums.length;
        // 找每个数字符合的target的数量
        for (int i=0; i<n-1; i++){
            if (i!=0 && nums[i]==nums[i-1]) continue;  // 得去重
            ans+=binarySearch(nums, nums[i]+k, i+1, n-1);
        }
        return ans;
    }

    // 二分查找，找到等于target的数字即可，这个数对具有唯一性，要去重
    public static int binarySearch(int[] arr, int target, int start, int end){
        while (start<end){
            int mid = (start+end)/2;
            if (arr[mid]<target){
                start = mid+1;
            }else if (arr[mid]>target){
                end = mid-1;
            }else {
                return 1;
            }
        }
        return 0;
    }
}
