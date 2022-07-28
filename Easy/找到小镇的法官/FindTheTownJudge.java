package Easy.找到小镇的法官;

public class FindTheTownJudge {
    // 信任没有传递性，比如是直接信任关系。。
    // 这就是一个有向图，可以找到唯一的出度为0的点，这个点就是法官，否则没有
    public int findJudge(int n, int[][] trust) {
        int[] inDegree = new int[n + 1];
        int[] outDegree = new int[n + 1];
        for (int[] edge : trust) {
            int x = edge[0], y = edge[1];
            outDegree[x]++;
            inDegree[y]++;
        }
        for (int i = 1; i <= n; i++) {
            // 当n=1, trust.len=0的时候
            // inD=n-1=0, outD=0，所以返回1，合理。
            // 即只有一个人的时候，他自己就是法官
            if (inDegree[i] == n - 1 && outDegree[i] == 0) {
                return i;
            }
        }
        return -1;
    }
}
