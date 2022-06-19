package Difficult.到达目的地的第二短时间;

import java.util.*;

public class SecondMinTime {
    /**
     * 在启程之初，所有信号灯都是绿色。
     * 用一个List数组存储所有边的关系
     * 然后用一个数组存储从起点到该点的最短路径和次短路径，便于从该点往后更新
     * 每当更新一个次短路径或者最短路径的时候，都有把新的路径放入队列里面，便于下次更新
     * 更新到终点后，退出计算从起点到终点路过这么多点需要花费的时间
     * 以两个change为一个单位，因为两个change就会让绿灯重新变回绿灯
     * 对两个change取余，得到目前是红灯还是绿灯，是否需要等待
     * 等待时间是 2*change-ret%(2*change)，也就是后一个change剩下的时间
     * @param n         n个城市
     * @param edges     城市之间的可通过的边
     * @param time      穿过每一条边所需要的时间
     * @param change    每个城市的交通信号灯的改变时间频率
     * @return          返回到达终点城市的第二短的时间
     */
    public int secondMinimum(int n, int[][] edges, int time, int change) {
        List<Integer>[] graph = new List[n+1];
        for (int i=0; i<n+1; i++){
            graph[i] = new ArrayList<>();
        }
        // 存储所有边的信息
        for (int[] edge : edges){
            int x = edge[0], y=edge[1];
            graph[x].add(y);
            graph[y].add(x);
        }
        // path[i][0]表示从1到i的最短路径
        // path[i][1]表示从1到i的次短路径
        int[][] path = new int[n+1][2];
        for (int i=0; i<n+1; i++) Arrays.fill(path[i], Integer.MAX_VALUE);
        // 存储需要更新的路径的队列
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{1,0});  // 最开始从起点出发，路径长度是0
        while (path[n][1]==Integer.MAX_VALUE && !queue.isEmpty()){  // 当次短路径更新后，就不需要迭代了
            int[] now = queue.poll();
            int cur = now[0], len = now[1];
            // 遍历当前节点能到达的所有边，更新能到达的最短和次短路径。
            for (int e: graph[cur]){  // 这里使得他可以来回跑
                if (path[e][0]>len+1) {
                    path[e][0] = len+1;
                    queue.add(new int[]{e, len+1});
                }else if (path[e][1]>len+1 && path[e][0]<len+1){
                    path[e][1] = len+1;
                    queue.add(new int[]{e, len+1});
                }
            }
        }

        int ret = 0;
        for (int i=0; i<path[n][1]; i++){
            if (ret%(2*change)>=change){
                ret = ret + 2*change-ret%(2*change);
            }
            ret += time;
        }
        return ret;
    }
}
