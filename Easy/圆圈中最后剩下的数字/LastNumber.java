package Easy.圆圈中最后剩下的数字;

public class LastNumber {

    /*
        对于f问题，删除当前n个数字的第m个，因为是围成一个圈
        所以是 m%n。
        上一次找到的数字是留下的数字是x，那么这次就是从x开始往后第m个
     */
    public int lastRemaining(int n, int m) {
        return f(n, m);
    }

    public int f(int n, int m) {
        if (n == 1) {
            return 0;
        }
        int x = f(n - 1, m);
        return (m + x) % n;
    }

    // 非递归
    public int lastRemaining2(int n, int m) {
        int f = 0;
        /*
            当i=1的时候，只有一个元素0位置，所以直接从2开始，其实位置在不断更新
            当更新到最后的时候，就是我们最终的数字。
            倒着推答案。
         */
        for (int i = 2; i != n + 1; ++i) {
            f = (m + f) % i;
        }
        return f;
    }
}
