package Difficult.跳跃游戏4;

import java.util.*;

public class JumpGame4 {
    /**
     * 1. 可以从 i->i+1，前提是i+1<arr.length
     * 2. 可以从 i->i-1，前提是i-1>=0
     * 3. 可以从 i->j, 前提是 arr[i]=arr[j]
     */
    public int minJumps(int[] arr) {
        // 记录同一个值的存在的所有索引位置的集合
        Map<Integer, List<Integer>> idxSameValue = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            idxSameValue.putIfAbsent(arr[i], new ArrayList<>());
            idxSameValue.get(arr[i]).add(i);
        }
        // 记录这个位置是否有访问过
        Set<Integer> visitedIndex = new HashSet<>();
        // 存储在之前几轮已经标记访问过的节点，且在这一轮可以访问到的节点
        // 数组值存储的是当前节点的索引值和走到当前节点的步数
        // 这个队列在循环里面是先存储的等值跳的位置，再存储的前后跳的位置
        // 使得我们后面的操作优先等值跳而不是前后跳
        // 类似于贪心：等值跳到最后可以得到最优解，但是前后跳不行
        Queue<int[]> queue = new ArrayDeque<>();
        // 最开始从第一个节点出发，并且步数为0
        queue.offer(new int[]{0, 0});
        visitedIndex.add(0);  // 标记访问
        while (!queue.isEmpty()) {
            int[] idxStep = queue.poll();
            int idx = idxStep[0], step = idxStep[1];
            if (idx == arr.length - 1) {  // 如果走到了最后一格，那么就返回所用的步数
                return step;
            }
            int v = arr[idx];
            step++;  // 没有走到终点，必然会迈出一步，步数++
            if (idxSameValue.containsKey(v)) {
                for (int i : idxSameValue.get(v)) {
                    if (visitedIndex.add(i)) {  // 添加成功，返回true，说明这个节点还没有去过
                        // 在队列里面添加记录：该节点需要step步可到达
                        queue.offer(new int[]{i, step});
                    }
                }
                // 删除掉已经访问过的节点集合，防止下次来的时候，重复访问超时
                idxSameValue.remove(v);
            }
            // 两端也是可以走的
            if (idx + 1 < arr.length && visitedIndex.add(idx + 1)) {
                queue.offer(new int[]{idx + 1, step});
            }
            if (idx - 1 >= 0 && visitedIndex.add(idx - 1)) {
                queue.offer(new int[]{idx - 1, step});
            }
        }
        // 整个走完后，都走不到最后一个，返回-1
        return -1;
    }

    // BFS  32ms
    public int minJumps2(int[] arr) {
        // Store a number and all positions
        Map<Integer, ArrayList<Integer>> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            if (!map.containsKey(arr[i])) {
                map.put(arr[i], new ArrayList<>());
            }

            // For continuous numbers, only store its starting pos and end pos
            if (i > 0 && i < arr.length - 1 && arr[i] == arr[i - 1] && arr[i] == arr[i + 1]) {
                continue;
            }
            map.get(arr[i]).add(i);
        }

        // BFS
        Queue<Integer> queue = new LinkedList<>(){{add(0);}};

        int step = 0;
        boolean[] visited = new boolean[arr.length];
        visited[0] = true;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int pos = queue.isEmpty()?0:queue.poll();
                if (pos == arr.length - 1) {
                    return step;
                }

                // Add all next possible positions to queue
                if (pos - 1 >= 0 && !visited[pos - 1]) {
                    queue.add(pos - 1);
                    visited[pos - 1] = true;
                }

                if (pos + 1 < arr.length && !visited[pos + 1]) {
                    if (pos + 1 == arr.length - 1) {
                        return step + 1;
                    }
                    queue.add(pos + 1);
                    visited[pos + 1] = true;
                }

                ArrayList<Integer> posWithSameValue = map.get(arr[pos]);
                for (int length = posWithSameValue.size(), j = length - 1; j >= 0; j--) {
                    if (posWithSameValue.get(j) == arr.length - 1) {
                        return step + 1;
                    }
                    if (!visited[posWithSameValue.get(j)]) {
                        queue.add(posWithSameValue.get(j));
                        visited[posWithSameValue.get(j)] = true;
                    }
                }
            }

            step++;
        }

        return -1;
    }
}
