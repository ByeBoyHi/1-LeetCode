package Easy.自除数;

import java.util.ArrayList;
import java.util.List;

public class SelfDividingNumbers {
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> ret = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            if (isValid(i)) {
                ret.add(i);
            }
        }
        return ret;
    }

    private boolean isValid(int cur) {
        int num = cur;
        while (cur!=0){
            int mod = cur%10;  // 个位取出来
            if (mod==0 || num%mod!=0)return false;
            cur = cur/10;  // 去掉个位
        }
        return true;
    }
}
