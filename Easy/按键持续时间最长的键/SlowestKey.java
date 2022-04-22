package Easy.按键持续时间最长的键;

public class SlowestKey {
    public char slowestKey(int[] releaseTimes, String keysPressed) {
        int len = releaseTimes[0];
        char[] chs = keysPressed.toCharArray();
        char ans = chs[0];
        int n = chs.length;
        for (int i=1; i<n; i++){
            if (releaseTimes[i]-releaseTimes[i-1]>len
            ||
                (releaseTimes[i]-releaseTimes[i-1]==len && ans<chs[i])){
                len = releaseTimes[i]-releaseTimes[i-1];
                ans = chs[i];
            }
        }
        return ans;
    }
}
