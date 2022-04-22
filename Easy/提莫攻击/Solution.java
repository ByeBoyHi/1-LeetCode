package Easy.提莫攻击;

public class Solution {
    // 每一次中毒，都会增加duration秒的中毒时间
    // 如果中毒前后时间差小于duration秒，那么中毒的总时间还需要减去(duration-他俩的时间差)
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int total = 0;
        for (int i=0; i<timeSeries.length; i++){
            total+=duration;
        }

        for (int i=1; i<timeSeries.length; i++){
            if (timeSeries[i]-timeSeries[i-1]<duration){
                total-=(duration-timeSeries[i]+timeSeries[i-1]);
            }
        }

        return total;
    }
}
