package Middle.迷你语法分析器;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Deserialize {
    // 定义全局变量来控制当前遍历的位置
    int index = 0;

    /*
        这个方法要求我们只能调用有已有的方法实现对应的操作，而不能自己定义别的属性等元素
     */
    public NestedInteger deserialize(String s) {
        // 如果当前数左括号，那么就把左括号之间的所有数字都存进去
        if (s.charAt(index) == '[') {
            index++; //移动到下一位
            NestedInteger ni = new NestedInteger(); // 记录当前数字
            while (s.charAt(index) != ']') {
                ni.add(deserialize(s));  // 在数字处理完之后，会回到这里，所以 ] 和 , 都是在这里处理的
                if (s.charAt(index) == ',') {  // 数字分隔
                    index++;
                }
            }
            index++;
            return ni;
        } else { // 否则处理数字，这里只是单纯的处理数字，没有递归层
            boolean negative = false;  // 处理负数
            if (s.charAt(index) == '-') {
                index++;
                negative = true;
            }
            int num = 0;
            while (index < s.length() && Character.isDigit(s.charAt(index))) {
                // 把当前数字处理了，进行对象返回
                num = num * 10 + s.charAt(index) - '0';
                index++;
            }
            if (negative) {
                num *= -1;
            }
            return new NestedInteger(num);
        }
    }

    // 栈处理
    public NestedInteger deserialize2(String s) {
        if (s.charAt(0) != '[') {
            return new NestedInteger(Integer.parseInt(s));
        }
        Stack<NestedInteger> stack = new Stack<>();
        int num = 0;  // 记录每一个数字
        boolean negative = false;  // 记录是否是负数
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '[') {  // 说明一个列表出来了
                stack.push(new NestedInteger());
            } else if (ch == '-') {
                negative = true;
            } else if (ch >= '0' && ch <= '9') {
                num = num * 10 + ch - '0';
            /*
                对于 "[123,[456,[789]]]"
                在下一个列表出现前，会被逗号拦截到，那么这个数字也会被存储到合适的NestInteger里面
             */
            } else if (ch == ',' || ch == ']') {  // 说明一个数字结束了
                if (Character.isDigit(s.charAt(i - 1))) {  // 前一个字符是数字，说明前面就是数字
                    if (negative) {
                        num *= -1;
                    }
                    NestedInteger ni = new NestedInteger(num);
                    stack.peek().add(ni);
                }
                // 重新清零
                num = 0;
                negative = false;
                // 如果当前字符是 ] ，说明这一整个列表都结束了
                // 并且保证这不是最后一个 ]，那么才有的操作，毕竟最后一个是我们需要返回的值
                if (ch == ']' && stack.size() > 1) {
                    NestedInteger cur = stack.pop();
                    stack.peek().add(cur);
                }
            }
        }
        return stack.pop();
    }
}

class NestedInteger {


    // Constructor initializes an empty nested list.
    public NestedInteger() {

    }

    // Constructor initializes a single integer.
    public NestedInteger(int value) {
    }

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger() {
        return true;
    }

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger() {
        return 1;
    }

    // Set this NestedInteger to hold a single integer.
    public void setInteger(int value) {

    }

    // Set this NestedInteger to hold a nested list and adds a nested integer to it.
    public void add(NestedInteger ni) {

    }

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return empty list if this NestedInteger holds a single integer
    public List<NestedInteger> getList() {
        return new ArrayList<>();
    }
}