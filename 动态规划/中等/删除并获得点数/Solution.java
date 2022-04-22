package 动态规划.中等.删除并获得点数;

public class Solution {
    public int deleteAndEarn(int[] nums) {
        if (nums.length==1) return nums[0];  // 只有一个数字的时候，返回这个数字
        if (nums.length==2){  // 有两个数字的时候
            if (Math.abs(nums[0]-nums[1])<=1){  // 这两个数字相邻的时候，返回较大值
                return Math.max(nums[0],nums[1]);
            }else {  // 不相邻，返回之和
                return nums[0]+nums[1];
            }
        }
        // 大于两个数字的时候
        int maxVal = nums[0];
        for (int num: nums){  // 找出最大值
            maxVal = Math.max(maxVal, num);
        }
        // 用最大值作为数组和的长度，对应数组下标代表的该值出现了总和
        int[] sum = new int[maxVal+1];
        for (int i=0; i<nums.length; i++){
            sum[nums[i]] += nums[i];
        }

        // 最后得到的数组和就是一个类似与打家劫舍的问题，取了其中一个的和，就要把两边的删掉。（不能取两边）
        return rob(sum);
    }

    public int rob(int[] arr){
        if (arr.length==1) return arr[0];
        if (arr.length==2) return Math.max(arr[0], arr[1]);

        int first = arr[0];  // 记录第一家的值
        int second = Math.max(arr[0], arr[1]);  // 记录第二家的时候的值
        int third = arr[2] + first;  // 记录第三家的值

        /**
         * 因为 f(i) = max( f(i-2), f(i-3) ) + arr[i]
         * 然后 f(i-3), f(i-2), f(i-1) 后移--> f(i-2), f(i-1), f(i)
         * 在这个后移的过程中，f(i-1) 会先进行 max 改变，所以我们先用temp记录下来
         * 其中的：
         *      f(i-3)-->first；
         *      f(i-2)-->second；
         *      f(i-1)-->third
         */
        for (int i=3; i<arr.length; i++){  // 计算f(i)
            int temp = third;
            third = Math.max(first, second) + arr[i];
            first = second;
            second = temp;
        }

        // 返回最后一次和倒数第二次的较大值，就是我们的最大金额。（不相邻要求）
        return Math.max(third, second);
    }
}
