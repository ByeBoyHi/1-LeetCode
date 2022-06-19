package Easy.句子中的有效单词数;

import java.util.HashSet;
import java.util.Set;

public class CountOfWords {

    static Set<Character> set = new HashSet<>();
    static{
        set.add('!');
        set.add('.');
        set.add(',');
    }

    public int countValidWords(String sentence) {
        int ans = 0;
        String[] ss = sentence.split(" ");
        if (ss.length==0) return 0;
        for (String s : ss){
            s = s.trim();
            if (s.length()==0) continue;
            char[] chs = s.toCharArray();
            boolean flag = false; // 记录'-'是否出现
            boolean ok = true;  // 记录当前Token是否合理
            for (int i=0; i<chs.length; i++){
                if (  // 出现不合理字符，直接推出循环，标记不合理token
                        (i==0 && i!=chs.length-1 && (chs[0]<'a' || chs[0]>'z'))  // 第一个字符必须是字母
                    ||
                        (chs[i]>='0' && chs[i]<='9')  // 不能有数字
                    ||
                        (set.contains(chs[i]) && i!=chs.length-1) // 标点符号没有在末尾
                    ||
                        // 出现第二个'-' 或者 '-'出现在末尾 或者 下一个不是字母
                        (chs[i]=='-' && (flag || i==chs.length-1 || (chs[i+1]<'a' || chs[i+1]>'z')))
                ) {
                    ok = false;
                    break;
                }
                // 出现第一个 - 且在合理位置
                if (chs[i]=='-' && !flag) flag = true;
            }
            if (ok) ans++;
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println((int)'a');
        System.out.println((int)'z');
    }
}
