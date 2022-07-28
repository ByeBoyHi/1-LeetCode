package Middle.猜数字大小2;

public class Solution {
    // 题意：至少需要多少现金才能保证一定猜对
    // 二分失败，比如 1，2，3，4
    // 对于二分，在确保必赢的情况下，最低消费 5
    // 对于动态规划，第一次取 1，第二次取 3，必赢，消费 4
    // 二分是一半一半找，动态规划是一开始就会去试探所有的值
    /**
     * f[i,j]表示i~j范围的最低消费
     * f[i,j] = x + max(f[i,x-1],f[x+1,j]) 猜了x之后的值加上两边区间的最大值，x 为 i..j
     * f[1,1] = 0 只有一个数字，必然猜中
     * f[1,3] = min((1 + f[2,3]), 2+max(f[1,1],f[3,3]) , (3+f[1,2]))
     * 对于f[i,j]，如果i==j，那么等于0，必猜中；如果i>j，那么等于0，必无效
     *
     * 因为这个DP问题是先计算小规模问题再计算大规模问题，所以要注意遍历方向
     */
    public int getMoneyAmount(int n) {
        int[][] f = new int[n+1][n+1];
        for (int i=n-1; i>=1; i--){
            for (int j=i+1; j<=n; j++){  // 这个i~j，相当于 (n-1)~n，(n-1)~n ... 1~n，因为 1~n的计算依赖于前面的 (i-1)~n
                int min = Integer.MAX_VALUE;
                for (int k=i; k<=j; k++){  // 这里在计算的时候始终保持了i<j，并且里面的k运算的时候，没有赋值的地方默认是0
                    int cur = k + Math.max(f[i][k-1], f[k+1][j]);
                    min = Math.min(min, cur);
                }
                f[i][j] = min;
            }
        }
        return f[1][n];
    }
}
