package Middle.保持城市天际线;

public class MaxIncreaseToKeepCitySkyline {

    // 1ms
    public int maxIncreaseKeepingSkyline(int[][] grid) {

        int[] maxRow = new int[grid.length];
        int[] maxCol = new int[grid[0].length];

        int row = grid.length;
        int col = grid[0].length;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                maxRow[i] = Math.max(maxRow[i], grid[i][j]);
                maxCol[j] = Math.max(maxCol[j], grid[i][j]);
            }
        }

        int ans = 0;

        for (int i=0; i<row; i++){  // 行
            for (int j=0; j<col; j++){  // 列
                ans+=Math.min(maxRow[i],maxCol[j])-grid[i][j];
            }
        }

        return ans;
    }

    // 0ms
    // 贪心
    // 找每行每列的最大高度，当前元素的高度，就是这两个高度的较小值
    public int maxIncreaseKeepingSkyline2(int[][] grid) {

        int[] maxRow = new int[grid.length];
        int[] maxCol = new int[grid[0].length];

        int row = grid.length;
        int col = grid[0].length;

        for (int i=0; i<row; i++){  // 行
            int max = -1;
            for (int j=0; j<col; j++){  // 列
                if (grid[i][j]>max){
                    max = grid[i][j];
                }
            }
            maxRow[i] = max;
        }

        for (int i=0; i<col; i++){  // 列
            int max = -1;
            for (int[] ints : grid) {  // 行
                if (ints[i] > max) {
                    max = ints[i];
                }
            }
            maxCol[i] = max;
        }

        int ans = 0;

        for (int i=0; i<row; i++){  // 行
            for (int j=0; j<col; j++){  // 列
                ans+=Math.min(maxRow[i],maxCol[j])-grid[i][j];
            }
        }

        return ans;
    }
}
