package Middle.括号生成;

import java.util.ArrayList;
import java.util.List;

public class Parenthesis {

    List<String> ans;

    public List<String> generateParenthesis(int n) {
        ans = new ArrayList<>();
        process(n, n, "");
        return ans;
    }

    /*
        生成括号的时候，左边括号是必须小于等于右边括号的
     */
    public void process(int left, int right, String pre){
        if (left==0 && right==0) {
            ans.add(pre);
            return;
        }
        if (left==0){  // 只能放右括号
            process(left, right-1, pre+")");
        }else if (left==right){  // 这时候只能放左括号
            process(left-1, right, pre+"(");
        }else {  //
            process(left-1, right, pre+"(");
            process(left, right-1, pre+")");
        }
    }
}
