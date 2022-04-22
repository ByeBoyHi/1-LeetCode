package Easy.唯一元素的和;

import java.util.HashMap;
import java.util.Map;

public class UniqueSum {
    public int sumOfUnique(int[] nums) {
        if (nums.length==1) return nums[0];
        Map<Integer, Integer> map = new HashMap<>();
        for (int n: nums){
            map.put(n, map.getOrDefault(n, 0)+1);
        }
        int ans = 0;
        for (int x: map.keySet()){
            if (map.get(x)==1) ans+=x;
        }
        return ans;
    }
}
