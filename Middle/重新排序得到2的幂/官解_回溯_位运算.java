package Middle.重新排序得到2的幂;

import java.util.Arrays;

public class 官解_回溯_位运算 {
    boolean[] vis;

    // 搜索回溯+位运算

    /**
     * 思路：将数字转为字符数组，然后对里面的值进行排序，让相同的挨在一起，可以避免重复回溯。
     * 而且我们需要的是对数字进行一定的排列，然后判断新的数字是否是2的次幂，对于这个数字的起始顺序无关。
     * @param n  需要全排列进行2的次幂判断的数字。
     * @return   返回判断结果
     */
    public boolean reorderedPowerOf2(int n) {
        char[] nums = Integer.toString(n).toCharArray();
        Arrays.sort(nums);
        vis = new boolean[nums.length]; // 对访问标记数组进行初始化
        return backtrack(nums, 0, 0);
    }

    /**
     * 用回溯探索字符数组nums的所有全排列组合，判断他们是否是2的次幂
     * @param nums  需要探索的字符数组
     * @param idx   当前探索到第几位了
     * @param num   目前的数字拼接后的结果
     * @return      返回是否是2的次幂
     */
    private boolean backtrack(char[] nums, int idx, int num) {
        if (idx==nums.length){
            return isPowerOfTwo(num);
        }
        for (int i=0; i<nums.length; i++){
            /*
                1. 第一个判断语句：传进来的数字如果是0并且当前数字也是0，这就是前导零，本次循环就没有意义，跳过
                2. 第二个判断语句：当前位置已经访问过了，重复访问没有意义，跳过
                3. 第三个判断语句：剪枝。避免重复的回溯，跳过。
                   这里取!vis[i-1]如果等于true，说明当前循环就是上一个循环结束过来的，而不是通过递归进来的。
             */
            if ((num==0 && nums[i]=='0')|| vis[i] || (i>0 && !vis[i-1] && nums[i]==nums[i-1])){
                continue;
            }
            // 标记访问
            vis[i] = true;
            if (backtrack(nums, idx+1, num*10+nums[i]-'0')){
                return true;
            }
            // 取消访问，给下次递归用
            vis[i] = false;
        }
        return false;
    }

    // 1000000 & 0111111 = 0000000
    private boolean isPowerOfTwo(int n) {
        return (n & (n - 1)) == 0;
    }
}
