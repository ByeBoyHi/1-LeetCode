package Middle.最接近的三数之和;

import java.util.Arrays;

public class _3SumClosest {
    // 暴力试试
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int ans = 100000;
        int len = nums.length;
        for (int a=0; a<len; a++){
            if (a>0 && nums[a-1]==nums[a]) continue;
            for (int b=a+1; b<len; b++){
                for (int c=b+1; c<len; c++){
                    int cur = nums[a]+nums[b]+nums[c];
                    if (Math.abs(cur-target)<Math.abs(ans-target)) ans = cur;
                    else break;
                }
            }
        }

        return ans;
    }

    // 双指针：对原数组进行排序
    // 固定数字a
    // 在 a+1~末尾，找b 和 c
    // 当 a+b+c>target的时候，c左移，因为b右移后，依然大于，没必要
    // 当 a+b+c<target的是hi，b右移，因为c左移后，依然小于，没必要
    // 当 a+b+c==target的时候，直接返回，因为已经最接近了
    // 时间复杂度：外层a是N，内层双指针是N，排序是NlogN，所以时间复杂度是 N^2+NlogN
    public int threeSumClosest2(int[] nums, int target){
        Arrays.sort(nums);
        int len = nums.length;
        int ans = 100000;
        for (int a=0; a<len-2; a++){
            if (a>0 && nums[a]==nums[a-1]) continue;
            int b = a+1;
            int c = len-1;
            while (b<c){
                int sum = nums[a]+nums[b]+nums[c];
                if (sum==target) return target;
                if (Math.abs(sum-target)<Math.abs(ans-target)) ans = sum;
                if (sum>target){
                    while (b<c && nums[c]==nums[c-1]) c--;
                }else {
                    while (b<c && nums[b]==nums[b+1]) b++;
                }

            }
        }
        return ans;
    }
}
