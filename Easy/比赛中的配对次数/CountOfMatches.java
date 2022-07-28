package Easy.比赛中的配对次数;

public class CountOfMatches {
    public int numberOfMatches(int n) {
        int ans = 0;
        while(n>1){
            ans+=n/2;
            if ((n&1)==1){
                n++;
            }
            n=n/2;
        }
        return ans;

        // n支队伍决赛，只会剩下一支队伍
        // 其他n-1支队伍都会输掉一场比赛，所以是n-1次匹配
        // return n-1
    }
}
