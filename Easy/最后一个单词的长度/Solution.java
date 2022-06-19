package Easy.最后一个单词的长度;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int res = s.lengthOfLastWord1(" ");
        System.out.println(res);
    }

    /**
     * 1. 判断字符串是否是空串
     * 2. 如果不是再进行计算：最后一个单词的长度等于字符串长度 - 第一个空格的下标索引 - 1
     * @param s 输入参数：单词字符串
     * @return  返回最后一个单词的长度
     */
    public int lengthOfLastWord1(String s) {
        s = s.trim();
        String[] res = s.split(" ");
        if (s.length()!=0) {
            return res[res.length - 1].length();
        }else {
            return 0;
        }
    }
    public int lengthOfLastWord(String s) {
        s = s.trim();
        int len = s.length();
        int lastSpace = s.lastIndexOf(' ');
        return len - lastSpace - 1;
    }
}
