package Middle.边界着色;

import java.util.HashMap;
import java.util.Map;

public class ColoringABorder {

    public static void main(String[] args) {
        ColoringABorder cab = new ColoringABorder();
        print(cab.colorBorder(new int[][]{
                        {1, 1, 1}, {1, 1, 1}, {1, 1, 1}
                }
                , 1, 1, 3));
    }


    int m, n, c;
    int[][] grid, ans;
    int[][] dirs = new int[][]{{1,0}, {-1,0}, {0,1}, {0,-1}};
    public int[][] colorBorder(int[][] _grid, int row, int col, int color) {
        grid = _grid; c = color;
        m = grid.length; n = grid[0].length;
        ans = new int[m][n];
        dfs(row, col);  // 这里把row,col这个范围的点进行染色处理
        for (int i = 0; i < m; i++) { // 下面对其他不在范围内的颜色进行染色处理
            for (int j = 0; j < n; j++) {
                if (ans[i][j] == 0) ans[i][j] = grid[i][j];
            }
        }
        return ans;
    }
    void dfs(int x, int y) {
        int cnt = 0;
        for (int[] di : dirs) {
            int nx = x + di[0], ny = y + di[1];
            if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue;  // 越界
            if (grid[x][y] != grid[nx][ny]) continue; // 不同色
            else cnt++;  // 同色
            if (ans[nx][ny] == -1) continue;  // 判断是否来过，-1表示来过，空间复用
            ans[nx][ny] = -1;  // 表示看过
            dfs(nx, ny);
        }
        // 这里会处理上一回的-1
        ans[x][y] = cnt == 4 ? grid[x][y] : c;
    }

    static class Solution {
        int[][] grid;
        int m, n, c, color;
        boolean[][] visited;
        public int[][] colorBorder(int[][] grid, int row, int col, int color) {
            m = grid.length;
            n = grid[0].length;
            this.grid = grid;
            this.color = color;
            c = grid[row][col];
            visited = new boolean[m][n];
            dfs(row, col);
            return grid;
        }

        /*
            四周都是一个颜色的时候，都会返回1，然后相加等于4，说明不是边界，不染色
            如果有一个方向不是我们指定的颜色，那么返回必然小于4
            如果当前颜色不是我们指定的颜色，那么我们不会dfs下去，会直接返回0
         */
        int dfs(int row, int col) {
            if (row < 0 || row >= m || col < 0 || col >= n) return 0;
            if (visited[row][col]) return 1;  // 只有同色的才会看
            if (grid[row][col] != c) return 0;
            visited[row][col] = true;
            if (dfs(row - 1, col) + dfs(row, col + 1) +
                    dfs(row + 1, col) + dfs(row, col - 1) < 4)
                grid[row][col] = color;
            return 1;
        }
    }

    public static void print(int[][] grid) {
        int col = grid[0].length;
        for (int[] ints : grid) {
            for (int j = 0; j < col; j++) {
                System.out.print(ints[j] + "\t");
            }
            System.out.println();
        }
    }
}
