package Easy.最长特殊序列1;

public class LongestUncommonSubsequence1 {
    public int findLUSlength(String a, String b) {
        if(a.equals(b)) return -1;
        return Math.min(a.length(), b.length());
    }
}
