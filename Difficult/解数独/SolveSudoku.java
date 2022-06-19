package Difficult.解数独;

import java.util.BitSet;

public class SolveSudoku {
    /*
        因为我们是在一个固定大小的二维数组上填写数字，所以由以下方式记录：
        使用两个一维数字存储每个位置可以放的数字：如 10010111 从左往右数，表示可以放 1 2 3 5 8
        这两个一维数组定义为： row[] col[]
        用一个二维数组定义每个九宫格可以放的数字cell[][]，记录方式如上。
     */
    private final int[] row = new int[9];
    private final int[] col = new int[9];
    private final int[][] cell = new int[9][9];

    /*
        算法：回溯+递归
        在填写数字的时候：
        1. 找出当前能填数字最少的那个位置
        2. 对他进行每个数字的尝试
        3. 在尝试之前，进行row col cell记录存储
        4. 尝试完了之后，恢复现场
        5. 当为1的时候，表示这个位置可以写，否则不行
     */

    public void solveSudoku(char[][] board) {
        int cnt = 0;
        // 处理每个位置的可填数量
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                // 表示需要填数字的数量+1
                if (board[i][j]=='.'){
                    cnt++;
                }else {
                    int n = board[i][j]-'1';  // 表示这个位置的数字已经有了
                    fillArr(i, j, n);
                }
            }
        }
        backtrace(board, cnt);
    }

    // 递归主函数
    public boolean backtrace(char[][] board, int cnt){
        if (cnt==0){
            return true; // 说明所有可填数字都填完了
        }
        // 找到可填数字最少的位置
        int[] pos = getLeastPoint(board);
        int x = pos[0], y = pos[1];
        // 取到这个位置可以填的所有信息
        int okMask = getOkMask(x, y);
        // 判断所有可填数字
        for (char i = '1'; i <= '9'; i++) {
            int index = i-'1';
            if (noZero(okMask, index)){  // 这个位置非零，说明可以
                fillArr(x, y, index); // 0->1
                board[x][y] = i;
                if (backtrace(board, cnt-1)) return true;  // 假定唯一解
                board[x][y] = '.';
                fillArr(x, y, index); // 1->0
            }
        }
        return false;
    }

    // 处理 col row cell 的函数
    // 第n个位置取1或者0，即填或不填
    // 异或：最开始都是0，当第一次进行异或的时候，变为1，表示填写，再次异或，即恢复现场
    public void fillArr(int x, int y, int n) {
        row[x] ^= (1 << n);
        col[y] ^= (1 << n);
        cell[x / 3][y / 3] ^= (1<<n);
    }

    // 取到低index位置的值是否非零
    public boolean noZero(int mask, int index) {
        return (mask & (1 << index)) != 0;
    }

    // 返回(x,y)位置所有可以放数字的最终值
    /*
        对于(1,2)，所属格子是 (1/3,2/3)=(0,0)
     */
    public int getOkMask(int x, int y) {
        return ~(row[x] | col[y] | cell[x / 3][y / 3]);
    }

    // 返回一共9个数字一共有多少种选择可以填
    public int getChoice(int mask) {
        int res = 0;
        for (int i = 0; i < 9; i++) {
            // 这一位为1，说明整体非零
            if ((mask & (1 << i)) != 0) {
                res++;
            }
        }
        return res;
    }

    // 返回可选填数字最少的位置
    public int[] getLeastPoint(char[][] board) {
        int[] ans = new int[2];
        int min = 10;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                // 等于 . 才有算下去的必要
                if(board[i][j]=='.') {
                    int num = getChoice(getOkMask(i, j));
                    if (num < min) {
                        min = num;
                        ans[0] = i;
                        ans[1] = j;
                    }
                }
            }
        }
        return ans;
    }
}

    // n 0..8
//    private void fillNumber(int x, int y, int n, boolean fill){
//        // 因为回溯先选择后撤销，所以fill先true后false, false时对应位置一定是1，所以异或可行
//        // rows[x] = fill ? rows[x] | (1<<n) : rows[x] ^ (1<<n);
//        // cols[y] = fill ? cols[y] | (1<<n) : cols[y] ^ (1<<n);
//        // cells[x/3][y/3] = fill ? cells[x/3][y/3] | (1<<n) : cells[x/3][y/3] ^ (1<<n);
//
//        // ture set 1, false set 0
//        rows[x] = fill ? rows[x] | (1<<n) : rows[x] & ~(1<<n);
//        cols[y] = fill ? cols[y] | (1<<n) : cols[y] & ~(1<<n);
//        cells[x/3][y/3] = fill ? cells[x/3][y/3] | (1<<n) : cells[x/3][y/3] & ~(1<<n);
//    }

class Solution {
    private BitSet[] rows = new BitSet[9];
    private BitSet[] cols = new BitSet[9];
    private BitSet[][] cells = new BitSet[3][3];

    public void solveSudoku(char[][] board) {
        // 初始化 BitSet
        for(int i=0; i<9; i++){
            rows[i] = new BitSet(9);
            cols[i] = new BitSet(9);
            cells[i/3][i%3] = new BitSet(9);
        }

        int count = 0;
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[i].length; j++){
                char c = board[i][j];
                if(c == '.'){
                    count++;
                }else{
                    int num = c - '1';
                    rows[i].set(num);
                    cols[j].set(num);
                    cells[i/3][j/3].set(num);
                }
            }
        }

        backtrace(board, count);
    }

    private boolean backtrace(char[][] board, int count){
        if(count == 0){
            return true;
        }
        int[] pos = getNext(board);
        int x = pos[0], y = pos[1];
        BitSet okMask = getPossibleStatus(x, y);

        for(char c='1'; c<='9'; c++){
            int i = c - '1';
            if(isValid(okMask, i)){
                fillNum(x, y, i, true);
                board[x][y] = c;
                if(backtrace(board, count-1)){
                    return true;
                }
                board[x][y] = '.';
                fillNum(x, y, i, false);
            }
        }

        return false;
    }

    // num 0..8     flag == true when set num
    private void fillNum(int x, int y, int num, boolean flag){
        rows[x].set(num, flag);
        cols[y].set(num, flag);
        cells[x/3][y/3].set(num, flag);
    }

    // num 0..8
    private boolean isValid(BitSet okMask, int num){
        return okMask.get(num);
    }

    private BitSet getPossibleStatus(int x, int y){
        BitSet bitset = (BitSet)rows[x].clone();
        bitset.or(cols[y]);
        bitset.or(cells[x/3][y/3]);
        bitset.flip(0, 9);
        return bitset;
    }

    // 返回二进制中 1 的个数
    private int getOneCount(BitSet mask){
        return mask.cardinality();
    }

    // 返回限制最多(候选状态最少)的位置
    private int[] getNext(char[][] board){
        int min = 10;
        int[] res = new int[2];
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[i].length; j++){
                if(board[i][j] == '.'){
                    BitSet mask = getPossibleStatus(i, j);
                    int count = getOneCount(mask);
                    if(count < min){
                        min = count;
                        res[0] = i;
                        res[1] = j;
                    }
                }
            }
        }
        return res;
    }

}