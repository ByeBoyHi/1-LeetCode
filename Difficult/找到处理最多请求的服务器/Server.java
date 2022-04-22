package Difficult.找到处理最多请求的服务器;

import java.util.*;

public class Server {

    // 1. 服务器记录数组
    int[] servers;
    // 2. 服务器结束时间
    int[] serversEnd;

    // 1558ms：暴力破解
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        servers = new int[k * 2];
        serversEnd = new int[2 * k];
        int max = 0;
        for (int i = 0; i < arrival.length; i++) {  // 每个请求到来
            int start = i % k;
            for (int j = start; j < k + start; j++) {  // 判断当前开始的服务器是否有可运行的
                if (arrival[i] >= serversEnd[j]) {
                    servers[j]++;  // 服务次数+1
                    if (serversEnd[j] == 0) {
                        serversEnd[j] += arrival[i];  // 当前时间加上，才是正确的结束时间
                    } else {
                        serversEnd[j] += (arrival[i] - serversEnd[j]);
                    }
                    serversEnd[j] += load[i];  // 下一次结束时间

                    if (j >= k) {
                        if (serversEnd[j - k] == 0) {
                            serversEnd[j - k] += arrival[i];  // 开始时间加上，才是正确的结束时间
                        } else {
                            serversEnd[j - k] += (arrival[i] - serversEnd[j - k]);  // 时间差和运行时间，构成新的结束时间
                        }
                        serversEnd[j - k] += load[i];
                        servers[j - k]++;
                    } else {
                        if (serversEnd[j + k] == 0) {
                            serversEnd[j + k] += arrival[i];  // 开始时间加上，才是正确的结束时间
                        } else {
                            serversEnd[j + k] += (arrival[i] - serversEnd[j + k]);
                        }
                        serversEnd[j + k] += load[i];
                        servers[j + k]++;
                    }

                    max = Math.max(max, servers[j]);  // 记录最大服务次数
                    break;
                }
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (servers[i] == max) {
                ans.add(i);
            }
        }
        return ans;
    }

    /*
        有序集合：存储所有有效的服务器的序号
        优先队列：存储忙碌中的服务器，以最早结束时间为排序标准。存储的是结束时间和服务器索引值。
     */
    public List<Integer> busiestServers2(int k, int[] arrival, int[] load) {
        // 记录闲置服务器
        TreeSet<Integer> available = new TreeSet<>();
        for (int i = 0; i < k; i++) {
            available.add(i);
        }
        // 记录忙碌程度的优先队列
        PriorityQueue<int[]> busy = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        int[] requests = new int[k];  // 记录服务器的处理次数
        // 对每个请求进行遍历处理
        for (int i = 0; i < arrival.length; i++) {
            while (!busy.isEmpty() && busy.peek()[0] <= arrival[i]) {
                available.add(busy.poll()[1]);  // 把所有可以处理这个 请求的服务器都加进去
            }

            if (available.isEmpty()) continue; // 没有可以处理的服务器，跳过这个请求

            Integer p = available.ceiling(i % k);  // 找到i%k为天花板的第一个值
            if (p == null) {  // 意思是从i%k开始，没有可以处理的服务器
                p = available.first();  // 取第一个服务器，也就是绕一圈回来了头部
            }

            requests[p]++;
            // 把当前服务器加进去
            busy.offer(new int[]{arrival[i] + load[i], p});
            // 从有效集合中去掉
            available.remove(p);
        }
        int max = 0;
        if (Arrays.stream(requests).max().isPresent()) {
            max = Arrays.stream(requests).max().getAsInt();
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (requests[i] == max) {
                ans.add(i);
            }
        }
        return ans;
    }
}

class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        if(k >= arrival.length){
            List<Integer> ret = new ArrayList<>();
            for(int i = 0;i<arrival.length;i++){
                ret.add(i);
            }
            return ret;
        }

        // TreeSet<U> freeNumSet = new TreeSet(new Comparator<U>(){
        //     public int compare(U o1,U o2){
        //         return o1.num - o2.num;
        //     }
        // });
        TreeMap<Integer,U> freeNumMap = new TreeMap<>();
        PriorityQueue<U> taskQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o.end));

        // init task
        U[] us = new U[k];
        for(int i = 0;i<k;i++){
            U  u = new U();
            u.end = arrival[i] + load[i];
            u.count = 1;
            u.num = i;
            us[i] = u;

            taskQueue.offer(u);
        }


        int maxCount = 1;
        for(int i = k;i<arrival.length;i++){
            // free machine
            while(!taskQueue.isEmpty() &&
                    taskQueue.peek().end <= arrival[i]){
                freeNumMap.put(taskQueue.peek().num,taskQueue.poll());
            }

            int startIndex = i % k;
            // search free
            if(freeNumMap.isEmpty()) continue;

            Integer freeNum = freeNumMap.ceilingKey(startIndex) ;
            freeNum = freeNum == null ? freeNumMap.ceilingKey(0) : freeNum;// loop

            // add task
            U u = freeNumMap.remove(freeNum);
            u.end = arrival[i] + load[i];
            maxCount = Math.max(maxCount,++u.count);
            taskQueue.offer(u);
        }

        List<Integer> ret = new ArrayList<>();
        for(int i = 0;i<k;i++){
            if(us[i].count == maxCount) ret.add(i);
        }

        return ret;
    }

    static class U{
        int num;
        int end;
        int count;
    }
}
