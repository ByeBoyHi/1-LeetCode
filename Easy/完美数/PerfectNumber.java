package Easy.完美数;

import java.util.List;

public class PerfectNumber {
    public boolean checkPerfectNumber(int num) {
        int ans = 0;
        int half = num/2;
        for (int i=1; i<=half; i++){
            if (num%i==0){
                ans+=i;
            }
        }
        return ans==num;
    }

    // num = a*b  当a==b的时候，a=sqrt(num)
    public boolean checkPerfectNumber2(int num){
        if (num==1) return false;
        int ans = 1;
        for (int d=2; d*d<=num; d++){
            if (num%d==0){
                ans+=d;
                if (d*d<num){
                    ans+=num/d;
                }
            }
        }
        return ans==num;
    }

    // 欧几里得-欧拉定理：每个偶完全数都可以写成：2^(p-1) * (2^p -1)
    // 所以 [1, 10^8] 的完全数有：6,28,496,8128,33550336
    public boolean checkPerfectNumber3(int num){
        return num == 6 || num == 28 || num == 496 || num == 8128 || num == 33550336;
    }
}
