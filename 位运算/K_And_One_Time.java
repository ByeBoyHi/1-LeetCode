package 位运算;

/**
 * 题目：一个一维数组，里面只有一个数字出现了一次，其他数字都出现了k次，找出出现了一次的数字。
 *
 * 不进位相加：二进制异或运算-->模运算
 */
public class K_And_One_Time {
    public static void main(String[] args) {
        int[] nums = new int[]{3,3,3,5,6,5,5,1,1,8,1,8,8};
        int k = 3;
        char[][] radix = new char[nums.length][];  // 里面有n个一维字符数组，用来存放每一个数字的k进制各个字符
        int maxLen = 0;  // 记录最长的一个数字的进制位数，用于后面进行计算补零
        // 转为k进制，然后存入字符二维数组radix中
        for (int i=0; i<nums.length; i++) {
             radix[i] = (Integer.toString(nums[i], k)).toCharArray();
             if (radix[i].length>maxLen) maxLen = radix[i].length;  // 更新maxLen
        }
        // 进行所有位数的和运算



    }
}
