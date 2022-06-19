package Middle.从英语中重建数字;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ReconstructOriginalDigitsFromEnglish {
    /*
        0：zero
        1：one
        2：two
        3：three
        4：four
        5：five
        6：six
        7：seven
        8：eight
        9：nine
        出现过的字母：
        z   0
        e   0 1 3*2 5 7*2 8 9
        r   0 3 4
        o   0 1 2 4
        n   1 7 9*2
        t   2 3 8
        w   2
        h   3 8
        f   4 5
        u   4
        i   5 6 8 9
        v   5 7
        s   6 7
        x   6
        g   8
        其中：0 2 4 6 8 --> z w u x g，只出现了一次，所以对应字母出现了几次，那么这几个数字就出现了几次
        从h可以得到3出现的次数
        从s可以得到7出现的次数
        从v可以得到5出现的次数
        从o可以得到1出现的次数
        从i可以得到9出现的次数
        用HashMap记录每个字母出现的次数，然后依次取
     */
    public String originalDigits(String s) {
        int[] ans = new int[10];  // 记录十个数字出现的次数
        HashMap<Character, Integer> map = new HashMap<>();
        char[] chs = s.toCharArray();
        for (char c:chs){
            map.put(c, map.getOrDefault(c,0)+1);
        }
        ans[0] = map.getOrDefault('z',0);
        ans[2] = map.getOrDefault('w',0);
        ans[4] = map.getOrDefault('u',0);
        ans[6] = map.getOrDefault('x',0);
        ans[8] = map.getOrDefault('g',0);
        ans[3] = map.getOrDefault('h',0)-ans[8];
        ans[7] = map.getOrDefault('s',0)-ans[6];
        ans[5] = map.getOrDefault('v',0)-ans[7];
        ans[1] = map.getOrDefault('o',0)-(ans[0]+ans[2]+ans[4]);
        ans[9] = map.getOrDefault('i',0)-(ans[5]+ans[6]+ans[8]);
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<10; i++){
            if (ans[i]!=0){
                sb.append(String.valueOf(i).repeat(ans[i]));
            }
        }
        return sb.toString();
    }

    // 大佬解题
    /*
        用一个字符数组把字符串里面的字母全部记录下来，按照26的顺序
        然后把 0 2 4 6 8 的补上，因为他们是独一无二的，然后再计算 1 3 5 7 9
     */
    public String originalDigits2(String s) {
        char[] chs = new char[26];
        for (char c: s.toCharArray()){
            chs[c-'a']++;
        }
        List<Character> list = Arrays.asList('z','w','u','x','g');
        int[] out = new int[10];
        // 存储完 0 2 4 6 8
        for (int i=0, flag=0; i<5; i++,flag+=2){
            out[flag] = chs[list.get(i)-'a'];
        }

        // h -- 3 8
        out[3] = chs['h'-'a']-out[8];
        // s -- 6 7
        out[7] = chs['s'-'a']-out[6];
        // v -- 5 7
        out[5] = chs['v'-'a']-out[7];
        // o -- 0 1 2 4
        out[1] = chs['o'-'a']-out[0]-out[2]-out[4];
        // i -- 5 6 8 9
        out[9] = chs['i'-'a']-out[5]-out[6]-out[8];

        StringBuilder sb = new StringBuilder();
        for (int i=0; i<10; i++){
            sb.append(String.valueOf(i).repeat(out[i]));
        }

        return sb.toString();
    }
}
