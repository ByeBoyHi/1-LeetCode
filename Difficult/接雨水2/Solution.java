package Difficult.接雨水2;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().trapRainWater(new int[][]{
                {12, 13, 1, 12},
                {13, 4, 13, 12},
                {13, 8, 10, 12},
                {12, 13, 12, 12},
                {13, 13, 13, 13}
        }));
    }

    public int trapRainWater(int[][] heightMap) {
        if (heightMap.length <= 2 || heightMap[0].length <= 2) {  // 只有两列或者两行是无法形成水洼的
            return 0;
        }
        int m = heightMap.length;
        int n = heightMap[0].length;
        boolean[][] visit = new boolean[m][n];
        // 小根堆，第一个是最小的
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    pq.offer(new int[]{i * n + j, heightMap[i][j]});
                    visit[i][j] = true;
                }
            }
        }
        int res = 0;
        int[] dirs = {-1, 0, 1, 0, -1};  // 控制上下左右：只要当前位置比上下左右都低
        while (!pq.isEmpty()) {
            int[] curr = pq.poll(); // 拿出当前最小的围墙
            for (int k = 0; k < 4; ++k) {
                int nx = curr[0] / n + dirs[k];
                int ny = curr[0] % n + dirs[k + 1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && !visit[nx][ny]) {
                    if (curr[1] > heightMap[nx][ny]) { // 如果这个最小的围墙比里面的高，那么就可以形成水洼
                        res += curr[1] - heightMap[nx][ny];
                    }
                    pq.offer(new int[]{nx * n + ny, Math.max(heightMap[nx][ny], curr[1])});
                    visit[nx][ny] = true;
                }
            }
        }
        return res;
    }
}

// 大佬题解：12ms
class Solution2
{
    private static class Cell implements Comparable<Cell>
    {
        private int row;
        private int col;
        private int value;

        public Cell(int r, int c, int v)
        {
            this.row = r;
            this.col = c;
            this.value = v;
        }

        @Override
        public int compareTo(Cell other)
        {
            return value - other.value;
        }
    }

    private int water;
    private boolean[][] visited1;

    public int trapRainWater(int[][] heightMap)
    {
        if (heightMap.length == 0) return 0;
        PriorityQueue<Cell> walls = new PriorityQueue<Cell>();
        water = 0;
        visited1 = new boolean[heightMap.length][heightMap[0].length];
        int rows = heightMap.length;
        int cols = heightMap[0].length;


        for (int c = 0; c < cols; c++)
        {
            walls.add(new Cell(0, c, heightMap[0][c]));
            walls.add(new Cell(rows - 1, c, heightMap[rows - 1][c]));
            visited1[0][c] = true;
            visited1[rows - 1][c] = true;
        }
        for (int r = 1; r < rows - 1; r++)
        {
            walls.add(new Cell(r, 0, heightMap[r][0]));
            walls.add(new Cell(r, cols - 1, heightMap[r][cols - 1]));
            visited1[r][0] = true;
            visited1[r][cols - 1] = true;
        }
        while(walls.size() > 0)
        {
            Cell min = walls.poll();
            visit(heightMap, min, walls);
        }
        return water;
    }
    private void visit(int[][] height, Cell start, PriorityQueue<Cell> walls)
    {
        fill(height, start.row + 1, start.col, walls, start.value);
        fill(height, start.row - 1, start.col, walls, start.value);
        fill(height, start.row, start.col + 1, walls, start.value);
        fill(height, start.row, start.col - 1, walls, start.value);
    }
    private void fill(int[][] height, int row, int col,
                      PriorityQueue<Cell> walls, int min)
    {
        if (row < 0 || col < 0) return;
        else if (row >= height.length || col >= height[0].length) return;
        else if (visited1[row][col]) return;
        else if (height[row][col] >= min) {
            walls.add(new Cell(row, col, height[row][col]));
            visited1[row][col] = true;
        }
        else {
            water += min - height[row][col];
            visited1[row][col] = true;
            fill(height, row + 1, col, walls, min);
            fill(height, row - 1, col, walls, min);
            fill(height, row, col + 1, walls, min);
            fill(height, row, col - 1, walls, min);
        }
    }
}
