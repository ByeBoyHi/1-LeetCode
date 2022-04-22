package Middle.喧闹和富有;

import java.util.*;

public class LoudAndRich {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(loudAndRich(new int[][]{
                {1, 0}, {2, 1}, {3, 1}, {3, 7}, {4, 3}, {5, 3}, {6, 3}
        }, new int[]{
                3, 2, 5, 4, 6, 1, 7, 0
        })));
    }

    /**
     * 思路：
     *  1. 安静值进行升序排序
     *  2. 直接用map找比自己富有的
     *  3. 挨个找每个人的更富有且最安静的人
     *  4. 如果没有则是自己
     */
    public static int[] loudAndRich(int[][] richer, int[] quiet) {
        if(richer==null || richer.length==0){
            Arrays.sort(quiet);
            return quiet;
        }

        int[] ans = new int[quiet.length];

        // 存储比自己富有的
        HashMap<Integer, HashSet<Integer>> map = new HashMap<>(quiet.length);
        for (int[] ints : richer) {
            if (!map.containsKey(ints[1])) {
                HashSet<Integer> cur = new HashSet<>();
                cur.add(ints[0]);
                map.put(ints[1], cur);
            } else {
                map.get(ints[1]).add(ints[0]);
            }
        }

        for (Integer n:map.keySet()){
            HashSet<Integer> set = map.get(n);
            set = dfs(map, n);
        }

        for (Integer n:map.keySet()){
            System.out.println(n+":"+map.get(n));
        }

        for (int i=0; i<quiet.length; i++){
            if (!map.containsKey(i)){
                ans[i] = i;
            }else {
                int max = quiet[i];
                int index = i;
                Set<Integer> cur = map.get(i);
                for (Integer n: cur){
                    int num = quiet[n];
                    if (max>num){
                        max = num;
                        index = n;
                    }
                }
                ans[i] = index;
            }
        }

        return ans;
    }

    // 时间优化，否则超时
    static HashMap<Integer, HashSet<Integer>> dp = new HashMap<>();
    public static HashSet<Integer> dfs(HashMap<Integer, HashSet<Integer>> map, int n){
        if (!map.containsKey(n)){
            return null;
        }

        HashSet<Integer> cur = map.get(n);
        // 这个t用来解决ConcurrentModification问题，即线程不同步问题
        HashSet<Integer> t = new HashSet<>();
        for (Integer num:cur){
            HashSet<Integer> tmp = dfs(map, num);
            if (tmp!=null ){
                if (!dp.containsKey(num)) {
                    dp.put(num, tmp);
                }
                t.addAll(dp.get(num));
            }
        }
        cur.addAll(t);
        return cur;
    }

}
//官解一：深度优先搜索
class Solution {
    public int[] loudAndRich(int[][] richer, int[] quiet) {
        int n = quiet.length;
        List<Integer>[] g = new List[n];
        for (int i = 0; i < n; ++i) {
            g[i] = new ArrayList<>();
        }
        for (int[] r : richer) {
            g[r[1]].add(r[0]);
        }

        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        for (int i = 0; i < n; ++i) {
            dfs(i, quiet, g, ans);
        }
        return ans;
    }

    public void dfs(int x, int[] quiet, List<Integer>[] g, int[] ans) {
        if (ans[x] != -1) {
            return;
        }
        ans[x] = x;
        for (int y : g[x]) {
            dfs(y, quiet, g, ans);
            if (quiet[ans[y]] < quiet[ans[x]]) {
                ans[x] = ans[y];
            }
        }
    }
}

// 官解二：拓扑排序
class Solution2 {
    public int[] loudAndRich(int[][] richer, int[] quiet) {
        int n = quiet.length;
        List<Integer>[] g = new List[n];
        for (int i = 0; i < n; ++i) {
            g[i] = new ArrayList<Integer>();
        }
        int[] inDeg = new int[n];
        for (int[] r : richer) {
            g[r[0]].add(r[1]);
            ++inDeg[r[1]];
        }

        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            ans[i] = i;
        }
        Queue<Integer> q = new ArrayDeque<Integer>();
        for (int i = 0; i < n; ++i) {
            if (inDeg[i] == 0) {
                q.offer(i);
            }
        }
        while (!q.isEmpty()) {
            int x = q.poll();
            for (int y : g[x]) {
                if (quiet[ans[x]] < quiet[ans[y]]) {
                    ans[y] = ans[x]; // 更新 x 的邻居的答案
                }
                if (--inDeg[y] == 0) {
                    q.offer(y);
                }
            }
        }
        return ans;
    }
}