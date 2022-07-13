package Easy.����ֵ��Ԫ�����Ŀ.OddCellNum;

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

    // ʡȥ����arr
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
                // ֱ���жϽ���λ�õ�ֵ������Ҫ�����arr�������洢
                if (((row[i] + col[j]) & 1) == 1) cnt++;
            }
        }
        return cnt;
    }

    // �ٽ������Ż�
    // O(m+n+indices.length)  O(m+n)
    // һ��λ��Ϊ���������ҽ���rows��cols����λ�ã�һ��Ϊ������һ��Ϊż����ʱ�����ʵ�֡�
    // ��˶����У����� ����������*��ż������
    // �����У����� ����������*��ż������
    // ���ǵĺͣ��������յĴ�
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
