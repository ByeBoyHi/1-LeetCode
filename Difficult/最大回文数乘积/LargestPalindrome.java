package Difficult.最大回文数乘积;

public class LargestPalindrome {

    public static void main(String[] args) {
        int n = 8;
        System.out.println(largestPalindrome(n));
    }

    public static int largestPalindrome(int n) {
        if (n == 1) {
            return 9;
        }
        int upper = (int) Math.pow(10, n) - 1;
        int ans = 0;
        // ans等于0说明没有找到最大回文数
        // left--实现反着找这个数字，才能找到最大的
        for (int left = upper; ans == 0; --left) { // 枚举回文数的左半部分
            long p = left;  // 这是一个long，可以存储二倍长度的数字
            for (int x = left; x > 0; x /= 10) {
                p = p * 10 + x % 10; // 翻转左半部分到其自身末尾，构造回文数 p
            }
            // x是n位数的，所以最多只有(10^n-1)这么大
            for (long x = upper; x * x >= p; --x) {  // 如果x^2都不能到达p，那么后面怎么循环都没必要了
                if (p % x == 0) { // x 是 p 的因子
                    ans = (int) (p % 1337);
                    break;
                }
            }
        }
        return ans;
    }

    int[] ans = {9,987,123,597,677,1218,877,475};
    public int largestPalindrome2(int n) {
        return ans[n-1];
    }
}
