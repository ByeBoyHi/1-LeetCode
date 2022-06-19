package Easy.二进制表示中质数个计算置位;

public class CountPrimeSetBits {
    public int countPrimeSetBits(int left, int right) {
        // 不断计算最右侧的1
        int ans = 0;
        for (int i = left; i <= right; i++) {
            int cur = 0;
            left = i;
            while (left!=0){
                int mostRightOne = left & (~left+1);  // 找到最右侧的1
                left-=mostRightOne;
                cur++;
            }
            if (cur>1 && isPrime(cur)){
                ans++;
            }
        }
        return ans;
    }

    public boolean isPrime(int n){
        int x = (int)Math.sqrt(n);
        for (int i = 2; i <= x; i++) {
            if (n%i==0) {
                return false;
            }
        }
        return true;
    }
}
