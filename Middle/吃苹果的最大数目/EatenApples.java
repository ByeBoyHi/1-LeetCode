package Middle.吃苹果的最大数目;

import java.lang.reflect.Array;
import java.util.*;

public class EatenApples {

    public static void main(String[] args) {
        System.out.println(eatenApples(new int[]{
//                1,2,3,5,2
                2, 1, 10
        }, new int[]{
//                3,2,1,4,2
                2, 10, 1
        }));
    }

    // 递归：从前往后
    public static int eatenApples(int[] apples, int[] days) {
        int[][] newApple = new int[apples.length][days.length];
        for (int i=0; i< apples.length; i++){
            newApple[i][0] = apples[i];
            newApple[i][1] = days[i]+i+apples[i];
        }
        Arrays.sort(newApple, Comparator.comparingInt(x->x[1]));
        return process(newApple, 0, 0);
    }

    public static int process(int[][] newApples, int pre, int index){
        if (index==newApples.length){
            return 0;
        }
        if (newApples[index][1]+index<=pre){
            return process(newApples, pre, index+1);
        }
        int cur = Math.min(newApples[index][0],newApples[index][1]-pre);
        return process(newApples, pre+cur, index+1)+cur;
    }

    public static int eatenApples2(int[] apples, int[] days){
        if (apples==null || apples.length==0){
            return 0;
        }
        int ans = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        int n = apples.length;
        int i = 0;
        while (i < n) {
            // 剩下时间不够用了
            while (!pq.isEmpty() && pq.peek()[0] <= i) {
                pq.poll();
            }
            int rottenDay = i + days[i];
            int count = apples[i];
            if (count > 0) {
                pq.offer(new int[]{rottenDay, count});
            }
            if (!pq.isEmpty()) {
                int[] arr = pq.peek();
                arr[1]--;
                if (arr[1] == 0) {  // 苹果吃完了
                    pq.poll();
                }
                ans++;
            }
            i++;
        }
        while (!pq.isEmpty()) { // 可能上面的n天走完后，还剩下很多苹果没有吃完，这里处理后续
            while (!pq.isEmpty() && pq.peek()[0] <= i) {
                pq.poll();
            }
            if (pq.isEmpty()) {
                break;
            }
            int[] arr = pq.poll();
            int curr = Math.min(arr[0] - i, arr[1]);
            ans += curr;
            i += curr;
        }
        return ans;
    }
}
