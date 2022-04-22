package Difficult.删除无效的括号;

import java.util.ArrayList;
import java.util.List;

public class 超低时间复杂度 {
    public static List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();
        remove(s,res,0,0,new char[]{ '(' , ')'});
        return res;
    }

    public static void remove(String s,List<String> res,int checkIndex,int deleteIndex,char[] chars){
        for (int count = 0,i = checkIndex; i < s.length(); i++) {
            if (s.charAt(i) == chars[0]){
                count++;
            }
            if (s.charAt(i) == chars[1]){  // s.charAt(i) == chars[1]
                count--;
            }
            // i位置: 第一个不合法(第一个右括号多于左括号位置),在i位置之前检查元素，并删除多于的右括号
            if (count < 0){
                for (int j = deleteIndex; j <= i; j++) {
                    //  s.charAt(j) == chars[1],如果 s.charAt(j-1) == chars[1]),最后形成的结果会一样(删除s.charAt(j)或者删除s.charAt(j-1))
                    if (s.charAt(j) == chars[1] && (j == deleteIndex || s.charAt(j-1) != chars[1])){
                        remove(s.substring(0,j) +s.substring(j+1,s.length()),
                                res,i,j,chars);
                    }
                }
                return;
            }
        }
        //  上面是从左往右遍历，删除多余的右括号，接下来需要从右往左遍历，删除多余的左括号，确保可以收集完所有可能
        String reverseStr = new StringBuilder(s).reverse().toString();
        if (chars[0] == '('){
            remove(reverseStr,res,0,0,new char[]{ ')', '('});
        }else {
            //  如果左括号和右括号已经删除完毕，添加答案
            res.add(reverseStr);
        }

    }
}
