package Middle.甲板上的战舰;

public class TheUnionSet {

    public int countBattleships(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        int k = 0; // 用来记录里面有多少个X
        for (char[] chars : board) {
            for (char c : chars) {
                if (c == 'X') k++;
            }
        }
        // 最开始的节点个数和岛的个数
        Union union = new Union(n * m, k);
        // 开始合并
        for (int i = 0; i < m; i++) { // 行
            for (int j = 0; j < n; j++) {  // 列
                // 当前点是X的情况下，才有判断的必要
                // 当前点不是X的时候，如果隔壁是X，那么会出现当前非合理点和合理点的合并
                if (board[i][j] == 'X') {
                    // 因为舰队只能横着建和竖着建，所以我们只需要判断合并横着的和竖着的就行
                    // 横着
                    if (j + 1 < n && board[i][j + 1] == 'X') union.union(i * n + j, i * n + j + 1);
                    // 竖着
                    if (i + 1 < m && board[i + 1][j] == 'X') union.union(i * n + j, (i + 1) * n + j);
                }
            }
        }
        return union.getSetCount();
    }

    static class Union {
        private final int[] root;
        private final int[] size;
        private int setCount;

        public Union(int n, int sum) {
            root = new int[n];
            size = new int[n];
            setCount = sum;  // 最开始的根节点个数
            for (int i = 0; i < n; i++) {
                size[i] = 1;  // 第i个节点为根的节点个数为0
                root[i] = i;  // 所有节点都是自己的根节点
            }
        }

        private int find(int z) {
            // 只有根节点，自己的根才等于自己
            if (root[z]==z) {
                return z;
            }
            // 这里是找根节点的同时，扁平化了一下，可以便于后面更快的找根
            return root[z] = find(root[z]);
        }

        private boolean isSameSet(int x, int y) {
            return find(x) == find(y);
        }

        // 合并两个节点x和y
        public void union(int x, int y) {
            x = find(x);
            y = find(y);
            if (!isSameSet(x, y)) {
                // 根据节点个数大小关系，把小的接在大的下面
                if (size[x] > size[y]) {
                    root[y] = x;
                    size[x] += size[y];
                    size[y] = 0;
                } else {
                    root[x] = y;
                    size[y] += size[x];
                    size[x] = 0;
                }
                setCount--;
            }
        }

        public int getSetCount() {
            return setCount;
        }
    }
}
