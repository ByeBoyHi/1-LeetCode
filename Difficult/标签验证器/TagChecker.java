package Difficult.标签验证器;

import java.util.ArrayDeque;
import java.util.Deque;

public class TagChecker {
    /*
        1. 前后有相同的标签 <tag> </tag>
        2. 标签长度为 1-9
        3. 内容部分，有 <![CDATA[CDATA_CONTENT]]> 的时候，CDATA_CONTENT部分跳过检查
        4. 可以嵌套标签，tagName必须全大写
     */
    public boolean isValid(String code) {
        int i=0;
        Deque<String> deque = new ArrayDeque<>(); // 存放tagName
        int n = code.length();
        while (i<n){
            if (code.charAt(i)=='<'){
                // 当前是最后一个字符
                if (i==n-1){
                    return false;
                }
                // 下一个字符是/  结束字符
                if (code.charAt(i+1)=='/'){
                    int j = code.indexOf('>', i); // 从i后面找一个>
                    if (j<0){  // 如果没有或者长度超标
                        return false;
                    }
                    String tagName = code.substring(i+2,j);
                    if (deque.isEmpty() || !deque.pop().equals(tagName)){
                        return false;
                    }
                    i = j+1;
                    if (deque.isEmpty() && i!=n){  // 闭合后，如果没有走到结尾，那么里面必然还有标签名字
                        return false;
                    }
                }else if (code.charAt(i+1)=='!'){  // cdata
                    if (deque.isEmpty() || i+9>n){  // 一个不被包的cdata 或者剩下的不够装了
                        return false;
                    }
                    String cdata = code.substring(i+2, i+9);
                    if (!cdata.equals("[CDATA[")){
                        return false;
                    }
                    int j = code.indexOf("]]>", i);
                    if (j<0) return false;
                    i = j+1;
                }else {  // 开始标签
                    int j = code.indexOf(">", i);
                    if (j<0 || j-i-1>9) return false;  // 检查长度
                    String tagName = code.substring(i+1, j);
                    if (tagName.length()<1 || tagName.length()>9) {
                        return false;
                    }
                    // 检查大写
                    for (int k=0; k<tagName.length(); k++){
                        if (!Character.isUpperCase(tagName.charAt(k))) return false;
                    }
                    deque.push(tagName);
                    i = j+1;
                }
            }else {
                // 里面始终有一个标签，并且当一个标签闭合后，必然出现新的标签补上
                if (deque.isEmpty()){
                    return false;
                }
                i++;
            }
        }

        // 最后如果都完美闭合了
        return deque.isEmpty();
    }
}
