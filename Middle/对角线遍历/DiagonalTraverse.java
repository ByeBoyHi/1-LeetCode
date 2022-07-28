package Middle.对角线遍历;

public class DiagonalTraverse {
    /**
     * 个人分析： <br>
     * [0,0]-> <br>
     * [0,1]->[1,0]-> <br>
     * [2,0]->[1,1]->[0,2]-> <br>
     * 往上走，row-，col+ <br>
     * 往下走，row+，col- <br>
     * 当其中一个走到边界，那么另一个数字就要+1 <br>
     * 并且根据方向，判断row和col的变化 <br>
     * <br>
     * 注意：<br>
     * 1. 记录边界的时候，是 n-1和m-1<br>
     * 2. while循环的时候，只要 row 和 col 都没有到边界，那么这个循环就还没有走完，所以是 ||<br>
     * 3. 我们可以用一个 boolean 进行方向控制<br>
     * 4. 向上和向下的时候，尤其要考虑在对角线上的时候，应该先判断哪个边界条件更合适，因为此时两个边界都符合要求<br>
     *
     */
    public int[] findDiagonalOrder(int[][] mat) {
        int[] arr = new int[mat.length * mat[0].length];
        if (arr.length == 0) {
            return arr;
        }
        boolean flag = true;  // 往上走为true，往下为false
        int rowBound = mat.length - 1;
        int colBound = mat[0].length - 1;
        int row = 0;
        int col = 0;
        arr[0] = mat[row][col];
        int idx = 1;
        // 只要有一个条件不满足，就在循环里面走
        while (row != rowBound || col != colBound) {
            if (flag) {  // 方向向上
                if (col == colBound || row == 0) {
                    flag = false;
                    if (col == colBound) {
                        row++;
                    } else {
                        col++;
                    }
                } else {
                    row--;
                    col++;
                }
            } else { // 方向向下
                if (col == 0 || row == rowBound) {
                    flag = true;
                    if (row == rowBound) {
                        col++;
                    } else {
                        row++;
                    }
                } else {
                    row++;
                    col--;
                }
            }
            arr[idx++] = mat[row][col];
        }
        return arr;
    }
}
