package Easy.可以形成最大正方形的矩形数目;

import java.util.Arrays;
import java.util.Comparator;

public class CountGoodRectangles {
    public int countGoodRectangles(int[][] rectangles) {
        for (int[] recs: rectangles){
            int t1 = recs[0], t2 = recs[1];
            recs[0] = Math.max(t1, t2);
            recs[1] = recs[0]==t1?t2:t1;
        }
        Arrays.sort(rectangles, Comparator.comparingInt(x->-x[1]));
        int ans = 1;
        for (int i=1; i<rectangles.length; i++){
            if (rectangles[i][1]<rectangles[i-1][1]) break;
            else  ans++;
        }
        return ans;
    }

    public int countGoodRectangles2(int[][] rectangles) {
        int ans = 0;
        int max = -1;
        for (int[] rec: rectangles){
            int cur = Math.min(rec[0], rec[1]);
            if (cur>max){
                max =cur;
                ans = 1;
            }else if (cur==max){
                ans++;
            }
        }
        return ans;
    }
}
