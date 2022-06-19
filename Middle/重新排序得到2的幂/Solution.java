package Middle.重新排序得到2的幂;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.reorderedPowerOf2(123));
    }

    boolean ans = false;
    List<String> res = new ArrayList<>();  // 测试全排列元素都是哪些

    public boolean reorderedPowerOf2(int n) {
        dfs(n + "", new StringBuilder(), new boolean[(n + "").length()]);
        return this.ans;
    }

    /**
     * 取出字符串的每个字符，然后对每个字符进行全排列，判断是否有字符是2的次幂，如果是，返回true，否则false。
     *
     * @param s      需要判断的字符串
     * @param curStr 当前已经拼接的字符串
     * @param used   记录每个索引位置是否已经被使用（这里建议用方法的局部传递遍历，而不是类的全局成员）
     */
    public void dfs(String s, StringBuilder curStr, boolean[] used) {
        if (curStr.length() == s.length() && curStr.charAt(0) != '0') {  // 已经取了n个数字

//            this.res.add(curStr.toString());
            if (isTwo(Integer.parseInt(curStr.toString()))) {
                this.ans = true;
            }
            return;
        }
        if (!this.ans) {
            for (int i = 0; i < s.length(); i++) {
                if (!used[i]) {
                    used[i] = true;
                    curStr.append(s.charAt(i));  // 尝试放进去
                    dfs(s, curStr, used);
                    used[i] = false;
                    curStr.deleteCharAt(curStr.length() - 1);  // 恢复现场
                }
            }
        }
    }

    /**
     * 判断当前数字是否是2的次幂，对于二进制，只需要判断32位即可
     *
     * @param n 需要判断的数字
     * @return 如果是2的次幂，返回true，否则false
     */
    private boolean isTwo(int n) {
        int cnt = 0;
        for (int i = 0; i < 32; i++) {
            if (((n >> i) & 1) == 1) {
                cnt++;
                if (cnt > 1) {
                    return false;
                }
            }
        }
        return cnt == 1;
    }

    // 一个数字如果是2的次幂，那么只有一个二进制位是1
    // 这个数字减一可得，这个一下面的所有0都变成一，而他变成0
    // 这时候这俩相与必为0。
    // 如： 100000 & 011111 = 000000
    private boolean isPowerOfTwo(int n) {
        return (n & (n - 1)) == 0;
    }
}
