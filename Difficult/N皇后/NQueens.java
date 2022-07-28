package Difficult.N皇后;

import java.util.ArrayList;
import java.util.List;

public class NQueens {

    public static void main(String[] args) {
        System.out.println(solveNQueens(4));
    }

    // 第一种普通方式
    /*
        一维数组 record[i] 表示第 i 行的皇后放在 record[i] 列上
     */
    static List<List<String>> ans = new ArrayList<>();;
    public static List<List<String>> solveNQueens(int n) {
        if (n<1) {
            return ans;
        }
        int[] record = new int[n];
        process(0, record, n);
        return ans;
    }

    // 主运行函数
    public static void process(int i, int[] record, int n){

        if (i==n) {
            List<String> list = new ArrayList<>();
            for (int cur: record){
                StringBuilder sb = new StringBuilder();
                for (int k = 0; k < n; k++) {
                    if (k==cur){
                        sb.append("Q");
                    }else {
                        sb.append(".");
                    }
                }
                list.add(sb.toString());
            }
            ans.add(list);
            return;
        }


        for (int j = 0; j < n; j++) {
            if (isValid(record, i, j)){
                record[i] = j;
                process(i+1, record, n);
            }
        }
    }

    // 判断当前位置放皇后是否合理
    /*
        判断在第 i 行 j 列位置是否可以放皇后
     */
    public static boolean isValid(int[] record, int i, int j){

        for (int k = 0; k < i; k++) {  // 这里的判断只需要判断该行前面是否与之相撞，因为后面的还没放
            // 因为record是记录的每一行的信息，所有不需要判断是否共行

            // 判断是否共列
            if (j==record[k]){
                return false;
            }

            // 共斜线
            // 45度或者135度
            if (Math.abs(k-i)==Math.abs(record[k]-j)){
                return false;
            }
        }

        return true;
    }

    // 位加速：取列的那里出了问题，还没解决
    // 当前列的限制
    // 左斜线的限制
    // 右斜线的限制
    public void process1(
            int limit,
            int colLimit,
            int leftLimit,
            int rightLimit,
            int n,
            int[] record,
            int i
    ){
        if (colLimit==limit){  // 限制都放满了
            List<String> list = new ArrayList<>();
            for (int cur: record){
                StringBuilder sb = new StringBuilder();
                for (int k = 0; k < n; k++) {
                    if (k==cur){
                        sb.append("Q");
                    }else {
                        sb.append(".");
                    }
                }
                list.add(sb.toString());
            }
            ans.add(list);
            return;
        }

        int mostRightOne;
        int pos = limit&(~(leftLimit | rightLimit | colLimit));  // 找到所有能放的位置
        while (pos!=0){
            mostRightOne = pos & (~pos+1);  // 找到最右侧的第一个1，也就是可以放皇后的位置，相当于列的位置
            int col;
            for (int k=0; ; k++){
                if ((mostRightOne&(1<<k))==1){
                    col = k;
                    break;
                }
            }
            record[i] = col;
            pos -= mostRightOne;  // 去掉当前位置
            process1(limit,
                    colLimit | mostRightOne,
                    (leftLimit | mostRightOne)<<1,
                    (rightLimit | mostRightOne)>>>1,
                    n, record, i+1);
        }

    }
}
