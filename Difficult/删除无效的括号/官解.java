package Difficult.删除无效的括号;

import java.util.ArrayList;
import java.util.List;

public class 官解 {
    private List<String> ans = new ArrayList<>();

    public List<String> removeInvalidParentheses(String s) {
        int lRemove = 0;
        int rRemove = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {  // 记录所有左边的括号
                lRemove++;
            } else if (s.charAt(i) == ')') {
                if (lRemove == 0) {  // 出现右括号就消掉一个左括号，如果左括号已经消除完了，那么现在出现的右括号就是多余的。
                    rRemove++;
                } else {
                    lRemove--;
                }
            }
        }
        helper(s, 0, 0, 0, lRemove, rRemove);
        return ans;
    }

    /**
     * 递归去除多余括号后，对有效括号进行无重复的存储
     *
     * @param str     当前括号字符串
     * @param start   起始位置
     * @param lCount  已经用过的左括号个数
     * @param rCount  已经用过的右括号个数
     * @param lRemove 还需要删除的左括号个数
     * @param rRemove 还需要删除的右括号个数
     */
    private void helper(String str, int start, int lCount, int rCount, int lRemove, int rRemove) {
        if (lRemove == 0 && rRemove == 0) {
            if (isValid(str)) {
                ans.add(str);
            }
            return;
        }
        for (int i = start; i < str.length(); i++) {
            // 当出现连续括号的时候，如：(((())
            // 这时候前面四个括号任意去掉一个结果都是相同的，这时候没必要再重复刚才的遍历，再递归走一遍
            // 这时候我们用这个if语句进行判断
            // 这里相当于取出可能出现的重复字符串
            if (i != start && str.charAt(i) == str.charAt(i - 1)) {
                if (str.charAt(i) == '(') {
                    lCount++;
                } else if (str.charAt(i) == ')') {
                    rCount++;
                }
                // 当前右括号的数量大于左括号的数量则为非法,直接返回.
                if (rCount > lCount) {
                    break;
                }
                continue;
            }
            // 剩余的字符比我们还需要去掉的字符数量少
            if (lRemove + rRemove > str.length() - i) {
                return;
            }

            // 尝试去掉当前左括号
            if (lRemove > 0 && str.charAt(i) == '(') {
                helper(str.substring(0, i) + str.substring(i + 1), i, lCount, rCount, lRemove--, rRemove);
            }
            // 尝试去掉当前右括号
            if (rRemove > 0 && str.charAt(i) == ')') {
                helper(str.substring(0, i) + str.substring(i + 1), i, lCount, rCount, lRemove, rRemove--);
            }

        }
    }

    private boolean isValid(String s) {
        int cnt = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                cnt++;
            } else if (s.charAt(i) == ')') {
                cnt--;
                if (cnt < 0) {
                    return false;
                }
            }
        }
        return cnt == 0;
    }
}
