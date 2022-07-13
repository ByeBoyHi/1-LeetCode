package Easy.奇数值单元格的数目.OddCellNum;

import java.util.Arrays;

public class OddCell {

    public int oddCells(int m, int n, int[][] indices) {
        int[][] arr = new int[m][n];
        int[] row = new int[m];
        int[] col = new int[n];
        for (int[] index : indices) {
            row[index[0]]++;
            col[index[1]]++;
        }

        for (int i=0; i<m; i++){
            if (row[i]!=0){
                Arrays.fill(arr[i], row[i]);
            }
        }
        for (int j=0; j<n; j++){
            if (col[j]!=0){
                for (int r=0; r<m; r++){
                    arr[r][j]+=col[j];
                }
            }
        }

        int ans = 0;
        for (int i=0; i<m; i++){
            for (int j=0; j<n; j++){
                if ((arr[i][j]&1)==1){
                    ans++;
                }
            }
        }
        return ans;
    }

    // 省去数组arr
    public int oddCells2(int m, int n, int[][] indices) {
        int[] row = new int[m];
        int[] col = new int[n];
        for (int[] nums : indices) {
            row[nums[0]]++;
            col[nums[1]]++;
        }
        int cnt = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 直接判断交叉位置的值，不需要额外的arr数组来存储
                if (((row[i] + col[j]) & 1) == 1) cnt++;
            }
        }
        return cnt;
    }

    // 官解最终优化
    // O(m+n+indices.length)  O(m+n)
    // 一个位置为奇数，当且仅当rows和cols交叉位置，一个为奇数另一个为偶数的时候才能实现。
    // 因此对于行，就是 行奇数个数*列偶数个数
    // 对于列，就是 列奇数个数*行偶数个数
    // 他们的和，就是最终的答案
    public int oddCells3(int m, int n, int[][] indices) {
        int[] rows = new int[m];
        int[] cols = new int[n];
        for (int[] index : indices) {
            rows[index[0]]++;
            cols[index[1]]++;
        }
        int oddx = 0, oddy = 0;
        for (int i = 0; i < m; i++) {
            if ((rows[i] & 1) != 0) {
                oddx++;
            }
        }
        for (int i = 0; i < n; i++) {
            if ((cols[i] & 1) != 0) {
                oddy++;
            }
        }
        return oddx * (n - oddy) + (m - oddx) * oddy;
    }
}
