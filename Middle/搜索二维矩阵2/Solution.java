package Middle.搜索二维矩阵2;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().searchMatrix(new int[][]{
                {1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}
        }, 20));
    }

    private boolean ans = false;
    private int[][] visited = new int[300][300];


    // dfs
    public boolean searchMatrix(int[][] matrix, int target) {
        dfs(matrix, target, 0, 0, visited);
        return ans;
    }

    private void dfs(int[][] matrix, int target, int i, int j, int[][] visited) {
        if (i < 0 || j < 0) return;  // 往回走出界了
        if (visited[i][j] == 1) return;  // 访问过了
        visited[i][j] = 1;  // 标记访问
        if (i != 0) {  // i!=0的时候，判断j受否走出去了
            if (i >= matrix.length || j >= matrix[i - 1].length) {  // 走出了矩阵范围
                return;
            }
        }
        if (matrix[i][j] == target) {  // 找到了
            ans = true;
            return;
        }
        if (!ans) {  // 还没找到
            if (matrix[i][j] < target) {
                if (i + 1 < matrix.length) {
                    dfs(matrix, target, i + 1, j, visited);
                    visited[i + 1][j] = 1;
                }
                if (j + 1 < matrix[i].length) {
                    dfs(matrix, target, i, j + 1, visited);
                    visited[i][j + 1] = 1;
                }
            } else {
                if (i - 1 >= 0) {
                    dfs(matrix, target, i - 1, j, visited);
                    visited[i - 1][j] = 1;
                }
                if (j - 1 >= 0) {
                    dfs(matrix, target, i, j - 1, visited);
                    visited[i][j - 1] = 1;
                }
            }
        }
    }

    // 暴力破解：比我dfs快？？
    public boolean searchMatrix1(int[][] matrix, int target) {
        for (int i=0; i<matrix.length; i++){
            for (int j=0; j<matrix[i].length; j++){
                if (matrix[i][j]==target){
                    return true;
                }
            }
        }
        return false;
    }

    // 对每一行进行二分查找
    public boolean searchMatrix2(int[][] matrix, int target){
        int i=0;
        while (i<matrix.length && !search(matrix[i], target)){
            i++;
        }
        return i != matrix.length;
    }
    private boolean search(int[] arr, int target){
        int low = 0;
        int high = arr.length-1;
        while (low<=high){
            int mid = low + ((high - low) >> 1);
            if (arr[mid]==target){
                return true;
            }else if (arr[mid]>target){
                high = mid-1;
            }else {
                low = mid+1;
            }
        }
        return false;
    }

    /**
     * Z字形判断：从右上角往左下角走。
     * 令 x==0，y==n-1。
     * 因为矩阵是 从左到右 从上到下 递增的。
     * 如果 matrix[x][y]>target，说明 y 列都是大于 target 的，因为 y 最开始在最上面，这时候我们 y-- 左移找较小值就好。
     * 如果 matrix[x][y]<target，说明 x 行都是小于 target 的，因为 x 最开始在最右边，这时候我们 x++ 下移找较大值就好。
     * 右上角的值：是该行的最大值，该列的最小值。
     * 左下角的值：是该行的最小值，该列的最大值。
     */
    public boolean searchMatrix3(int[][] matrix, int target){
        int m = matrix.length;    // 行
        int n = matrix[0].length; // 列
        int x = 0;   // 最上面
        int y = n-1; // 最右边
        while (x<m && y>=0){  // 只要有一个越界，就会退出while
            if (matrix[x][y]==target){
                return true;
            }
            if (matrix[x][y]>target){
                y--;
            }else {
                x++;
            }
        }
        return false;
    }
}