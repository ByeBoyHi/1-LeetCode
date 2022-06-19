package Easy.有效的括号;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

/**
 * 有待研究。
 */

public class Solution {


    public static void main(String[] args) {
        System.out.println(isValid("[](){}"));
        System.out.println(isValid("[)]("));
        System.out.println(isValid("([{}])"));
        System.out.println(isValid("["));
        System.out.println(isValid("]"));
        System.out.println(isValid("]["));
        // System.out.println(isValid(")123"));

    }


    // 官解
    private static final Map<Character, Character> map = new HashMap<Character, Character>() {{
        put('{', '}');
        put('[', ']');
        put('(', ')');
        put('?', '?');
    }};
    private static boolean isValid(String s) {
        if (s.length() > 0 && !map.containsKey(s.charAt(0))) return false;
        LinkedList<Character> stack = new LinkedList<Character>() {{
            add('?');
        }};
        for (Character c : s.toCharArray()) {
            if (map.containsKey(c)) stack.addLast(c);
            else if (map.get(stack.removeLast()) != c) return false;
        }
        return stack.size() == 1;
    }


    /**
     *  个解
     * 1. 用map进行判断弹出的和读取的是否配对
     * 2. 在最初判断字符串长度是否为1，节省时间
     * 3. 在弹栈的时候，判断是否为空
     * 4. 在整个循环完了看一下栈里面还有没有东西，如果有，直接false，没有配对完
     * 5. 判断字符串中是否有不是括号的字符  未解决！
     *
     * @param s
     * @return
     */
    private static boolean isValid2(String s) {
        if (s.length() == 1) return false;
        HashMap<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');
        Stack<Character> stack = new Stack();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{') {
                stack.push(s.charAt(i));
            } else {
                if (!stack.isEmpty() && map.get(stack.pop()) != s.charAt(i)) {
                    return false;
                }
            }
            s = s.substring(i);
        }
        if (!stack.isEmpty()) return false;
        return true;
    }
}
