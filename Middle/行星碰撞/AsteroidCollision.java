package Middle.行星碰撞;

import java.util.*;

public class AsteroidCollision {

    // 官解

    /**
     * 用一个栈来模拟星球的运动。
     * 依此遍历每个星球，当该星球为负数的时候，如果栈顶元素为正数，
     * 判断他俩谁更大，如果栈顶更大，则当前星球爆炸，并且存活为false，
     * 如果到最后存活依然为true，那么说明当前星球对于栈内所有元素都是可存活的，放进栈里面
     */
    public int[] asteroidCollision2(int[] asteroids) {
        Deque<Integer> queue = new ArrayDeque<>();
        for (int aster : asteroids) {
            boolean alive = true;
            // 小于零才可能发生碰撞，大于零只能用来装后面小于零的，所以先存进队列里面
            while (alive && aster < 0 && !queue.isEmpty() && queue.peek() > 0) {
                alive = queue.peek() < -aster;  // 是否存在，如果一正一负就会碰撞爆炸
                if (queue.peek() <= -aster) { // 会爆炸
                    queue.pop();
                }
            }
            if (alive) {
                queue.push(aster);
            }
        }
        int size = queue.size();
        int[] ans = new int[size];
        for (int i = size - 1; i >= 0; i--) {
            ans[i] = queue.pop();
        }
        return ans;
    }

    /**
     * 最终的碰撞，必然只能留下一个方向的行星
     * 1. 左边和右边的相撞，如果左边的小，那么左边的消失，左边的继续左移
     * 2. 左边和右边的相撞，如果右边的小，那么右边的消失，右边的继续右移
     * 因为是从左边开始遍历的，当左边的走到起点无法再移动后，去右端点的右边接着找一个向右移动的行星，重复上面的步骤
     * <p>
     * 找一个向右移动最大的和向左移动最大的两个行星。
     * 1. 如果有交集，那么交集中的所有行星都消失，并且他俩留下大的。
     * 较大的行星的另一边，重复判断。
     * 2. 如果没有交集，处理三部分
     */
    // 下面再看
    List<Integer> ans = new ArrayList<>();

    public int[] asteroidCollision(int[] asteroids) {
        process(asteroids, 0, asteroids.length - 1);
        Collections.sort(ans);
        int[] ret = new int[ans.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = asteroids[ans.get(i)];
        }
        return ret;
    }

    /**
     * @param asteroids 行星数组
     * @param left      左边界
     * @param right     右边界
     */
    public void process(int[] asteroids, int left, int right) {
        if (left < 0 || right >= asteroids.length || left > right) return;

        if (left == right) {
            ans.add(left);
            return;
        }

        int leftAsteroid = leftMax(asteroids, left, right);   // 最大值
        int rightAsteroid = rightMax(asteroids, left, right); // 最小值

        // 可能找到的两个星球都是负数或者都是正数
        // 那么中间的所有星球都要被保留下来
        if (
                (asteroids[leftAsteroid] > 0 && asteroids[rightAsteroid] > 0)
                        ||
                        (asteroids[leftAsteroid] < 0 && asteroids[rightAsteroid] < 0)
        ) {

            int a = Math.min(leftAsteroid, rightAsteroid);
            int b = Math.max(leftAsteroid, rightAsteroid);
            leftAsteroid = a;
            rightAsteroid = b;
            for (int i = leftAsteroid;
                 i <= rightAsteroid;
                 i++) {
                ans.add(i);
            }
            process(asteroids, left, leftAsteroid - 1);
            process(asteroids, rightAsteroid + 1, right);
        } else {

            if (leftAsteroid < rightAsteroid) {  // 有交集
                if (asteroids[leftAsteroid] + asteroids[rightAsteroid] > 0) {
                    // 左边更大，中间所有的都消失
                    ans.add(leftAsteroid);
                    process(asteroids, left, leftAsteroid - 1); // 左边
                    if (rightAsteroid != asteroids.length - 1) {
                        asteroids[rightAsteroid + 1] = asteroids[leftAsteroid];
                    }
                    process(asteroids, rightAsteroid + 1, right); // 右边
                } else if (asteroids[leftAsteroid] + asteroids[rightAsteroid] < 0) {
                    // 右边更大，中间所有的都消失
                    ans.add(rightAsteroid);
                    process(asteroids, rightAsteroid + 1, right);  // 右边
                    if (leftAsteroid != 0) {
                        asteroids[leftAsteroid - 1] = asteroids[rightAsteroid];
                    }
                    process(asteroids, left, leftAsteroid - 1);  // 左边
                } else {

                    /*
                        判断这个区间的最大值和最小值有多少个，如果不一样多，那么留下多的那一方的最边缘位置
                     */
                    int leftNum = 1;
                    int rightNum = 1;
                    for (int i = leftAsteroid + 1; i < rightAsteroid; i++) {
                        if (asteroids[i] == asteroids[leftAsteroid]) {
                            leftNum++;
                        }
                        if (asteroids[i] == asteroids[rightAsteroid]) {
                            rightNum++;
                        }
                    }
                    if (leftNum != rightNum) {
                        if (leftNum > rightNum) { // 右边全部抵消掉，留左边行星最右边的

                        } else { // 左边全部抵消掉，留右边行星最左边的

                        }
                    }

                    // 一样多，同归于尽，中间所有星球都消失
                    process(asteroids, left, leftAsteroid - 1);
                    process(asteroids, rightAsteroid + 1, right);
                }
            } else {  // 没有交集，处理三部分
                if (leftAsteroid == rightAsteroid) {
                    ans.add(leftAsteroid);
                } else {
                    process(asteroids, rightAsteroid + 1, leftAsteroid - 1);
                }
                process(asteroids, left, rightAsteroid);
                process(asteroids, leftAsteroid, right);
            }
        }
    }

    public int leftMax(int[] asteroids, int left, int right) {
        int ans = left;
        for (int i = left + 1; i <= right; i++) {
            if (asteroids[i] > asteroids[ans]) {
                ans = i;
            }
        }
        return ans;
    }

    public int rightMax(int[] asteroids, int left, int right) {
        int ans = right;
        for (int i = right - 1; i >= left; i--) {
            if (asteroids[i] < asteroids[ans]) {
                ans = i;
            }
        }
        return ans;
    }
}
