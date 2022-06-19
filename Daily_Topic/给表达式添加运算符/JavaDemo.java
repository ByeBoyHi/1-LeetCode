package Daily_Topic.给表达式添加运算符;

import java.util.ArrayList;
import java.util.List;

public class JavaDemo {
    public static void main(String[] args) {
        System.out.println(new Solution().addOperators("105", 5));
    }
}

class Solution {
    /**
     * 按照回溯法穷举：
     * 这个题目只能按照顺序插符号，不能交换顺序。
     * 外面只需要从头不断的往后走，没来一个新的数字，就先插入一个运算符，除了第一个。
     * 在插入乘号的时候，需要考虑优先级，把上一次运算数字拿出来先做乘法，再做上一次的那个运算。
     * 保存这个运算结果给下一个回合使用。
     * 每次取数字可能会有连续的数字当作一个数字使用，要避免当使用多位数的时候，第一位不能是0。
     * 会有一个遍历来记录外面递归的深度，已经使用了多少的数字长度。
     * 如果已经用完了就可以开始判断是否等于target。
     * <p>
     * 定义一个辅助函数用来递归和回溯：
     * 传入参数：
     * - expr：上次递归结束的结果字符串
     * - i：已经递归到第几个字符了
     * - res：上一次递归结束的运算结果
     * - mul：上一次递归结束的最后运算算子
     */

    // 用成员属性来记录，相当于全局变量
    int n;
    String num;
    int target;
    List<String> ans;

    public List<String> addOperators(String num, int target) {
        this.n = num.length();
        this.num = num;
        this.target = target;
        this.ans = new ArrayList<>();
        StringBuilder expr = new StringBuilder();
        backTrace(expr, 0, 0, 0);
        return ans;
    }

    public void backTrace(StringBuilder expr, int i, long res, long mul) {
        // 递归结束条件：如果递归到最后一层，就可以判断是否结束
        if (i == n) {
            if (res == target) {
                ans.add(expr.toString());
            }
            return;
        }

        // 用一个变量来记录要插入符号的位置
        int signIndex = expr.length();
        if (i > 0) {  // 只有不是开头的时候，我们才需要为运算符占一个位置
            expr.append(0);  // 给运算符占一个位置，因为后面的for循环是先append数字，然后递归运算符的。
        }

        // 定义一个变量来记录运算结果
        long val = 0;
        // j 用来控制取的数字字符串的长度
        // 第一个判断是怕第一个数字就是 0，然后就没进来递归，所以让第一个数字直接进
        // 第二个判断是判断上一个数字是否是 0
        for (int j = i; j < n && (j == i || num.charAt(i) != '0'); j++) {
            // *10可以让当前数字前移，也就是上一次外面取数字 1取运算，这次就是 10+y取运算
            // '5' - '0' = 5
            val = val * 10 + num.charAt(j) - '0';
            // 先把当前数字加进来，因为前面有给符号占位置，所以这里不用留
            expr.append(num.charAt(j));
            if (i == 0) {  // 第一个数字不需要加符号，直接去下一层
                backTrace(expr, j + 1, val, val);
            } else {  // 其他位置，我们枚举符号
                expr.setCharAt(signIndex, '+');
                backTrace(expr, j + 1, res + val, val);
                expr.setCharAt(signIndex, '-');
                backTrace(expr, j + 1, res - val, -val);
                expr.setCharAt(signIndex, '*');
                backTrace(expr, j + 1, res - mul + mul * val, mul * val);
            }
        }
        // 在上面一圈递归结束之后，expr的长度会发生变化。
        // 这时候我们返回上一层还需要继续使用原来的长度去递归下一种可能性，所以这个时候要重置长度。
        expr.setLength(signIndex);
    }
}
