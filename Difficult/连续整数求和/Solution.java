package Difficult.连续整数求和;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().consecutiveNumbersSum2(1000000000));
    }

    /**
     * 一个数字的连续整数和：一定是这个数字除以一个数字，然后这个数字朝外扩散之和。
     * 如：数字 Num --> Num --> Num/2+Num/2+1 --> Num/3+(Num/3+1)+(Num/3-1)+(Num/3+2)+(Num/3-2)
     * 如果是偶数：如 18 --> 除以1：18
     * --> 除以2：8+9==17 9+10==19所以没有两位相加
     * --> 除以3：5+6+7 可以
     * --> 除以4：2+3+4+5+6 = 20 没有 --> 去掉 2 就合适了（从两头去）
     * --> 除以5：同 4。
     * --> 除以6：3+2+1+0+(-1)+..  有负数出现，退出
     * <p>
     * 如果是奇数：如 19 --> 除以1：19
     * --> 除以2：9+10
     * --> 除以3：5+6+7=18<19 不可以
     * --> 除以4：2+3+4+5+6 --> 20-19=1<2
     * --> 除以5：3+2+1+0+4+5+6 -->21-19=2>1 <1+2
     * --> 除以6：有负数，退出。
     * <p>
     * 思路：定义遍历 index=1(++) 用以记录除以几。
     * 1. curNum=n/index;
     * 2. 往上加到 curNum+(n-2)，然后往下加，最多加到curNum-(n-2)，每加一个就判断是否小于，
     * 小于就继续加，等于就ans++，停止本次循环，大于就退出本次循环。
     * 3. 用一个集合存储这一串数字，存储为String，用来判断是否计数重复。
     * 4. 当最小的数字为负数，可以退出整个循环。
     */
    // 堆溢出
    public int consecutiveNumbersSum2(int n) {
        int index = 3;
        int sum;
        List<String> set = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        while (true) {
            int curNum = n / index;
            sb.append(curNum);
            sum = curNum;
            // 先往上加
            for (int i = 1; i <= index - 2; i++) {
                sum += (curNum + i);
                sb.append(curNum + i);
                if (sum >= n) {  // 如果大于等于了，那后面的也没必要加了退出
                    break;
                }
            }
            // 往下加
            for (int i = 1; i <= index - 2; i++) {
                sum += (curNum - i);
                sb.append(curNum + i);
                if (sum >= n) {  // 如果大于等于了，那后面的也没必要加了退出
                    break;
                }
            }
            if (sum == n && !set.contains(sb.toString())) {  // 如果最后的和等于n并且set里面没有加过这个，我们就记录一下
                set.add(sb.toString());
            }
            if (n / index - index + 2 <= 0) break;
            index++;
        }
        if (n != 1 && (n & 1) == 1) return set.size() + 2;
        return set.size() + 1;
    }

    // 超时
    public int consecutiveNumbersSum(int n) {
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            long j = 1;
            long sum = 2 * j * i;
            while (sum < (long) 2 * n) {
                j++;  // 前j项和
                sum = (2 * j * i + j * (j - 1));
            }
            if (sum == (long) 2 * n) {
                ans++;
            }
        }
        return ans;
    }

    // 技巧一
    // 因为 n = (x+1)+(x+2)+...+(x+k) = kx + k*(k+1)/2  --> k^2+k(2x+1)=2n
    // 那么 k < sqrt(2n)
    // 并且 n - (1+2+...+k) = kx
    public int consecutiveNumbersSum3(int n) {
        int ans = 1;  // 自身
        for (int i = 2; i < Math.sqrt(2 * n); i++) {
            if ((n - (i * (i + 1) / 2)) % i == 0) ans++;
        }
        return ans;
    }
}
