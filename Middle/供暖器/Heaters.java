package Middle.供暖器;

import java.util.Arrays;

public class Heaters {
    public int findRadius(int[] houses, int[] heaters) {
        if (heaters.length == 1) {
            return Math.max(Math.abs(houses[0] - heaters[0]),
                    Math.abs(houses[houses.length - 1] - heaters[0]));
        }
        int min = Integer.MIN_VALUE;
        int len = houses.length;
        int hlen = heaters.length;
        int idx = 0;

        Arrays.sort(houses);
        Arrays.sort(heaters);

        for (int i = 0; i < len; ) {
            if (idx+1<hlen &&
                    houses[i]>heaters[idx] + (heaters[idx + 1] - heaters[idx] - 1) / 2){
                idx++;
            }else {
                min = Math.max(min, Math.abs(heaters[idx] - houses[i]));
                i++;
            }
        }
        return min;
    }
}
