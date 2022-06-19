package Easy.分糖果;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().distributeCandies(new int[]{
                5,6,3,4,11,2,3,4,5,6,7,8
        }));
    }
    /**
     * 给弟弟妹妹分糖果，要求妹妹获得的糖果是种类不同的，且不超过半数，返回妹妹能获得的糖果数
     * @param candyType  传入的糖果类型数组，不同的数字代表不同的糖果
     * @return           返回妹妹能够获得的糖果数
     */
    public int distributeCandies(int[] candyType) {
        int cnt = 1;
        Arrays.sort(candyType);
        System.out.println(Arrays.toString(candyType));
        for( int i=1; i<candyType.length; i++){
            if (candyType[i]!=candyType[i-1]){
                cnt++;
            }
        }
        return Math.min(cnt, (candyType.length >> 1));
    }

    // 哈希表
    public int distributeCandies2(int[] candyType){
        Set<Integer> set = new HashSet<>();
        for (int candy: candyType){
            set.add(candy);
        }
        return Math.min(set.size(), (candyType.length>>1));
    }

    // 数组
    public int distributeCandies3(int[] candyType){
        int[] record = new int[200005];
        int ans = 0;
        for (int candy: candyType){
            if (record[candy+100000]==0){
                record[candy+100000]=1;
                ans++;
            }
        }
        return Math.min(ans, candyType.length>>1);
    }
}
