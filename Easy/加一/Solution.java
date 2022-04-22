package Easy.加一;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().plusOne1(new int[]{
                9, 9, 9, 9, 9, 9, 9
        })));
    }
    /**
     * 把一个数字拆分成一个数组进行存储。
     * 从后往前加，需要考虑进位。
     */
    public int[] plusOne(int[] digits) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < digits.length; i++) {
            list.add(digits[i]);
        }
        if ((1 + list.get(list.size() - 1)) == 10) {
            list.set(list.size() - 1, 0);
            if (list.size()>1) {
                list.set(list.size() - 2, list.get(list.size() - 2) + 1);
                for (int i = list.size() - 2; i > 0; i--) {
                    if (list.get(i) == 10) {
                        list.set(i, 0);
                        list.set(i - 1, list.get(i - 1) + 1);
                    } else {
                        break;
                    }
                }
            }
            if (list.get(0)==10 || list.size()==1){
                list.set(0,0);
                list.add(0,1);
            }
        } else {
            list.set(list.size() - 1, list.get(list.size() - 1) + 1);
        }
        int[] ans = new int[list.size()];
        int i = 0;
        for (int num : list) {
            ans[i++] = num;
        }
        return ans;
    }

    // 大佬更清晰明了的思路
    public int[] plusOne1(int[] digits){
        for (int i=digits.length-1; i>=0; i--){
            digits[i]++;
            digits[i] = digits[i] % 10;
            if (digits[i]!=0) return digits;
        }
        // 这里初始化后，初始值全是0，因为如果能到这一步，说明其他全是9，实现了进位，都变成了 0
        digits = new int[digits.length+1];
        digits[0] = 1;
        return digits;
    }
}
