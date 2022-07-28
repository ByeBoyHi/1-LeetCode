package 动态规划.中等.丑数2;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().nthUglyNumber(1690));
    }

    // 最小堆
    public int nthUglyNumber(int n) {
        if (n <= 6) return n;
        int[] factors = new int[]{2,3,5};
        // 所有数字都是 2x 3x 5x，把他们存入最小堆里面，会进行排序
        PriorityQueue<Long> heap = new PriorityQueue<>();
        Set<Long> seen = new HashSet<>();
        heap.add(1L);
        int number = 0;
        long ugly = 0;
        while (number<n){
            ugly = heap.poll();
            number++;
            for (int factor: factors){
                if (seen.add(ugly*factor)){
                    heap.add(ugly*factor);
                }
            }
        }
        return (int)ugly;
    }

    // 动态规划
    public int nthUglyNumber2(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        int p2 = 1, p3 = 1, p5 = 1;
        for (int i = 2; i <= n; i++) {
            /*
                这里有点儿类似于：arr[] = 1
                    第一轮：生成 2 3 5，选小的放进去，然后p2++  arr[] = 1,2
                        p2=2  p3=1  p5=1
                    第二轮：生成 4 3 5，选小的加进去，然后p3++  arr[] = 1,2,3
                        p2=2  p3=2  p5=1
                    第三轮：生成 4 6 5，选小的加进去，然后p2++  arr[] = 1,2,3,4
                        p2=3  p3=2  p5=1
                    第四轮：生成 6 6 5，选小的加进去，然后p5++  arr[] = 1,2,3,4,5
                    ...
                    每一轮都会用当前指向的数字乘以对应的倍数，选择小的进去，可以保证整个顺序是升序的。
                    并且由于之后的每个数字都是前面生成的，所以对应的因子也只会是 2 3 5，保证了一定是丑数。
             */
            int num2 = dp[p2] * 2, num3 = dp[p3] * 3, num5 = dp[p5] * 5;
            dp[i] = Math.min(Math.min(num2, num3), num5);
            if (dp[i] == num2) {
                p2++;
            }
            if (dp[i] == num3) {
                p3++;
            }
            if (dp[i] == num5) {
                p5++;
            }
        }
        return dp[n];
    }
}
