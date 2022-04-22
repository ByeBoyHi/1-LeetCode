package Middle.适合打劫银行的日子;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GoodDay {
    public List<Integer> goodDaysToRobBank(int[] security, int time) {
        List<Integer> ans = new ArrayList<>();
        if (security.length<=time) return ans;

        int len = security.length;
        int[] nonIncre = new int[len-time];  // 后time天不用记录是否递增
        int[] nonDecre = new int[len-time];  // 前time天不用记录是否递减
        nonIncre[0] = 1;
        nonDecre[len-time-1] = 1;
        for (int i=1; i<len-time; i++){  // 1~len-time
            if (security[i]<=security[i-1]){  // 非递增
                nonIncre[i] = nonIncre[i-1]+1;
            }else {
                nonIncre[i] = 1;
            }
        }
        for (int i=len-2; i>time-1; i--){  // time~len
            if (security[i]<=security[i+1]){  // 非递减
                nonDecre[i-time] = nonDecre[i-time+1]+1;
            }else {
                nonDecre[i-time] = 1;
            }
        }
        for (int i=time; i<len-time; i++){
            if (nonIncre[i]>=time  // 前time天都是非递增的
                    && nonDecre[i-time]>=time  // 后time天都是非递减的
                    &&  security[i]<Math.max(security[i-1], security[i+1]) // 并且当天是要小于两边的
            ){
                ans.add(i);
            }
        }
        System.out.println(Arrays.toString(nonIncre));
        System.out.println(Arrays.toString(nonDecre));
        return ans;
    }
}
