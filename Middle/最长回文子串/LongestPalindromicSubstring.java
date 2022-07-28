package Middle.最长回文子串;

public class LongestPalindromicSubstring {

    public static void main(String[] args) {
        System.out.println(new LongestPalindromicSubstring().longestPalindrome(
                "aacabdkacaa"
        ));
    }

    // 公共子串的DP
    /*
        求一个字符串的回文，那么倒转过来也是一样的。
        我们可以求原字符串和逆转字符串的公共子串即可。
        根据图，在进行比较的时候，如果当前字符相等，那么 a[i][j] = a[i-1][j-1]+1
        当i==0或者j==0的时候，a[i][j]=1
        你会根据每一次结束，得到一个长度，进行最大长度的更新
        返回的字符串就是：结束位置-最大长度+1
     */
    public String longestPalindrome(String s) {
        if (s.equals("")){
            return "";
        }
        int len = s.length();
        String reverse = new StringBuilder(s).reverse().toString();
        int[][] dp = new int[len][len];
        int maxLen = 0;
        int maxEnd = 0;
        for (int i=0; i<len; i++){  // s
            for (int j=0; j<len; j++){  // reverse
                if (s.charAt(i)==reverse.charAt(j)){
                    if (i==0 || j==0){
                        dp[i][j] = 1;
                    }else {
                        dp[i][j] = dp[i-1][j-1]+1;
                    }
                }
                if (dp[i][j]>maxLen){  // 因为我们找的是公共子串，但是这个子串在原来的里面是否位置也倒置？
                    int beforeRev = len - 1 - j;
                    if (beforeRev + dp[i][j] - 1 == i) { //判断下标是否对应
                        maxLen = dp[i][j];
                        maxEnd = i;
                    }
                }
            }
        }
        return s.substring(maxEnd-maxLen+1, maxEnd+1);
    }

    // 上面方法的空间优化
    public String longestPalindrome2(String s) {
        if (s.equals("")){
            return "";
        }
        int len = s.length();
        String reverse = new StringBuilder(s).reverse().toString();
        int[] dp = new int[len];
        int maxLen = 0;
        int maxEnd = 0;
        for (int i=0; i<len; i++){  // s
            for (int j=len-1; j>=0; j--){  // reverse
                if (s.charAt(i)==reverse.charAt(j)){
                    if (i==0 || j==0){
                        dp[j] = 1;
                    }else {
                        dp[j] = dp[j-1]+1;
                    }
                }else { // 这里回文断掉
                    dp[j] = 0;
                }
                if (dp[j]>maxLen){  // 因为我们找的是公共子串，但是这个子串在原来的里面是否位置也倒置？
                    int beforeRev = len-1-j;  // 计算出当前字符的逆转字符位置
                    if (beforeRev+dp[j]-1==i) {  // 判断一下当前字符正常逆转后是否是i
                        maxLen = dp[j];
                        maxEnd = i;
                    }
                }
            }
        }
        return s.substring(maxEnd-maxLen+1, maxEnd+1);
    }

    // 最快的算法
    int len;
    int start;
    int end;
    char[] chs;
    public String longestPalindrome3(String s) {
        if (s.equals("")){
            return "";
        }
        len = s.length();
        start = 0;
        end = 0;
        chs = s.toCharArray();

        process(0);

        return s.substring(start, end+1);
    }

    public void process(int i){
        if (i>len || 2*(len-i)<(end-start)) return;

        int curE = i;
        int curS = i;

        // 这是为了判断一连串的相等字符串，这些字符串只能自身回文
        while (curE<len-1 && chs[curE]==chs[curE+1]) curE++;


        i = curE;  // 这是走到了连等字符串结束的位置，便于下一次递归，跳过这些重复的字符串
        /*
            比如： bcbaaaaabdda
            从第一个a就能得到 aaaaa。
            然后以 aaaaa 向两边扩张判断镜像
            第二个a就没必要了，而且从第二个a开始的话，就会漏掉第一个a，得到的回文必然只能是 aaaa，没有第一个a长
         */


        // 这是判断以上面结束后的串为中心，两边是否镜像
        while (curS>0 && curE<len-1 && chs[curS-1]==chs[curE+1]){
            curE++;
            curS--;
        }

        // 如果新的到的回文串长度更长，那么更新起点和终点
        if (curE-curS>end-start){
            start = curS;
            end = curE;
        }

        process(i+1);
    }
}
