package Middle.二倍数对数组;

import java.util.Arrays;
import java.util.HashMap;

public class TwoTimeArr {
    public boolean canReorderDoubled(int[] arr) {
        Arrays.sort(arr);
        HashMap<Integer,Integer> set = new HashMap<>();
        for (int a : arr) {
            if (set.containsKey(2 * a)) {
                if (set.get(2*a)-1==0){
                    set.remove(2*a);
                }else {
                    set.put(2*a, set.get(2*a)-1);
                }
            } else if (a % 2 == 0 && set.containsKey(a / 2)) {
                if (set.get(a / 2)-1==0){
                    set.remove(a / 2);
                }else {
                    set.put(a / 2, set.get(a / 2)-1);
                }
            }else {
                set.put(a, set.getOrDefault(a,0)+1);
            }
        }
        return set.size()==0;
    }
}
