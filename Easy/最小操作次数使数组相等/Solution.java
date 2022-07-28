package Easy.最小操作次数使数组相等;

import java.util.Arrays;

public class Solution {

    /**
     * 对数组中 n-1 个元素进行 +1 操作，使得最后所有元素都相等，得到对应的+1次数并返回。
     * @param nums  需要计算的数组
     * @return  返回最少的移动次数
     * 思路：所有 n-1 个元素 +1 到最大的那个，等效于一个最大的元素 -1 到最小的那个。
     */
    public int minMoves2(int[] nums){
        int min = getMinAndMax(nums)[0];
        int ans = 0;
        for (int num: nums){
            if (num!=min)
                ans = ans + (num-min);
        }
        return ans;
    }

    public int minMoves(int[] nums) {
        if (nums.length==1) return 0;
        int ans = 0;
        int min = getMinAndMax(nums)[0];  // 获取下界
        int max = getMinAndMax(nums)[1];  // 获取上界
        Arrays.sort(nums);
        while (max!=min){
            ans = ans + (max-min);
            for (int i=0; i<nums.length-1; i++){
                nums[i] = nums[i] + (max-min);
            }
            min = getMinAndMax(nums)[0];
            max = getMinAndMax(nums)[1];
            if (nums[nums.length-2]>nums[nums.length-1])
                Arrays.sort(nums);
        }
        return ans;
    }

    public int[] getMinAndMax(int[] arr){
        int max = 0;
        int min = 0;
        for (int i=1; i<arr.length; i++){
            if (arr[i]>arr[max]){
                max = i;
            }
            if (arr[i]<arr[min]){
                min = i;
            }
        }
        return new int[]{arr[min], arr[max]};
    }
}
