package Easy.复写零;

import java.util.Arrays;

public class DuplicateZeros {

    public static void main(String[] args) {
        int[] arr = new int[]{
//                1, 0, 2, 3, 0, 4, 5, 0
//                0, 1, 7, 6, 0, 2, 0, 7
                0, 0, 0, 0, 0, 0, 0
//                8,4,5,0,0,0,0,7
        };
        duplicateZeros2(arr);
        System.out.println(Arrays.toString(arr));
    }

    // 额外空间O(n)
    public static void duplicateZeros(int[] arr) {
        int[] res = new int[arr.length];
        int idx = 0;
        for (int i : arr) {
            if (idx == res.length) break;
            res[idx++] = i;
            if (idx < res.length && i == 0) {
                res[idx++] = 0;
            }
        }
        System.arraycopy(res, 0, arr, 0, arr.length);
    }

    // 原地修改

    /**
     * 思路：
     *    用两个指针进行记录，一个记录我们数据的读入位置 i，一个记录最终位置 j。
     *    当 j 走到最后的时候，说明我们整个数据读入已经结束。
     *    当遇到一个零的时候，j 就多走一个，然后再一起后移。
     *    当遇到一连串的零的时候，可能最后会出现 j 多走了两格的情况，那么此时就需要 j 走到 n-2，然后把 n-1的位置赋值为零。（因为零才多走了一格）
     *    否则 j 就来到 n-1 的位置。
     *    接下来回到赋值环节，由数据位 i 来控制是否退出循环，当全部赋值结束后，就退出，且遇到零的话多赋值一位。
     */
    public static void duplicateZeros2(int[] arr) {
        if (arr.length == 0 || arr.length == 1) return;

        // 两个指针都往右移动，遇到零的话，j多移动一格，表示真实数组需要的长度
        // i 正常移动，由j 来控制移动长度，最终i会停留在真实数组的最后一个位置
        int i = 0;
        int j = 0;
        while (j < arr.length) {
            if (arr[i] == 0) j++;
            i++;
            j++;
        }
        if (j==arr.length+1){
            arr[j-2] = 0;
            j = arr.length - 2;
            i--;
        }else {
            j = arr.length-1;
        }
        i--;
        while (i >= 0) {
            arr[j] = arr[i];
            if (arr[i] == 0) {
                arr[--j] = 0;
            }
            i--;
            j--;
        }

    }
}
