package Middle.只出现一次的数字3;

import java.util.Arrays;

public class Solution {
    /**
     * 在一个数组里面，只有两个数字出现了一次，其他数字都出现了两次，找出这两个数字并返回。
     * @param nums   需要寻找探索的数组。
     * @return       返回只出现一次的两个数字。
     *
     * 异或运算：自己^自己 = 0;  0^任何数字 = 数字本身
     * 思路：假设里面有两个数字 a 和 b 是出现一次的，其他全部出现了两次。
     * 那么我们对所有数字进行异或和运算，最终会得到 a^b 的结果。
     * 这两个数字不相等，在二进制位上必然有一个是互不相同的，也就是一个 0，一个 1
     * 我们对这个结果找出右侧第一个1，然后对原数组进行分类：把这一个位置上是1的分一起，是0的分另一边
     * 就得到了 a 和 b 分别在的两组。
     * 随便选一组和之前的全部异或和再进行一次异或，得到的就是另一个数字，假设这个数字是 a。
     * 我们再用 a ^ (a^b) ，也就是异或之前的结果，也就得到了数字 b。
     */
    public int[] singleNumber(int[] nums) {
        int a = nums[0];
        for(int i=1; i<nums.length; i++){
            a^=nums[i];
        }
        int rightOne = (1<<firstRightOne(a));
        int b = a;
        for (int num : nums) {
            if ((num & rightOne) != 0) {
                a ^= num;
            }
        }
        b = b^a;
        return new int[]{a,b};
    }

    // 找一个数字的右侧第一个1
    private int firstRightOne(int n){
        int ans = 0;
        while (((n>>ans)&1)!=1) ans++;
        return ans;
    }

    // 暴力破解：
    // 对原数组进行排序，让相同的数字挨在一起，只要出现了一次，就加进结果里面
    public int[] singleNumber2(int[] nums) {
        Arrays.sort(nums);
        int[] ans = new int[2];
        int cur = 0;
        int cnt = 1;
        for (int i=1; i<nums.length; i++){
            if (nums[i]==nums[i-1]){
                cnt++;
            }else {
                if (cnt==1){
                    ans[cur] = nums[i-1];
                    cur++;
                    if (cur==2){
                        break;
                    }
                }
                // 如果最后一个出现了不等现象
                if (i==nums.length-1){
                    ans[cur] = nums[i];
                }
                cnt = 1;
            }
        }
        return ans;
    }
}
