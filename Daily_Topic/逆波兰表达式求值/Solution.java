package Daily_Topic.逆波兰表达式求值;

import java.util.Stack;

/**
 * 示例 1：
 * <p>
 * 输入：tokens = ["2","1","+","3","*"]
 * 输出：9
 * 解释：该算式转化为常见的中缀算术表达式为：((2 + 1) * 3) = 9
 * 示例 2：
 * <p>
 * 输入：tokens = ["4","13","5","/","+"]
 * 输出：6
 * 解释：该算式转化为常见的中缀算术表达式为：(4 + (13 / 5)) = 6
 * 示例 3：
 * <p>
 * 输入：tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
 * 输出：22
 * 解释：
 * 该算式转化为常见的中缀算术表达式为：
 * ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 * <p>
 * ======================================================
 * <p>
 * 逆波兰表达式是一种后缀表达式，所谓后缀就是指算符写在后面。
 * <p>
 * 平常使用的算式则是一种中缀表达式，如 ( 1 + 2 ) * ( 3 + 4 ) 。
 * 该算式的逆波兰表达式写法为 ( ( 1 2 + ) ( 3 4 + ) * ) 。
 * 逆波兰表达式主要有以下两个优点：
 * <p>
 * 去掉括号后表达式无歧义，上式即便写成 1 2 + 3 4 + * 也可以依据次序计算出正确结果。
 * 适合用栈操作运算：遇到数字则入栈；遇到算符则取出栈顶两个数字进行计算，并将结果压入栈中。
 * =========================================================================
 * 提示：
 * <p>
 * 1 <= tokens.length <= 104
 * tokens[i] 要么是一个算符（"+"、"-"、"*" 或 "/"），要么是一个在范围 [-200, 200] 内的整数
 */

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] s = new String[]{"10","6","9","3","+","-11","*","/","*","17","+","5","+"};
        System.out.println(solution.evalRPN2(s));
    }

    /**
     * 6ms
     * 38.3M   O(N)
     * @param tokens  字符串数组
     * @return  返回逆波兰表达式计算值
     */
    public int evalRPN(String[] tokens) {
        if (tokens.length == 0) {
            return 0;
        }
        int temp;
        Stack<Integer> stack = new Stack<>();
        for (String ch : tokens) {
            switch (ch) {
                case "-" : {
                    int a = stack.pop();
                    int b = stack.pop();
                    temp = b - a;
                    stack.push(temp);
                    break;
                }
                case "+" :{
                    temp = stack.pop() + stack.pop();
                    stack.push(temp);
                    break;
                }
                case "/" : {
                    int a = stack.pop();
                    int b = stack.pop();
                    temp = b / a;
                    stack.push(temp);
                    break;
                }
                case "*" : {
                    temp = stack.pop() * stack.pop();
                    stack.push(temp);
                    break;
                }
                default : stack.push(Integer.valueOf(ch));
            }
        }
        return stack.pop();
    }

    /**
     * 3ms
     * 38.3M  O(N)
     * @param tokens  字符串数组
     * @return  返回逆波兰表达式计算值
     *
     * 如果将改代码改为 foreach 和 switch， 速度3ms,内存38M
     */
    public int evalRPN2(String[] tokens) {
        if (tokens.length == 0) {
            return 0;
        }
        int[] res = new int[tokens.length];
        int index = -1;
        for (int i = 0; i < tokens.length; i++) {
            switch (tokens[i]) {
                case "+":
                    res[index - 1] = res[index - 1] + res[index];
                    index--;
                    break;
                case "-":
                    res[index - 1] = res[index - 1] - res[index];
                    index--;
                    break;
                case "*":
                    res[index - 1] = res[index - 1] * res[index];
                    index--;
                    break;
                case "/":
                    res[index - 1] = res[index - 1] / res[index];
                    index--;
                    break;
                default:
                    res[++index] = Integer.parseInt(tokens[i]);
                    break;
            }
        }
        return res[index];
    }
}
