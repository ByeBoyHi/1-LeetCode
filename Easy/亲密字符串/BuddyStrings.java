package Easy.亲密字符串;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BuddyStrings {

    public static void main(String[] args) {
        System.out.println(new BuddyStrings().buddyStrings2("abcaa", "abcbb"));
    }

    /*
        亲密字符串：交换s中的某两个字符，使得s==goal，那么就称他们为亲密字符串
        也就是s和goal的字符串一一对比，只要有两个字符不相等，其他都相等即可，且这两个不同的字母必须彼此相同
        或者全部相等的情况下，他们内部有两个及以上相同的字母
     */
    public boolean buddyStrings(String s, String goal) {
        int ans = 0;
        List<char[]> list = new ArrayList<>();
        list.add(new char[s.length()]);
        list.add(new char[goal.length()]);
        char[] chs = s.toCharArray();
        char[] gos = goal.toCharArray();

        for (int i = 0; i < s.length(); i++) {
            if (chs[i] != gos[i]) {
                list.get(0)[ans] = chs[i];
                list.get(1)[ans] = gos[i];
                ans++;
            }
        }

        if (ans == 2){ // 刚好有两个字符不同
            char[] c1 = list.get(0);
            char[] c2 = list.get(1);
            Arrays.sort(c1);
            Arrays.sort(c2);
            // 这里排序之后，空串提前，我们的信息串往后走了，所以需要从后往前走
            for (int i=c1.length-1; i>=0; i--){
                if (c1[i]!=c2[i]){
                    return false;
                }
            }
            return true;
        }
        if (ans == 1 || ans > 2) return false;  // 有超过两个字符或者只有一个字符不同

        // 全部相同的情况下

        Arrays.sort(chs);
        for (int i = 0; i < chs.length - 1; i++) {
            if (chs[i] == chs[i + 1]) {  // 只要有两个字符是相同的，就认为是亲密的
                return true;
            }
        }

        return false;
    }

    // 方法二：直接排序判断，是全部相等，还是只有两个不等且这两个彼此相等
    public boolean buddyStrings2(String s, String goal) {
        char[] cs = s.toCharArray();
        char[] gs = goal.toCharArray();
        int[][] record = new int[2][2];
        int len = cs.length;
        int ans = 0;
        for (int i=0; i<len; i++){
            if (cs[i]!=gs[i]){
                if (ans==2){ // 这是第三个不等的字符了
                    return false;
                }
                record[0][ans] = cs[i];
                record[1][ans] = gs[i];
                ans++;
            }
        }

        if (ans==2){  // 所有字符全部相等
            Arrays.sort(record[0]);
            Arrays.sort(record[1]);
            for (int i=0; i<2; i++){

                if (record[0][i]!=record[1][i]){
                    return false;
                }
            }
            return true;
        }

        if (ans==0){
            Arrays.sort(cs);
            for (int i=0; i<len-1; i++){
                if (cs[i]==cs[i+1]){
                    return true;
                }
            }
        }

        return false;
    }

}
