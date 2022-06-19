package Middle.有效的井字游戏;

public class ValidTicTacToeState {


    public static void main(String[] args) {
        System.out.println(validTicTacToe(new String[]{"XXX", "   ", "OOO"}));
    }

    /*
        1. X的个数应该大于等于O的个数
        2. 个数差的不超过1
        3. 如果有三个连成一线，那么另一个一定不会连成一线
        游戏规则：判断是不是有效的井字格
     */

    public static boolean validTicTacToe(String[] board) {
        int Xnums = 0;
        int Onums = 0;
        boolean X = false;
        boolean O = false;
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                if (board[j].charAt(i) == 'X') {
                    Xnums++;
                    if (!X) {
                        if (j == 1 && i == 1) {
                            if ((board[1].charAt(0) == 'X' && board[1].charAt(2) == 'X') // 横
                                    || (board[0].charAt(1) == 'X' && board[2].charAt(1) == 'X')  // 竖
                                    || (board[0].charAt(2) == 'X' && board[2].charAt(0) == 'X')  // 右斜
                                    || (board[0].charAt(0) == 'X' && board[2].charAt(2) == 'X')) // 左斜
                            {
                                X = true;
                            }
                        } else if (j == 0 && i == 1) {  // 第一排
                            if (board[0].charAt(0) == 'X' && board[0].charAt(2) == 'X') {
                                X = true;
                            }
                        } else if (j == 2 && i == 1) {  // 第三排
                            if (board[2].charAt(0) == 'X' && board[2].charAt(2) == 'X') {
                                X = true;
                            }
                        } else if (j == 1 && i == 0) { // 第一列
                            if (board[0].charAt(0) == 'X' && board[2].charAt(0) == 'X') {
                                X = true;
                            }
                        } else if (j == 1 && i == 2) {  // 第三列
                            if (board[0].charAt(2) == 'X' && board[2].charAt(2) == 'X') {
                                X = true;
                            }
                        }
                    }
                }
                if (board[j].charAt(i) == 'O') {
                    Onums++;
                    if (!O) {
                        if (j == 1 && i == 1) {
                            if ((board[1].charAt(0) == 'O' && board[1].charAt(2) == 'O') // 横
                                    || (board[0].charAt(1) == 'O' && board[2].charAt(1) == 'O')  // 竖
                                    || (board[0].charAt(2) == 'O' && board[2].charAt(0) == 'O')  // 右斜
                                    || (board[0].charAt(0) == 'O' && board[2].charAt(2) == 'O')) // 左斜
                            {
                                O = true;
                            }
                        } else if (j == 0 && i == 1) {  // 第一排
                            if (board[0].charAt(0) == 'O' && board[0].charAt(2) == 'O') {
                                O = true;
                            }
                        } else if (j == 2 && i == 1) {  // 第三排
                            if (board[2].charAt(0) == 'O' && board[2].charAt(2) == 'O') {
                                O = true;
                            }
                        } else if (j == 1 && i == 0) { // 第一列
                            if (board[0].charAt(0) == 'O' && board[2].charAt(0) == 'O') {
                                O = true;
                            }
                        } else if (j == 1 && i == 2) {  // 第三列
                            if (board[0].charAt(2) == 'O' && board[2].charAt(2) == 'O') {
                                O = true;
                            }
                        }
                    }
                }
            }
        }
        if (X & !O) {
            return Xnums - Onums == 1;
        }
        if (O && !X) {
            return Xnums == Onums;
        }
        // Xnums必须大于等于Onums的1个
        // 只能有一个是连成一线，或者俩哥哥都不连成一线
        return (Math.abs(Xnums - Onums) <= 1 && Xnums >= Onums) && (X ^ O || (!X && !O));
    }
}
