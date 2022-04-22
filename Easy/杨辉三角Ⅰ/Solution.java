package Easy.杨辉三角Ⅰ;

import java.lang.reflect.Array;
import java.util.*;

/**
     输入: 5
     输出:
     [
            [1],
           [1,1],
          [1,2,1],
         [1,3,3,1],
        [1,4,6,4,1]
     ]
 */
public class Solution {

    public static void main(String[] args){
        List<List<Integer>> lists =  generate(5);
        System.out.println(lists);
    }

    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> list = new ArrayList<>();
            list.add(1);
            for (int j = 1; j < i; j++) {
                // 上一个链表的左上角
                int num1 = lists.get(i-1).get(j-1);
                // 上一个链表的右上角
                int num2 = lists.get(i-1).get(j);
                // 他俩取和加在这个位置
                list.add(j,(num1+num2));
            }
            if (i!=0){
                list.add(1);
            }
            lists.add(list);
        }

        return lists;
    }
}
