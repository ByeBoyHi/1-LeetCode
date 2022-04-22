package Easy.范围求和2;

public class Solution {
    public int maxCount(int m, int n, int[][] ops) {
        if (ops.length==0){  // 没有操作的时候，全是0，即最大值个数
            return m*n;
        }
        // 只要有操作，就会有变化
        // 记录最小行和最小列，返回他们的乘积
        int minRow = Integer.MAX_VALUE;
        int minCol = Integer.MAX_VALUE;
        for (int[] op : ops) {
            minRow = Math.min(minRow, op[0]);
            minCol = Math.min(minCol, op[1]);
        }
        return minRow*minCol;
    }
}
