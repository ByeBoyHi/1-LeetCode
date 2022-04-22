package Middle.甲板上的战舰;

public class BattleshipsInBoard {
    int ans = 0;
    // 岛问题
    public int countBattleships(char[][] board) {
        if (board== null || board.length==0){
            return 0;
        }
        int m = board.length;
        int n = board[0].length;
        for (int i=0; i<m; i++){
            for (int j=0; j<n; j++){
                ans+=process(board, i, j);
            }
        }
        return ans;
    }
    public int process(char[][] board, int x, int y){
        // 无效格子
        if (x<0 || y<0 || x>board.length-1 || y>board[0].length-1 || board[x][y]=='.'  || board[x][y]=='Y'){
            return 0;
        }
        // 有效格子，找到一个岛屿，返回1
        board[x][y] = 'Y';
        process(board, x - 1, y);  // 左
        process(board, x + 1, y);  // 右
        process(board, x, y - 1);  // 上
        process(board, x, y + 1);  // 下
        return 1;
    }
}
