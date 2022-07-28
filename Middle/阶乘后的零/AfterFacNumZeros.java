package Middle.阶乘后的零;

public class AfterFacNumZeros {

    public static void main(String[] args) {
        int n = 10000;
        System.out.println(new AfterFacNumZeros().trailingZeroes(n));
    }

    //On
    /*
        数学：
            末尾是零，取决于2*5=10。
            因此需要在所有数字中找2和5为因子的个数。
            然而为2的因子一定比为5的因子多，所以计算5的个数即可。
     */
    public int trailingZeroes(int n) {
        int ans = 0;
        for (int i = 5; i <= n; i += 5) {
            for (int x = i; x % 5 == 0; x /= 5) {
                ans++;
            }
        }
        return ans;
    }

    // 暴力
    public int trailingZeroes1(int n) {
        int a = 1;
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            a *= i;
            while (a % 10 == 0) {
                ans++;
                a = a / 10;
            }
            a = a % 100000;
        }
        return ans;
    }

    // Ologn
    /*
        对于数字130：
            130/5 = 26，说明在1~130有26个数字有一个因子5
            130/25 = 5，说明在1~130有5个数字有两个因子5（其中第一个已经在第一次算过）
            130/125= 1，说明在1~130有1个数字有三个因子5（其中前两个在前面两次已经算过）
        每次越过一个5的距离，不断筛过。
     */
    public int trailingZeroes2(int n) {
        int ans = 0;
        while (n > 0) {
            n /= 5;
            ans += n;
        }
        return ans;
    }

}
