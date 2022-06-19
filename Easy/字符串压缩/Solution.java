package Easy.字符串压缩;

/**
 * @author 唐
 * @date 2020/10/13 0013
 * @time 10:30
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(compressString("aaabbccccccaa"));

    }

    public static String compressString(String S) {
        if (S.length()==0){
            return "";
        }
        if (S == null){
            return null;
        }
        if (S.length()==2 || S.length()==1){
            return S;
        }
        int len = S.length();
        StringBuilder res = new StringBuilder();

        char cur = S.charAt(0);
        int num = 1;

        for (int i = 1; i < S.length(); i++) {
            if (S.charAt(i) != cur) {
                res.append(cur).append(num);
                cur = S.charAt(i);
                num = 1;
            } else {
                num++;
            }
            // 如果循环进行到一般的时候，整合的字符串长度已经大于等于原来字符串的长度，则后面就没必要进行了
            if (res.length()>=len){
                return S;
            }
        }
        res.append(cur).append(num);

        if (S.length()<=res.length()){
            return S;
        }

        return res.toString();
    }
}
