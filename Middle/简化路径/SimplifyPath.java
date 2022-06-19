package Middle.简化路径;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class SimplifyPath {

    public static void main(String[] args) {
        System.out.println(simplifyPath(
//                "/a/../../b/../c//.//"
                "/home/../../.."
        ));
    }

    /**
     * 考虑完以下规则，即可得到真实路径：
     *  1. 多个/在一起需要换成一个/
     *  2. 遇到 . 和 空串 的时候，路径不变
     *  3. 遇到 ..
     *      1）如果当前不是根目录，返回上一级
     *      2）如果当前是根目录，保持不变
     *  4. 其他字符，移动到当前字符的目录
     */
    public static String simplifyPath(String path) {
        if (path.equals("/")) return path;  //1

        path = path.replaceAll("/+", "/");

        String[] ss = path.split("/");
        List<String> list = new LinkedList<>();
        list.add(ss[0]);
        int len = ss.length;

        for (int i=1; i<len; i++){
            if (ss[i].equals(".") || ss[i].equals("")){  // 2
                continue;
            }
            if (ss[i].equals("..")){  // 3
                if (!list.isEmpty()) list.remove(list.size()-1);  // 3-1
                // 3-2
            }else {  // 4
                list.add(ss[i]);
            }
        }

        StringBuilder sb = new StringBuilder("/");
        for (String s: list){
            if (!s.equals("")) sb.append(s).append("/");
        }
        if (sb.length()>1) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    // 栈实现：如果遇到目录，放入栈底，遇到.. 从栈底弹元素
    // 拼接路径的时候，从栈顶出发
    // 因此可以用一个双端队列来实现栈
    public static String simplifyPath2(String path) {
        String[] ss = path.split("/");  // 直接对/进行拆分，多个/会被拆分成多个空串
        // 用一个双端队列来表示栈，队头是栈底，因为先进来，队尾是栈顶，因为后进来
        Deque<String> deque = new ArrayDeque<>();
        for (String s : ss) {
            if (s.equals("..")) {  // 遇到返回上一级，判断是否需要返回
                if (!deque.isEmpty()) deque.pollLast();
            } else if (s.length() > 0 && !s.equals(".")) {  // 过滤空串 和 .
                deque.addLast(s);
            }
        }
        // 用一个StringBuilder拼接
        StringBuilder sb = new StringBuilder();
        if (deque.isEmpty()){
            sb.append("/");
        }else {
            for (String s:deque){
                sb.append("/").append(s);
            }
        }
        return sb.toString();
    }
}
