package Easy.ArrayIdxTransfer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {

    public static void main(String[] args) {
        int[] arr = {
                37,12,28,9,100,56,80,5,12
        };
        System.out.println(Arrays.toString(arrayRankTransform(arr)));
    }

    public static int[] arrayRankTransform(int[] arr) {
        int[] newArr = new int[arr.length];
        System.arraycopy(arr, 0, newArr, 0, arr.length);
        Arrays.sort(newArr);
        Map<Integer, Integer> map = new HashMap<>();  // stored new index
        for (int j : newArr) {
            if (!map.containsKey(j))
                map.put(j, map.size() + 1);
        }

        int[] ret = new int[arr.length];
        for (int i=0; i<arr.length; i++){
            ret[i] = map.get(arr[i]);
        }
        return ret;
    }
}
