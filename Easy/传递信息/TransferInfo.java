package Easy.传递信息;

import java.util.*;

public class TransferInfo {
    public int numWays(int n, int[][] relation, int k) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] re: relation){
            int from = re[0];
            int to = re[1];
            if (map.containsKey(from)){
                map.get(from).add(to);
            }else {
                List<Integer> cur = new ArrayList<>();
                cur.add(to);
                map.put(from, cur);
            }
        }
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(0);
        while (k>0&&!queue.isEmpty()){
            Queue<Integer> tmp = new ArrayDeque<>();
            while (!queue.isEmpty()) {
                List<Integer> cur = map.get(queue.poll());
                if(cur==null) continue;
                for (int i : cur) {
                    tmp.offer(i);
                }
            }
            k--;
            queue = tmp;
        }
        int ans = 0;
        while (!queue.isEmpty()){
            if (queue.poll()==n-1){
                ans++;
            }
        }

        return ans;
    }


    int k;
    List<List<Integer>> edges;
    int n;
    int ways = 0;

    public int numWays2(int n, int[][] relation, int k) {
        this.k = k;
        this.n = n;
        for (int i=0; i<n; i++){  // 给每个位置新建一个链表
            edges.add(new ArrayList<>());
        }
        // 建立有向图关系
        for (int[] re:relation){
            edges.get(re[0]).add(re[1]);
        }
        dfs(0,0);
        return ways;
    }

        // dfs
    public void dfs(int index, int step){
        if (step==k){
            if (index==n-1){
                ways++;
            }
            return;
        }
        List<Integer> list = edges.get(index);
        for (int i: list){
            dfs(i, step+1);
        }
    }

    // dp
    public int numWays3(int n, int[][] relation, int k){
        // dp[i][j]:表示经过i轮传递给j的次数
        int[][] dp = new int[k+1][n];
        dp[0][0] = 1;  // 经过0轮传给0的次数
        // 经过i轮，对于每一条边，从src传给dst的次数
        for (int i=0; i<k; i++){
            for (int[] edge: relation){
                int src = edge[0], dst = edge[1];
                dp[i+1][dst]+=dp[i][src];  // 从src一步到dst的次数和
            }
        }
        return dp[k][n-1];
    }

    // dp2
    /*
        对于上面的dp解法，显然dp[i+1]只和dp[i]有关，可以优化为一维数组
     */
    public int numWays4(int n, int[][] relation, int k) {
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i=0; i<k; i++) {
            int[] next = new int[n];
            for (int[] edge : relation) {
                next[edge[1]] = dp[edge[0]];  // 用上一次的结果来更新这一次的
            }
            dp = next;
        }
        return dp[n-1];
    }
}
