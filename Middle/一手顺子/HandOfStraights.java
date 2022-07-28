package Middle.一手顺子;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class HandOfStraights {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        if(hand.length<groupSize) return false;

        Arrays.sort(hand);
        int len = hand.length;
        while (len>0){
            int cur = -1;
            int num = 0;
            if (len<groupSize){
                return false;
            }
            for (int i=0; i<hand.length; i++){
                if (hand[i]==-1){
                    continue;
                }
                if (cur==-1 || hand[i]==cur+1){
                    cur = hand[i];
                    System.out.println(cur);
                    len--;
                    hand[i]=-1;
                    num++;
                } else if (hand[i]>cur+1){
                    return false;
                }
                if (len==0 || num==groupSize){
                    break;
                }
            }
        }
        return true;
    }

    // 哈希表+排序树：从最小的牌开始取，然后从后连成顺子
    public boolean isNStraightHand2(int[] hand, int groupSize) {
        if (hand.length<groupSize) return false;
        if (groupSize==1) return true;
        if (hand.length%groupSize!=0) return false;

        TreeSet<Integer> set = new TreeSet<>();
        Map<Integer, Integer> map = new HashMap<>();

        for (int cur:hand){
            map.put(cur, map.getOrDefault(cur,0)+1);
            set.add(cur);
        }

        while (!set.isEmpty()){
            int cur = set.first();
            if (!map.containsKey(cur)){  // 之前的顺子可能已经把这个数字删除掉了
                set.pollFirst();
                continue;
            }
            map.put(cur, map.get(cur) - 1);
            for (int i=cur+1; i<cur+groupSize; i++) {
                if (map.containsKey(i)) {
                    map.put(i, map.get(i) - 1);
                    if (map.get(i) == 0) map.remove(i);
                }else {
                    return false;
                }
            }
            if (map.get(cur)==0) set.pollFirst();
        }

        return true;
    }

    // 哈希表+排序
    public boolean isNStraightHand3(int[] hand, int groupSize) {
        if (groupSize==1) return true;
        if (hand.length%groupSize!=0) return false;

        Map<Integer, Integer> map = new HashMap<>();
        Arrays.sort(hand);
        for (int x:hand){
            map.put(x, map.getOrDefault(x, 0)+1);
        }

        for (int x:hand){
            if (!map.containsKey(x)){
                continue;
            }
            for (int j = 0; j < groupSize; j++) {
                int num = x + j;
                if (!map.containsKey(num)) {
                    return false;
                }
                map.put(num, map.get(num) - 1);
                if (map.get(num) == 0) {
                    map.remove(num);
                }
            }
        }

        return true;
    }
}
