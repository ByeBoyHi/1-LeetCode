package Middle.爱吃香蕉的珂珂;

import java.util.Arrays;

/**
 * <功能描述>
 * Time: 2022/6/7
 * User: HeyBoy
 */
public class Banana {

    // 二分查找的方式找合适的速度
    // 最小速度是1，最大速度是最多的一堆
    public int minEatingSpeed(int[] piles, int h) {
        int low = 1;
        int high = getMax(piles);
        while (high > low) {
            int speed = low + ((high - low) >> 1);
            int time = getTime(piles, speed);
            if (time > h) {
                low = speed + 1;
            } else {
                high = speed;
            }
        }
        return high;
    }

    private int getTime(int[] piles, int speed) {
        int time = 0;
        for (int b : piles) {
            // 向上取整
            // +speed 就实现了最后的 +1
            // b-1 实现了取小一点儿
            // 假如 b%speed==0，那么-1+speed实现了归位
            // 假如 b%speed!=0，那么-1+speed实现了+1
            time += (b + speed - 1) / speed;
        }
        return time;
    }

    public int getMax(int[] arr) {
        int max = 0;
        for (int b : arr) {
            max = Math.max(max, b);
        }
        return max;
    }

}
