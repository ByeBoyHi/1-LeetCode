package Easy.连续字符;

public class ConsecutiveCharacters {
    public int maxPower(String s) {
        if(s.length()==1){
            return 1;
        }
        int ans = 1;
        int cur = 1;
        for (int i=1; i<s.length(); i++){
            if (s.charAt(i)==s.charAt(i-1)){
                cur++;
            }else {
                ans = Math.max(ans, cur);
                cur = 1;
            }
        }
        // 最后一个字符如果相等，没有更新
        if (s.charAt(s.length()-1)==s.charAt(s.length()-2)){
            ans = Math.max(ans, cur);
        }
        return ans;
    }
}
