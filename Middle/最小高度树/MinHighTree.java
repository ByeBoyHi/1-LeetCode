package Middle.最小高度树;

import java.util.*;

public class MinHighTree {
    /*
        拓扑+BFS
        从外围往中间靠近，最开始度为1的一定是深度最大的，越靠近中间，深度越小
        所以通过不断的排除掉度为1的点，来往中间靠近
     */

    // 存储当前节点及其相邻节点
    List<List<Integer>> nexts = new ArrayList<>();
    // 存储所有节点信息
    int[] degree;

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> res = new ArrayList<>();
        if (n == 1) {
            res.add(0);
            return res;
        }
        degree = new int[n];
        for (int i = 0; i < n; i++) {
            nexts.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            degree[from]++;  // 度增加
            degree[to]++;
            nexts.get(from).add(to);  // 建立对应关系
            nexts.get(to).add(from);
        }

        // 把所有度为1的放进去，然后一圈一圈的往中心靠近，最中心的，就是深度最短的
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (degree[i] == 1) queue.add(i);
        }

        while (!queue.isEmpty()) {
            res = new ArrayList<>();  // 每一次，都要新new一个，因为每一次res都是存储的外圈一层的节点，到最后存储的才是我们想要的结果
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                assert !queue.isEmpty();
                int neighbor = queue.poll();  // 包装类可以是null，所以不会出现空指针问题
                res.add(neighbor); // 把每一次的节点都加进去（如果里面还有一层，那么这一层也会被去掉，不用担心）
                // 进行bfs遍历
                for (int next : nexts.get(neighbor)) {
                    degree[next]--;  // 把这个点的度-1，因为我们要去掉neighbor的影响
                    if (degree[next] == 1) {
                        queue.offer(next);
                    }
                }
            }
        }

        return res;
    }

    // 暴力遍历
    public List<Integer> findMinHeightTrees2(int n, int[][] edges) {
        List<Integer> res = new ArrayList<>();
        if (n == 0) {
            res.add(0);
            return res;
        }
        List<List<Integer>> map = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            map.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
        }

        int max = Integer.MAX_VALUE;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            visited[i] = true;
            int cur = dfs(map, map.get(i), 0, visited);
            if (max == cur) {
                res.add(i);
            } else if (max > cur) {
                res = new ArrayList<>();
                res.add(i);
                max = cur;
            }
            System.out.println(cur);
            visited[i] = false;
        }

        return res;
    }

    public int dfs(List<List<Integer>> map, List<Integer> next, int index, boolean[] visited) {
        if (index == next.size()) {
            return 0;
        }
        // 当前节点的邻接点访问过，那么直接退出
        // 去相邻节点，深度不变
        if (visited[next.get(index)]) return dfs(map, next, index+1, visited);
        visited[next.get(index)] = true;
        int max = 0;
        for (int neigh : map.get(index)) {
            if (!visited[neigh]) {
                visited[neigh] = true;
                max = Math.max(dfs(map, map.get(neigh), 0, visited), max);
            }
        }
        max++; // 遍历完所有的孩子节点，深度才会+1
        // 去相邻节点，深度不变
        max = Math.max(dfs(map, next, index + 1, visited), max);
        visited[next.get(index)] = false;
        return max;
    }

}
