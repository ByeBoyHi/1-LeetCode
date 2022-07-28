package Daily_Topic.密钥格式化;

import java.util.Arrays;
import java.util.Locale;

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().licenseKeyFormatting4("--a-a--a--a--", 2));
    }

    // 18ms  39.2M
    public String licenseKeyFormatting(String s, int k) {
        String[] split = s.toUpperCase().split("-");
        StringBuilder sb = new StringBuilder();
        for (String str : split) {
            sb.append(str);
        }
        int len = sb.length();
        StringBuilder res = new StringBuilder();
        for (int i = len - 1; i >= 0; i--) {
            if ((len - 1 - i) % k == 0 && i != (len - 1)) {
                res.append("-");
            }
            res.append(sb.charAt(i));
        }
        return res.reverse().toString();
    }

    // 48ms  38.9M
    // insert效率极低，因为是把元素依次后移，还不如append
    public String licenseKeyFormatting2(String s, int k) {
        StringBuilder sb = new StringBuilder(s.length());
        String[] strs = s.toUpperCase().split("-");
        for (String str : strs) {
            sb.append(str);
        }
        int len = sb.length();
        for (int i = len - k; i > 0; i -= k) {
            sb.insert(i, "-");
        }
        return sb.toString();
    }

    // 官解：数学法
    public String licenseKeyFormatting3(String s, int k) {
        StringBuilder ans = new StringBuilder(s.length());
        int cur = 0;
        int len = s.length();
        for (int i = len - 1; i >= 0; i--) {
            if (s.charAt(i) != '-') {  // 如果不是短横线，就加进去，然后cur记录加了多少个元素
                ans.append(s.charAt(i));
                cur++;
                // 如果已经加了 nk 个元素，那么加完这个元素要再加一个短横线。
                // 最后一个元素的位置不加。
                if (cur % k == 0) {
                    ans.append("-");
                }
            }
        }
        String res = ans.reverse().toString();
        if (res.startsWith("-")) {
            res = res.replaceFirst("-", "");
        }
        return res.toUpperCase();
    }

    // 2ms 大神
    /*
        思路：
            1. 将字符串所有字符用一个字符数组存储。
            2. 然后用一个变量来记录除了'-'之外的其他字符的数量。
            3. 用这个字符长度对要求长度k取余，如果等于0，那么还需要 len/k-1 个空间存 '-'；否则需要 len/k 个空间。
            4. 新建立一个字符数组用来存储格式化的密钥。
            5. 返回的是直接字符数组存入 new String() 里面就好。
    * */
    public String licenseKeyFormatting4(String s, int k) {
        char[] lines = s.toCharArray();
        int n = lines.length;
        int len = n;
        for (char c:lines){
            if (c=='-')
                len--;
        }
        if (len==0) return "";

        if (len%k==0){
            len = len + len/k-1;
        }else{
            len = len + len/k;
        }

        char[] ans = new char[len];
        int j = len-1;
        // 最外层循环多-1，导致了里面的有的元素被跳过了
        for (int i=n-1; i>=0; ){
            for (int l=0; l<k; l++){
                while ( i>=0 && lines[i]=='-')
                    i--;
                // res[j--] = (line[i] & 64) == 0 ? line[i] : (char) (line[i] & 0x5f);
                // 避免上面跳过之后，已经字符串结束了
                if (i<0) break;
                ans[j--] = lines[i--];
            }
            if (i<0 || j<0)
                break;
            ans[j--] = '-';
        }
        return new String(ans).toUpperCase();
    }
}