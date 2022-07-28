package Easy.高度检查器;

import java.util.Arrays;
import java.util.OptionalInt;

public class HeightChecker {

    /**
     * 时间复杂度：O(nlogn)
     * 快排花费时间。
     */
    public int heightChecker(int[] heights) {
        int[] clone = Arrays.copyOf(heights, heights.length);
        Arrays.sort(clone);
        int ans=0;
        for (int i=0; i<heights.length; i++){
            if (clone[i]!=heights[i]){
                ans++;
            }
        }
        return ans;
    }

    // 计数排序
    /**
     * 用一个数组，存储所有数字出现的次数
     * 相同的数字应该连续出现，才能保证是不递减的
     *
     * 时间复杂度：O(n+C)
     * n->数组的长度
     * C->找到的最大值
     */
    public int heightChecker1(int[] heights){
        int m = 0;  // 找到最大值
        // 这个流处理看不到时间复杂度
        OptionalInt anInt = Arrays.stream(heights).max();
        if (anInt.isPresent()){
            m = anInt.getAsInt();
        }
        int[] cnt = new int[m+1];
        // 计数
        for (int h: heights){
            cnt[h]++;
        }

        int idx = 0;
        int ans = 0;
        for (int i=0; i<=m; i++){  // 判断每个数字
            for (int j=1; j<=cnt[i]; j++){  // 判断相同的数字是否有这么多连续挨着的
                if (heights[idx]!=i){  // 每当出现一个不连续挨着的，说明就有一个数字的下标错了
                    ans++;
                }
                idx++;
            }
        }

        return ans;
    }

}
