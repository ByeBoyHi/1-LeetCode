package Easy._3的幂;

public class PowerOfThree {
    // 循环
    public boolean isPowerOfThree(int n) {
        if (n<=0){
            return false;
        }
        while (n>1){
            if (n%3!=0){
                return false;
            }
            n/=3;
        }
        return true;
    }

    //快速幂
    public static boolean isPowerOfThree2(int n) {
        if (n<=0) return false;
        long start = 3;
        while (start<n){
            start*=start;
            System.out.println(start);
        }
        while (start>n){
            start/=3;
            System.out.println(start);
        }

        return start==n;
    }

    public static void main(String[] args) {
        System.out.println(isPowerOfThree2(129140163));
    }

    // 判断一个数X是否是k的次幂
    // log(X)/log(k)是否≈0
    // 而到了 2的493次方，结果是493.00000000000006 ； 所以，平时用这个公式来确定n是否是2的整数次幂足够了！
    // 后面的次幂比较，是确定误差在这个范围内，都是计算误差，实际结果是正确的
    public static boolean isPowerOfThree3(int n) {
        double x = Math.log(n)/Math.log(3);
        return Math.abs(x-Math.round(x))<Math.pow(10,-14);
    }
}
