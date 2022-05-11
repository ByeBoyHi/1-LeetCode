package Easy.爬楼梯;

public class PaLouti {
    // 一次可以上一步台阶或者两步台阶，当有N个台阶的时候，可以有多少种方式走完
    public static int getWays(int N) {
        if (N == 1) {  // 等于1的时候，只能走一步
            return 1;
        } else if (N == 2) {  // 等于2的时候，可以走两次一步，也可以一次两步
            return 2;
        }
        return getWays(N - 1) + getWays(N - 2);
    }
    // 优化
    /*
        第i步，是第i-1步一步走过来，或者第i-2步一次走过来的
        所以 f(i) = f(i-1)+f(i-2)
     */
    public static int getWays2(int N){
        if (N==1){
            return 1;
        }
        if (N==2){
            return 2;
        }

        int one = 1;
        int two = 2;
        int res = 0;
        for (int i=3; i<=N; i++){
            res = one+two;
            one = two;
            two = res;
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(getWays(11));
        System.out.println(getWays(9));

        System.out.println(getWays2(11));
        System.out.println(getWays2(9));
    }
}
