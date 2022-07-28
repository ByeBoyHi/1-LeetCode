package Daily_Topic.GenerateParenthesis;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * 比如：有n个左括号和n个右括号，然后以字符串数组的形式输出全部排列组合
 */

public class GenerateParenthesis {

    public static void main(String[] args) {
        GenerateParenthesis g = new GenerateParenthesis();
        System.out.println(g.generateParenthesis(2));
    }

    public List<String> generateParenthesis(int n) {
        // 用ArrayList来就不用重写方法了。
        List<String> res = new ArrayList<>();
        if (n <= 0) {
            return res;
        }

//        HashSet set = new HashSet(res);  用于dfs
//        res = new ArrayList<String>(set);

//        dfs1("", n, n, res);  // 减法
        dfs("", n, n, 0, 0, res);  // 加法
        return res;
    }

    public void dfs(String str, int left, int right,int l, int r, List<String> res) {
        if (l==left && r==right){
            res.add(str);
            return;
        }

        if (l<r){ // 剪枝，左括号加的比右边少
            return;
        }
        if (l<left){ // 左括号还没加完
            dfs(str+"(", left, right, l+1, r, res);
        }
        if (r<right){
            dfs(str+")", left, right, l, r+1, res);
        }

    }

    /**
     * 用深度优先的方式搜索   --   减法
     *
     * @param str：用来储存的数组
     * @param left：左括号的个数
     * @param right：右括号的个数
     * @param res：最终的链表结果
     */
    public void dfs1(String str, int left, int right, List<String> res) {
        // 用来判断左右括号是否用完了，用完了就加进来，这个无需进行回溯
        if (left == 0 && right == 0) {
            res.add(str);
            return;
        }

        // 剪枝：左括号的个数严格大于右括号的格式的时候，才剪枝。
        if (left > right) {
//            因为在递归，所以这个return可以返回到上一层方法中。
            return;
        }

        if (left > 0) {
            dfs1(str + "(", left - 1, right, res);
        }
        if (right > 0) {
            dfs1(str + ")", left, right - 1, res);
        }
    }

    /**
     * 加法
     *
     * @param curStr 当前递归得到的结果
     * @param left   左括号已经用了几个
     * @param right  右括号已经用了几个
     * @param n      左括号、右括号一共得用几个
     * @param res    结果集
     */
    private static void dfs2(String curStr, int left, int right, int n, List<String> res) {
        if (left == n && right == n) {
            res.add(curStr);
            return;
        }

        // 剪枝
        if (left < right) {
            return;
        }
        // 在进行迭代的路上，如果返回到这个位置的时候，会跳到下一个的right，然后继续迭代，这样就达到了遍历了所有结果的目的
        if (left < n) {
            dfs2(curStr + "(", left + 1, right, n, res);
        }
        if (right < n) {
            dfs2(curStr + ")", left, right + 1, n, res);
        }
    }

}
