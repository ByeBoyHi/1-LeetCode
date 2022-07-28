package 动态规划.简单.第N个斐波那契数;

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().tribonacci(25));
    }
    public int tribonacci(int n) {
        if (n==0) return 0;
        if (n==2 || n==1) return 1;
        int[] T = new int[n];
        T[0] = 0;
        T[1] = 1;
        T[2] = 1;
        for (int i=3; i<n; i++){
            T[i] = T[i-1]+T[i-2]+T[i-3];
        }
        return T[n-1];
    }
}
