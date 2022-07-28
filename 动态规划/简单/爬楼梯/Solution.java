package 动态规划.简单.爬楼梯;

public class Solution {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println(new Solution().climbStairs(44));
        System.out.println(new Solution().climbStairs2(44));
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }

    // 动态规划
    public int climbStairs(int n) {
        // n==0的时候，就是n=2然后下一步走了两级台阶的可能。
        if(n==1 || n==0) return 1;
        int[] res = new int[n];
        res[0] = res[1] = 1;
        return climbHelp(n-1, res) + climbHelp(n-2, res);   // 所有可能性的个数就是叶子节点的个数。
    }
    public int climbHelp(int n, int[] res){
        if (res[n]==0){
            res[n] = climbHelp(n-1, res) + climbHelp(n-2, res);
        }
        return res[n];
    }

    // 递归：当数字较大的时候，会超时
    public int climbStairs2(int n){
        // n==0的时候，就是n=2然后下一步走了两级台阶的可能。
        if(n==1 || n==0) return 1;
        return climbStairs(n-1) + climbStairs(n-2);   // 所有可能性的个数就是叶子节点的个数。
    }
}
