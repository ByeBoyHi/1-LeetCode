package Middle.Z字形变换;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().convert2("PAYPALISHIRING", 4));
    }

    // 按照Z字形读取和存储：
    // 给一个集合，里面存入StringBuilder，个数为 min(numRows, len(s))
    // 因为字符串s是一个Z字形存储的形式，那么我们可以同样以一个Z字形的方式把对应的字符串存入对应的行，也就是对应的StringBuilder
    // 最后在对每一行进行拼接即可
    public String convert(String s, int numRows) {
        if (numRows == 1) return s;  // 只有一行，Z不起来啊兄弟
        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < Math.min(numRows, s.length()); i++) {
            rows.add(new StringBuilder());
        }

        // 给一个变量用于控制方向，因为是Z字形存储，会有向下走和向上走走，最开始是向下走的
        // 当走到最下面，也就是numRows的时候就要转向，或者走到0，也要转向
        // 初始值为false，因为第一次就在第一行，容易出现转向，这时候应该先往下转，
        // 如果给false往下，也可以在三目运算符那里改一下正负一，但是不易读
        boolean goingDown = false;
        int curRow = 0;
        for (char c : s.toCharArray()) {
            rows.get(curRow).append(c);
            if (curRow == 0 || curRow == numRows - 1) goingDown = !goingDown;
            curRow += goingDown ? 1 : -1;
        }

        // 对每一行的字符串进行拼接
        StringBuilder res = new StringBuilder();
        for (StringBuilder sb : rows) res.append(sb);

        return res.toString();
    }

    // 直接按照假象的行进行读取
    // 对第i行：   (numRows-curRow) ; curRow*2  除了第一行和最后一行
    // 第一行和最后一行：2*(numRows-1)
    public String convert2(String s, int numRows) {
        if (numRows == 1 || s.length() == 1) return s;
        StringBuilder sb = new StringBuilder();
        boolean flag;  // 控制单双数
        int minRow = Math.min(s.length(), numRows);
        int curRow;
        for (int j = 0; j < minRow; j++) {  // 控制行数
            curRow = j + 1;
            flag = true;
            for (int i = j; i < s.length(); ) {  // 进行跳跃
                sb.append(s.charAt(i));
                if (curRow != 1 && curRow != minRow) {
                    if (flag) {
                        i += ((numRows - curRow) * 2);
                    } else {
                        i += ((curRow - 1) * 2);
                    }
                    flag = !flag;
                } else {
                    i += (2 * (numRows - 1));
                }
            }
        }
        return sb.toString();
    }
}
