package Easy.杨辉三角Ⅱ;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) {

    }

    public List<Integer> getRow(int rowIndex) {
        List<Integer> list = new ArrayList<>();
        if (rowIndex == 0 || rowIndex == 1) {
            list.add(1);
            if (rowIndex == 1) {
                list.add(1);
            }
            return list;
        }

        list.add(1);
        list.add(1);
        for (int i = 0; i < rowIndex-1; i++) {

        }

        return list;
    }
}
