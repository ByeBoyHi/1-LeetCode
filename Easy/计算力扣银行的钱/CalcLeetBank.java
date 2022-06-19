package Easy.计算力扣银行的钱;

public class CalcLeetBank {
    public int totalMoney(int n) {
        int ans = 0;
        int i=1;
        while (n>=7){
            ans+=(i+i+6)*3+i+3;
            n-=7;
            i++;
        }
        ans+=(i+i+n-1)*(n/2);
        if ((n&1)==1){
            ans+=i+n/2;
        }
        return ans;
    }
}
