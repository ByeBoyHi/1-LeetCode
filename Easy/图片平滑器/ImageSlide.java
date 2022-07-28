package Easy.图片平滑器;

public class ImageSlide {
    int m;
    int n;
    int[][] ans;

    public int[][] imageSmoother(int[][] img) {
        m = img.length;
        n = img[0].length;
        ans = new int[m][n];

        for (int i = 0; i < m; i++) {  // 控制行
            for (int j = 0; j < n; j++) {  // 控制列
                int cur = img[i][j];
                int num = 1;
                if (i-1>=0){
                    cur+=img[i-1][j];
                    num++;
                    if (j-1>=0){
                        cur+=img[i-1][j-1];
                        num++;
                    }
                    if (j+1<n){
                        cur+=img[i-1][j+1];
                        num++;
                    }
                }
                if (i+1<m){
                    cur+=img[i+1][j];
                    num++;
                    if (j-1>=0){
                        cur+=img[i+1][j-1];
                        num++;
                    }
                    if (j+1<n){
                        cur+=img[i+1][j+1];
                        num++;
                    }
                }
                if (j-1>=0){
                    cur+=img[i][j-1];
                    num++;
                }
                if (j+1<n){
                    cur+=img[i][j+1];
                    num++;
                }
                ans[i][j] = cur/num;
            }
        }
        return ans;
    }

}
