package Middle.最简分数;

import java.util.ArrayList;
import java.util.List;

public class SimplifiedFractions {
    public List<String> simplifiedFractions(int n) {
        List<String> ans = new ArrayList<>();
        for (int i=2; i<=n; i++){ // 分母
            for (int j=1; j<i; j++){  // 分子
                if (gcd(j, i)==1){  // 最简分数才会加进去，可以约分的都进不去
                    ans.add(j+"/"+i);
                }
            }
        }
        return ans;
    }

    public int gcd(int a, int b){
        return b!=0?gcd(b, a%b):a;
    }
}
