package Easy.括号的最大嵌套深度;

import java.util.Stack;

public class MaximumNestingDepth {
    public int maxDepth(String s) {
        int size = 0;
        int ans = 0;
        for (int i=0; i<s.length(); i++){
            char ch = s.charAt(i);
            if (ch=='('){
                size++;
                ans = Math.max(ans, size);
            }else if (ch==')'){
                size--;
            }
        }
        return ans;
    }
}
