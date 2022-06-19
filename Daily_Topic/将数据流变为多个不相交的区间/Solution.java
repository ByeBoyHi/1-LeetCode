package Daily_Topic.将数据流变为多个不相交的区间;

import java.util.*;

public class Solution {
    public static void main(String[] args) {

    }
}

class SummaryRanges {

    List<Integer> arr = new ArrayList<>();

    public SummaryRanges() {}

    public void addNum(int val) {
        arr.add(val);
    }

    public int[][] getIntervals() {
        List<int[]> ans = new ArrayList<>();
        int len = arr.size()-1;

        // 只有一个元素
        if (len == 0) {
            ans.add(new int[]{arr.get(0), arr.get(0)});
        } else {
            // 排序：只要挨着的两个元素相邻，就可以构成一个区间
            arr.sort(Comparator.comparingInt(a -> a));
            int start;
            int end;
            for (int i = 0; i < len; i++) {
                start = i;
                while (i < len && (arr.get(i + 1) - arr.get(i) <= 1))
                    i++;
                end = i;
                ans.add(new int[]{arr.get(start), arr.get(end)});
                if (i!=len
                        && len - i <= 2
                        && arr.get(len)-arr.get(i+1)<=1) {
                    ans.add(new int[]{arr.get(i + 1), arr.get(len)});
                    break;
                }
            }
        }

        int[][] res = new int[len+1][];
        int i = 0;
        for (int[] cur : ans){
            res[i++] = cur;
        }

        return res;
    }
}

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(val);
 * int[][] param_2 = obj.getIntervals();
 */