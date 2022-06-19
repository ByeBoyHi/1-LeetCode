package Middle.最大单词长度乘积;


import java.util.*;

public class MaximumProductOfWordLengths {

    public static void main(String[] args) {
        System.out.println(maxProduct(new String[]{
//                "abcw","baz","foo","bar","xtfn","abcdef"
                "a", "ab", "abc", "d", "cd", "bcd", "abcd"
        }));
    }

    /**
     * 暴力破解思路：
     * 遍历每一对单词，看这一对单词是否有公共字母，如果没有，计算长度乘积，判断是否更大
     */
    public static int maxProduct(String[] words) {
        int ans = 0;
        for (int i = 0; i < words.length; i++) {
            HashSet<Character> set = new HashSet<>();
            String wi = words[i];

            for (int m = 0; m < wi.length(); m++) {
                set.add(wi.charAt(m));
            }
            for (int j = 0; j < words.length; j++) {
                String wj = words[j];
                if (i == j) {
                    continue;
                }
                int k = 0;
                for (; k < wj.length(); k++) {
                    if (set.contains(wj.charAt(k))) {
                        break;
                    }
                }
                if (k == wj.length()) {
                    if (ans < wi.length() * wj.length()) {
                        ans = wi.length() * wj.length();
                    }
                }
            }
        }

        return ans;
    }


    /**
     * 官解位运算思路：
     * 1. 因为单词只有小写字母，即26位
     * 2. 那么外面可以对每个字母进行位运算存储，用0~25位进行存储
     * 3. 某个字母出现过，那么第i位就是1，否则就是0
     * 4. 判断两个字母是否有交集，只需要对他俩的二进制进行与运算，如果为0，说明没有一个1碰上面，也就是没有交集
     * 5. 字母i-'a'，就是他所在的二进制位置，然后1左移，再和原来的二进制码进行或运算，就可以更新为这个值，也不影响原来的值是1还是0
     */
    public static int maxProduct2(String[] words) {
        int len = words.length;
        int[] mask = new int[len];
        int ans = 0;
        for (int i=0; i<len; i++){
            int n = words[i].length();
            for (int j=0; j<n; j++){
                mask[i] |= (1<<(words[i].charAt(j)-'a'));
            }
        }

        for (int i=0; i<len; i++){
            for (int j=0; j<len; j++){
                if (i!=j && (mask[i] & mask[j])==0){
                    ans = Math.max(ans, words[i].length() * words[j].length());
                }
            }
        }

        return ans;
    }
}