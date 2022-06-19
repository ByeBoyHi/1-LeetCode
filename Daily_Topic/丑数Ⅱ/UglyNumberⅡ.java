package Daily_Topic.丑数Ⅱ;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class UglyNumberⅡ {
    public static void main(String[] args) {
        UglyNumberⅡ test = new UglyNumberⅡ();
        System.out.println(test.nthUglyNumber(1690));
    }

    public int nthUglyNumber(int n) {
        int res = 1;
        int cur = 1;
        while (n > cur) {
            res++;
            if (isUglyNumber(res)) {
                cur++;
            }
        }
        return res;
    }

    // 判断丑数
    public boolean isUglyNumber(int num) {
        if (num == 1) return true;  //1通常被认定为丑数
        while (num != 1) {
            if (num % 2 == 0) {
                num = num >> 1;
            } else if (num % 3 == 0) {
                num = num / 3;
            } else if (num % 5 == 0) {
                num = num / 5;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * 要得到从小到大的第 nn 个丑数，可以使用最小堆实现。
     *
     * 初始时堆为空。首先将最小的丑数 11 加入堆。
     *
     * 每次取出堆顶元素 xx，则 xx 是堆中最小的丑数，由于 2x, 3x, 5x2x,3x,5x 也是丑数，因此将 2x, 3x, 5x2x,3x,5x 加入堆。
     *
     * 上述做法会导致堆中出现重复元素的情况。为了避免重复元素，可以使用哈希集合去重，避免相同元素多次加入堆。
     *
     * 在排除重复元素的情况下，第 nn 次从最小堆中取出的元素即为第 nn 个丑数。
     *
     * @param n 第n个丑数
     * @return  返回第n个丑数是什么
     */
    public int nthUglyNumber2(int n) {
        int[] factors = {2, 3, 5};  // 因子
        Set<Long> seen = new HashSet<>();  // 用来存储已经有的丑数，可以去重
        PriorityQueue<Long> heap = new PriorityQueue<Long>();  // 优先级队列：默认小顶堆
        seen.add(1L);  // 1必然是丑数
        heap.offer(1L);  // 最小堆里面加入
        int ugly = 0;
        for (int i = 0; i < n; i++) {
            long curr = heap.poll();  // 取出队头元素
            // 循环了n次，取出了n次，不管最小堆里面有多少个元素，取出的第n个元素一定是我们需要的第n个丑数
            ugly = (int) curr;
            for (int factor : factors) {
                long next = curr * factor;
                if (seen.add(next)) {  // 用HashSet去重
                    heap.offer(next);  // 每次添加1-3个元素到最小堆中
                }
            }
        }
        return ugly;
    }

}
