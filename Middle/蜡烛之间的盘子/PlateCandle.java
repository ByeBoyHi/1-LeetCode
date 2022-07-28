package Middle.蜡烛之间的盘子;

import java.util.Arrays;

public class PlateCandle {

    // TLE
    public int[] platesBetweenCandles(String s, int[][] queries) {
        int len = s.length();
        // 记录最靠近自己的左侧的盘子和右侧的盘子
        int[] left = new int[len];
        int[] right = new int[len];
        left[0] = -1;
        right[len-1] = 100005;  // 右侧的不合法值应该用右侧的极大值代替，而不是-1
        char[] chars = s.toCharArray();
        for (int i=1; i<len; i++){
            if (chars[i-1]=='|' && chars[i]=='*') { // 左边是个蜡烛并且自己是个盘子
                left[i] = i-1;
            }else{
                left[i] = left[i-1];
            }
        }
        for (int i=len-2; i>=0; i--){
            if (chars[i+1]=='|' && chars[i]=='*') { // 左边是个蜡烛并且自己是个盘子
                right[i] = i+1;
            }else{
                right[i] = right[i+1];
            }
        }

        int[] ans = new int[queries.length];
        for (int i=0; i<len; i++){
            if (chars[i]=='*'){
                for (int j=0; j<queries.length; j++) {
                    if (queries[j][0] <= left[i] && queries[j][1] >= right[i]) {
                        ans[j]++;
                    } else {
                        break;
                    }
                }
            }
        }
        return ans;
    }

    // 寻找蜡烛：一个区间最左边的蜡烛和最右边的蜡烛
    public int[] platesBetweenCandles2(String s, int[][] queries) {
        // 用来记录当前位置即之前有多少个盘子
        int[] preSum = new int[s.length()];

        // 记录总的盘子数量
        int sum = 0;
        char[] chars = s.toCharArray();
        for (int i=0; i<chars.length; i++){
            if (chars[i]=='*'){
                sum++;
            }
            preSum[i] = sum;
        }

        // 用来记录当前位置左侧最靠近的蜡烛和右侧最靠近的蜡烛
        int[] left = new int[s.length()];
        int[] right = new int[s.length()];

        for (int i=0, l=-1; i<s.length(); i++){
            if (chars[i] == '|') {
                l = i;
            }
            left[i] = l;
        }
        for (int i=s.length()-1, r=-1; i>=0; i--){
            if (chars[i]=='|'){
                r = i;
            }
            right[i] = r;
        }

        int[] ans = new int[queries.length];
        for (int i=0; i<queries.length; i++){
            int[] ints = queries[i];
            int x = right[ints[0]], y = left[ints[1]];
            ans[i]+= x==-1||y==-1||x>=y?0:preSum[y]-preSum[x];
        }
        return ans;
    }
}
