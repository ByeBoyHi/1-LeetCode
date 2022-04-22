package Easy.x的平方根;

class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.mySqrt2(Integer.MAX_VALUE));
        System.out.println(Integer.MAX_VALUE);
        System.out.println(46340 * 46340);
    }

    public int mySqrt(int x) {
        if (x <= 0) {
            return 0;
        } else if (x <= 3) {
            return 1;
        } else if (x == 4) {
            return 2;
        } else {
            double res = 0;
            for (int i = 2; i <= (x / 2 + 1); i++) {
                res = i * i;
                if (res > x || res < 0) {
                    res = i - 1;
                    break;
                } else if (res == x) {
                    res = i;
                    break;
                }
            }
            return (int) res;
        }
    }

    public int mySqrt2(int x) {
        if (x <= 0) {
            return 0;
        } else if (x == 1) {
            return 1;
        } else {
            double mid = x / 2.0;
            double temp = x * 1.0;
            while (mid > 1) {
                if (mid * mid == x) {
                    return (int) mid;
                } else if (mid * mid <= 0) {  // 超越最大值的界，就会变成负数
                    temp = mid;
                    mid = mid / 10;
                } else {
                    int H = (int) (mid + 1);
                    int L = (int) mid;
                    if (H * H > x && L * L < x) {   // L*L < x < H*H
                        return L;
                    } else if (H * H == x) {
                        return H;
                    } else if (L * L == x) {
                        return L;
                    } else if (H * H < x) { // 扩大
                        mid = (mid + temp) / 2;
                    } else if (L * L > x) { // 缩小
                        temp = mid;
                        mid = mid / 2;
                    }
                }
            }
            return 1;
        }
    }
}
