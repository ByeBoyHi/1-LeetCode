package 动态规划.简单.斐波那契数;

public class Solution {
    public int fib(int n) {
        if(n==0) return 0;
        if(n==1 || n==2) return 1;
        int one = 1; // 0
        int two = 1;  // 1
        for(int i=2; i<n; i++){
            int temp = one;  // 这里用来保存更新之前的值
            one = two;
            two = temp+two;  // 需要加one更新之前的值，而不是之后的
        }
        return two;
    }
}
