package Easy.删除回文子序列;

public class RemovePalindromicSubsequences {
    // 只有a和b两种字母
    public int removePalindromeSub(String s) {
        for (int i=0; i<s.length()/2; i++){
            if (s.charAt(i)!=s.charAt(s.length()-i-1)){
                return 2;
            }
        }
        return 1;
    }
}
