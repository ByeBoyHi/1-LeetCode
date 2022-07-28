package Middle.文件的最长绝对路径;

import java.util.ArrayDeque;
import java.util.Deque;

public class MaxLenDir {

    /*
        按照\n进行换行
        按照\t进行分级
        有多少个\t，就是多少级的目录

        思路：用栈进行每一层目录的长度存储。
            对于每一个文件，以\n为分界线，可以用来识别每一个文件
            对于每一层目录，以\t为标记，多少个\t，就代表当前文件的深度是多少
            在遍历每一层的时候，都用栈来记录这一层的全体路径的长度
            当访问到文件的时候，还需要更新全体路径长度的答案

     */

    public int lengthLongestPathStack(String input) {
        int ans = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        int pos = 0; // 标记当前访问到哪儿了
        int n = input.length();
        while (pos<n){
            int depth = 1; // 当前深度默认为1
            // 每一个\t都代表一个深度
            while (pos<n && input.charAt(pos)=='\t'){
                pos++;
                depth++;
            }
            // 统计当前文件的长度
            boolean isFile = false;
            int len = 0;
            while (pos<n && input.charAt(pos)!='\n'){  // 还没有走到换层
                if (input.charAt(pos)=='.'){
                    isFile = true;
                }
                pos++;
                len++;
            }
            pos++; // 跳过当前换行符，来到下一个文件的开始

            while (!stack.isEmpty() && stack.size()>=depth){
                stack.pop();
            }
            if (!stack.isEmpty()){
                len +=stack.peek()+1;  // +1 是因为文件分级的时候有 ‘\’
            }
            if (isFile){  // 如果是文件，更新长度
                ans = Math.max(len, ans);
            }else{
                stack.push(len);
            }
        }
        return ans;
    }

    // 用数组同理
    public int lengthLongestPathArray(String input) {
        int ans = 0;
        int pos = 0; // 标记当前访问到哪儿了
        int n = input.length();
        int[] level = new int[n];

        while (pos<n){
            int depth = 1; // 当前深度默认为1
            // 每一个\t都代表一个深度
            while (pos<n && input.charAt(pos)=='\t'){
                pos++;
                depth++;
            }
            // 统计当前文件的长度
            boolean isFile = false;
            int len = 0;
            while (pos<n && input.charAt(pos)!='\n'){  // 还没有走到换层
                if (input.charAt(pos)=='.'){
                    isFile = true;
                }
                pos++;
                len++;
            }
            pos++; // 跳过当前换行符，来到下一个文件的开始


            if (depth>1){
                len +=level[depth-2]+1;  // +1 是因为文件分级的时候有 ‘\’
            }
            if (isFile){  // 如果是文件，更新长度
                ans = Math.max(len, ans);
            }else{
                level[depth-1] = len;
            }
        }
        return ans;
    }
}
