package Difficult.字典序的第K小数字;

import java.util.Objects;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class KthMin {

    // TLE
    public int findKthNumber(int n, int k) {
        TreeSet<String> tree = new TreeSet<>();
        for (int i = 1; i <= n; i++) {
            tree.add("" + i);
        }
        while (k > 1) {
            tree.pollFirst();
            k--;
        }
        return Integer.parseInt(Objects.requireNonNull(tree.pollFirst()));
    }

    // MLE
    public int findKthNumber2(int n, int k) {
        PriorityQueue<String> queue = new PriorityQueue<>();
        for (int i = 1; i <= n; i++) {
            queue.add("" + i);
        }
        while (k > 1) {
            queue.poll();
            k--;
        }
        return Integer.parseInt(Objects.requireNonNull(queue.poll()));
    }

    /*
        当前节点如果的子节点个数：steps(i)
        1. 大于k个，说明就在这棵树里面找k-1
        2. 小于等于k个，说明去兄弟节点里面找k-steps(i)
     */
    public int findKthNumber3(int n, int k) {
        int cur = 1;
        k--;
        while (k > 0) {
            int steps = getSteps(cur, n);  // 小于等于n的范围找，别越界
            if (steps <= k) {
                k = k - steps;
                cur++;
            } else {
                cur *= 10;
                k--;
            }
        }
        return cur;
    }

    public int getSteps(int curr, long n) {
        int steps = 0;
        long first = curr;
        long last = curr;
        while (first <= n) {
            steps += Math.min(last, n) - first + 1;
            first = first * 10;
            last = last * 10 + 9;
        }
        // 找到steps个节点
        return steps;
    }

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
    }
}
