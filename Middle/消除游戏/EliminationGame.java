package Middle.消除游戏;

import java.util.Arrays;

public class EliminationGame {

    // TLE：超时 Time limit exceeded
    public int lastRemaining(int n) {

        int[] arr = new int[n+1];
        for (int i=1; i<=n; i++){
            arr[i] = i;
        }

        // true:从左往右
        // false:从右往左
        boolean flag = true;
        int size = n;
        while (size!=1){
            if (flag){
                int start = 1;
                while (arr[start]==-1)start++;
                arr[start]=-1;
                size--;
                int step = 0;
                for (int i=start; i<=n; i++){
                    if (arr[i]==-1){
                        continue;
                    }
                    if (step==0){
                        step++;
                    }else {
                        arr[i]=-1;
                        size--;
                        step=0;
                    }
                }
                flag = false;
            }else {
                int start = n;
                while (arr[start]==-1)start--;
                arr[start]=-1;
                size--;
                int step = 0;
                for (int i=start; i>=1; i--){
                    if (arr[i]==-1){
                        continue;
                    }
                    if (step==0){
                        step++;
                    }else {
                        arr[i]=-1;
                        size--;
                        step=0;
                    }
                }
                flag = true;
            }
        }

        for (int i=1; i<=n; i++){
            if (arr[i]!=-1){
                return arr[i];
            }
        }

        return 1;
    }

    // 等差数列优化：log n
    // 我这里删除了an元素，也就是不记录最后一个元素的位置
    // 因为在整个过程我们都没有用到最后一个元素，如果遇到类似的题目需要，可以加上
    public int lastRemaining2(int n) {
        int a1 = 1;
        int k = 0, cnt = n, step = 1;
        while (cnt > 1) {
            if (k % 2 == 0) { // 正向
                a1 = a1 + step;
            } else { // 反向
                a1 = (cnt % 2 == 0) ? a1 : a1 + step;
            }
            k++;
            cnt = cnt >> 1;
            step = step << 1;
        }
        return a1;
    }
}
