package Middle.格雷编码;

import java.util.ArrayList;
import java.util.List;

public class GrayCode {
    // 方法一
    public List<Integer> grayCode1(int n) {
        List<Integer> ret = new ArrayList<>();
        ret.add(0);
        for (int i = 1; i <= n; i++) {
            int m = ret.size();
            for (int j = m - 1; j >= 0; j--) {  // 逆序加入列表里面
                ret.add(ret.get(j) | (1 << (i - 1)));  // 最高位补一
            }
        }
        return ret;
    }

    // 方法二
    public List<Integer> grayCode2(int n) {
        List<Integer> ret = new ArrayList<Integer>();
        for (int i = 0; i < (1 << n); i++) {
            // i左移一位让高位变成0
            // 然后和原来的i进行亦或得到当前位置应有的格雷码
            ret.add((i >> 1) ^ i);
        }
        return ret;
    }
}
