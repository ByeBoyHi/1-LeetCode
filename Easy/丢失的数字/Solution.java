package Easy.丢失的数字;

import java.util.BitSet;

public class Solution {

    // O(n)  O(n)
    public int missingNumber(int[] nums) {
        int[] arr = new int[nums.length+1];
        for (int i=0; i<nums.length; i++){
            arr[nums[i]] = 1;
        }
        int ans = 0;
        for (int i=0; i<arr.length; i++){
            if (arr[i]==0){
                ans = i;
                break;
            }
        }
        return ans;
    }

    // 位运算O(n) O(1)
    public int missingNumber2(int[] nums){
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }
        for (int i=0; i<=nums.length; i++){
            xor^=i;
        }
        return xor;
    }

    // 数学公式：前n项和减去数组的每一个元素，剩下的结果就是缺失的值
    // Sn = n*(n+1)/2
    // O(n)  O(1)
    public int missingNumber3(int[] nums){
        int n = nums.length;
        int Sn = n*(n+1)/2;
        for (int num : nums) {
            Sn -= num;
        }
        return Sn;
    }
}
