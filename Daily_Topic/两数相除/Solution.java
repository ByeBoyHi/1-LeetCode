package Daily_Topic.两数相除;

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().divide2(2147483647,1));
//        System.out.println(Integer.MIN_VALUE);
//        System.out.println(Integer.MAX_VALUE);
    }

    // 被除数，除数
    // 不能使用除法、乘法、取余。
    public int divide(int dividend, int divisor) {
        if ((long) dividend == 0) {  // 除数为0
            return 0;
        }

        int ans = 0;
        long ans1 = 1;
        long dividendLong = Math.abs((long) dividend);
        long divisorLong = Math.abs((long) divisor);

        if (dividendLong < divisorLong) {
            return 0;
        }

        while ((divisorLong << 1) <= dividendLong) {
            divisorLong = divisorLong << 1;
            ans1 = ans1 << 1;
        }
        if (ans1 == 1) {  // dividend 和 divisor绝对值相等
            // 同号
            if ((dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0))
                return 1;
                // 异号
            else
                return -1;
        }

        // 把上面已经除掉的减掉
        dividendLong = dividendLong - divisorLong;
        long divisorAbs = Math.abs((long) divisor);
        while (dividendLong >= divisorAbs) {
            ans++;
            dividendLong -= divisorAbs;
        }

        if ((dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0)) {
            return (~(ans + (int) ans1)) + 1;
        }
        if (ans + ans1 > Integer.MAX_VALUE || ans + ans1 < Integer.MIN_VALUE) {
            return Integer.MAX_VALUE;
        }
        return ans + (int) ans1;
    }

    // 双层while位运算
    public int divide2(int dividend, int divisor) {
        if (dividend==0 || Math.abs((long)dividend)<Math.abs((long)divisor)){
            return 0;
        }
        long dividendLong = Math.abs((long)dividend);
        long divisorLong = Math.abs((long)divisor);

        if (dividendLong==divisorLong){
            if ((dividend>0 && divisor>0) || (dividend<0 && divisor<0)){
                return 1;
            }else{
                return -1;
            }
        }

        long ans = 0;
        long temp;
        while (divisorLong<=dividendLong){
            temp = 1;
            while ((divisorLong<<1)<=dividendLong){
                divisorLong = divisorLong<<1;
                temp = temp<<1;
            }
            ans = ans+temp;
            dividendLong = dividendLong - divisorLong;
            divisorLong = Math.abs((long)divisor);
        }


        // 对计算出来的结果进行符号的判断
        if ((dividend>0 && divisor>0) || (dividend<0 && divisor<0)){
            // 判断完了符号还要判断是否越界
            if (ans>Integer.MAX_VALUE){
                return Integer.MAX_VALUE;
            }else {
                return (int)ans;
            }
        }else{
            if ((~ans+1)<Integer.MIN_VALUE){
                return Integer.MAX_VALUE;
            }
            return (~(int)ans)+1;
        }
    }
}
