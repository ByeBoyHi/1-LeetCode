package Easy.构造矩阵;

public class Solution {
    // 暴力破解
    public int[] constructRectangle(int area) {
        int L = (int) Math.sqrt(area);
        if(L*L==area){
            return new int[]{L, L};
        }
        int W = 1;
        for (int i=L+1; i<=area; i++){
            W = area/i;
            if (W*i==area){
                L = i;
                break;
            }
        }
        return new int[]{L, W};
    }


    public int[] constructRectangle1(int area) {
        int L = (int) Math.sqrt(area);
        if(L*L==area){
            return new int[]{L, L};
        }
        int W = 1;
        for (int i=L; i>=1; i--){  // 这里反过来办理，就比原来的快很多啊。。
            W = area/i;
            if (W*i==area){
                L = i;
                break;
            }
        }
        return new int[]{W, L};
    }
}
