package Easy.最长和谐子序列;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LongestHarmoniousSubsequence {

    public static void main(String[] args) {
        System.out.println(new LongestHarmoniousSubsequence().findLHS(new int[]{
                1,3,2,2,5,2,3,7
        }));
    }

    /*
        1. 暴力破解
     */
    public int findLHS(int[] nums) {
        Arrays.sort(nums);
        int start = 0;
        int end = 0;
        for (int i=0; i<nums.length; i++){
            int e = i;
            for (int j=i+1; j<nums.length; j++){
                if (nums[j]-nums[i]==1){
                    e=j;
                }else if (nums[j]-nums[i]>1) {
                    break;
                }
            }
            if (e-i>end-start){
                start = i;
                end = e;
            }
        }
        if (end==0) return 0;
        return end-start+1;
    }

    /*
        2. 双指针
     */
    public int findLHS2(int[] nums) {
        Arrays.sort(nums);
        int start = 0;
        int ans = 0;
        // end作为尾部，控制start在end之前移动，并且只移动一次，不会回到0
        // 且始终不会超过end，因为当在end的时候，他俩的差等于0，会退出while，判断if，然后进行下一层循环
        for (int end=0; end<nums.length; end++){
            while (nums[end]-nums[start]>1){
                start++;
            }
            if (nums[end]-nums[start]==1){
                ans =Math.max(ans, end-start+1);
            }
        }
        return ans;
    }

    /*
        3. 哈希表：记录每个数字出现的位置
     */
    public int findLHS3(int[] nums) {
        Map<Integer,Integer> map = new HashMap<>();
        int ans = 0;
        for (int num: nums){
            map.put(num, map.getOrDefault(num, 0)+1);
        }

        for (int num: nums){
            if (map.containsKey(num+1)){
                ans = Math.max(ans, map.get(num)+map.get(num+1));
            }
        }
        return ans;
    }
}
