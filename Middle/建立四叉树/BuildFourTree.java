package Middle.建立四叉树;

public class BuildFourTree {
    public Node construct(int[][] grid) {
        return process(grid, 0, 0, grid[0].length, grid.length);
    }

    // 递归处理每个子树
    // 竖轴x横轴y
    // 因为x控制行，y控制列
    public Node process(int[][] grid, int x1, int y1, int x2, int y2) {
        boolean same = true;
        for (int row = x1; row < x2; row++) {
            for (int col = y1; col < y2; col++) {
                if (grid[row][col] != grid[x1][y1]) {
                    same = false;
                    break;
                }
            }
            if (!same) {
                break;
            }
        }

        if (same) {
            return new Node(grid[x1][y1] == 1, true); // 叶子
        }

        return new Node(
                true,
                false,
                process(grid, x1, y1, (x1 + x2) / 2, (y1 + y2) / 2),  // 左上
                process(grid, x1, (y1 + y2) / 2, (x1 + x2) / 2, y2),  // 右上
                process(grid, (x1 + x2) / 2, y1, x2, (y1 + y2) / 2),  // 左下
                process(grid, (x1 + x2) / 2, (y1 + y2) / 2, x2, y2)   // 右下
        );
    }

    // 二维前缀和
    /*
        用以缓存某一部分是否均为0或1。
        当均为0的时候，显然和为0；让均为1的时候，和为该区域面积，从而以O(1)的时间复杂度得到该区域的情况。
     */
    public Node construct2(int[][] grid) {
        int n = grid.length;
        int[][] pre = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                // i j 存储的是 grid矩阵(0,0)->(i-1,j-1)这个区域的面积和
                pre[i][j] = pre[i - 1][j] + pre[i][j - 1] - pre[i - 1][j - 1] + grid[i - 1][j - 1];
            }
        }
        return dfs(grid, pre, 0, 0, n, n);
    }

    public Node dfs(int[][] grid, int[][] pre, int x1, int y1, int x2, int y2) {
        int total = getSum(pre, x1, y1, x2, y2);
        if (total==0){  // 全零
            return new Node(false, true);
        }else if (total==(y2-y1)*(x2-x1)){  // 全1
            return new Node(true, true);
        }
        // 其他情况
        return new Node(
                true,
                false,
                dfs(grid, pre, x1, y1, (x1+x2)/2, (y1+y2)/2),
                dfs(grid, pre, x1, (y1+y2)/2, (x1+x2)/2, y2),
                dfs(grid, pre, (x1+x2)/2, y1, x2, (y1+y2)/2),
                dfs(grid, pre, (x1+x2)/2, (y1+y2)/2, x2,y2)
        );
    }

    // 求出从(x1,y1)->(x2,y2)的面积和
    public int getSum(int[][] pre, int x1, int y1, int x2, int y2) {
        return pre[x2][y2] - pre[x2][y1] - pre[x1][y2] + pre[x1][y1];
    }

}


class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;


    public Node() {
        this.val = false;
        this.isLeaf = false;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }

    public Node(boolean val, boolean isLeaf) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }

    public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
    }
}