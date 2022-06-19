package Easy.键盘行;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().findWords(new String[]{
//                "Hello","Alaska","Dad","Peace"
                "emk"
        })));
    }
    public String[] findWords(String[] words) {
        int[] wordLine = new int[26];  // 记录每个字母出现的行数
        String[] characters = new String[]{"qwertyuiop", "asdfghjkl", "zxcvbnm"};
        for (int i=0; i<3; i++){ // 记录完毕
            char[] temp = characters[i].toCharArray();
            for (int j=0; j<temp.length; j++){
                wordLine[temp[j]-'a'] = i+1;
            }
        }

        StringBuilder record = new StringBuilder();
        for (int i=0; i<words.length; i++){
            String curStr = words[i].toLowerCase();
            int cur = wordLine[curStr.charAt(0)-'a'];  // 记录当前单词的首字母所在的行号
            boolean flag = true;
            for (int j=1; j<words[i].length(); j++){
                if (wordLine[curStr.charAt(j)-'a']!=cur) {  // 不在同一行
                    flag = false;
                    break;
                }
            }
            if (flag) {
                record.append(words[i]).append("-");
            }
        }
        if (record.length()==0) {
            return new String[0];
        }
        return record.toString().split("-");
    }
}
