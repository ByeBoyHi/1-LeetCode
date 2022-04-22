package Middle.最小时间差;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MinimumTimeDifference {
    public int findMinDifference(List<String> timePoints) {
        int[][] time = new int[timePoints.size()][2];
        int i = 0;
        for (String s : timePoints) {
            String[] tmp = s.split(":");
            time[i][0] = Integer.parseInt(tmp[0]);
            time[i][1] = Integer.parseInt(tmp[1]);
            i++;
        }
        Arrays.sort(time, (t1, t2) -> {
            if (t1[0] == t2[0]) {
                return t1[1] - t2[1];
            }
            return t1[0] - t2[0];
        });

        for (int[] ints: time){
            System.out.println(ints[0]+":"+ints[1]);
        }

        int min = Integer.MAX_VALUE;
        for (int k = 1; k < time.length; k++) {
            int curHour = time[k][0];
            int curMinu = time[k][1];
            int preHour = time[k - 1][0];
            int preMinu = time[k - 1][1];
            if (curHour == preHour && curMinu == preMinu) {
                return 0;
            }

            int hour = 0;
            int minu = 60-preMinu+curMinu;
            if (curHour!=preHour){
                hour = curHour-preHour;
                if (curMinu<preMinu) hour--;
            }
            min = Math.min(min, hour*60 + minu);
        }

        int hour = 23-time[time.length-1][0]+time[0][0];
        int minu = 60-time[time.length-1][1]+time[0][1];
        min = Math.min(min, hour*60+minu);

        return min;
    }

    public int findMinDifference2(List<String> timePoints) {
        int n = timePoints.size();
        // 因为24*60==1440，那么如果数组的长度超过1440，必然会有重复的时间，直接返回0
        // 抽屉原理：5双袜子放进四个抽屉里，每个抽屉必须有一个袜子，必然有一个抽屉里面有两双袜子
        if (n > 1440) {
            return 0;
        }
        Collections.sort(timePoints);
        int ans = Integer.MAX_VALUE;
        int t0Minutes = getMinutes(timePoints.get(0));
        int preMinutes = t0Minutes;
        for (int i = 1; i < n; ++i) {
            int minutes = getMinutes(timePoints.get(i));
            ans = Math.min(ans, minutes - preMinutes); // 相邻时间的时间差
            preMinutes = minutes;
        }
        ans = Math.min(ans, t0Minutes + 1440 - preMinutes); // 首尾时间的时间差
        return ans;
    }

    public int getMinutes(String t) {
        return ((t.charAt(0) - '0') * 10 + (t.charAt(1) - '0')) * 60 + (t.charAt(3) - '0') * 10 + (t.charAt(4) - '0');
    }
}
