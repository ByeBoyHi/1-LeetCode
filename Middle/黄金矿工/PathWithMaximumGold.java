package Middle.黄金矿工;

public class PathWithMaximumGold {
    // 控制前后左右方向
    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    int[][] grid;
    int m, n;  // 行数和列数
    int ans = 0;

    public int getMaximumGold(int[][] grid) {
        this.grid = grid;
        this.m = grid.length;
        this.n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 0) {  // 这里有黄金
                    dfs(i, j, 0);
                }
            }
        }
        return ans;
    }

    // 典型的DFS
    public void dfs(int x, int y, int gold) {
        gold += grid[x][y];
        ans = Math.max(ans, gold);

        // 记录并清楚，遍历回溯
        int rec = grid[x][y];
        grid[x][y] = 0;

        for (int d = 0; d < 4; d++) {
            int nx = x + dirs[d][0];
            int ny = y + dirs[d][1];
            // 不越界且这里有黄金的情况下
            if (nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] > 0) {
                dfs(nx, ny, gold);
            }
        }
        grid[x][y] = rec; // 恢复现场
    }
}
