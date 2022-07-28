package Daily_Topic.数字范围按位与;


/**
 * m和n假如在最高位到x位之间的二进制位完全相同，
 * 这就说明在递增的过程中+1的操作永远没有改变过这些位，
 * 而相反第x-1到最后一位都至少改变过一次，而改变一定是0->1或者1->0，
 * 就是说在着m-n+1个操作数中至少有一个的第i位（i>=0 && i<=x-1）为0．
 * 因此，第x-1位之下全都为0，m和n从最高位开始的连续相同部分就是我们要的结果．
 * <p>
 * 00000000 00000000 00000000 00000110   6
 * 右移两位：00000000 00000000 00000000 00000001   6/2^2 = 1
 * 左移两位：00000000 00000000 00000000 00011000   6*2^2 = 24
 * <p>
 * 00000000 00000000 00000000 00000110   6
 * 右移两位：00000000 00000000 00000000 00000001   6/2^2 = 1
 * 左移两位：00000000 00000000 00000000 00011000   6*2^2 = 24
 * <p>
 * 00000000 00000000 00000000 00000110   6
 * 右移两位：00000000 00000000 00000000 00000001   6/2^2 = 1
 * 左移两位：00000000 00000000 00000000 00011000   6*2^2 = 24
 * <p>
 * 00000000 00000000 00000000 00000110   6
 * 右移两位：00000000 00000000 00000000 00000001   6/2^2 = 1
 * 左移两位：00000000 00000000 00000000 00011000   6*2^2 = 24
 */

/**
 *          00000000 00000000 00000000 00000110   6
 * 右移两位：00000000 00000000 00000000 00000001   6/2^2 = 1
 * 左移两位：00000000 00000000 00000000 00011000   6*2^2 = 24
 */

/**
 * 给定范围 [m, n]，其中 0 <= m <= n <= 2147483647，返回此范围内所有数字的按位与（包含 m, n 两端点）。
 * <p>
 * 示例 1: 
 * 输入: [5,7]
 * 输出: 4
 * <p>
 * 示例 2:
 * 输入: [0,1]
 * 输出: 0
 *
 * @author 唐
 * @date 2020/8/23 0023
 * @time 23:23
 */
public class rangeBitwiseAnd {
    public static void main(String[] args) {
        System.out.println(rangeBitwiseAnd1(5, 7));
        System.out.println(rangeBitWiseAnd2(5, 7));
    }

    /**
     * 在最低位，只要参与相与的数字是两个，那么最低位必然是0
     * 例如： m 5  1 0 1
     *         6  1 1 0
     *         7  1 1 1
     *                0
     * 同理，以相同的思想判断第二位、第三位...
     * 这时候只要进行右移，依次判断即可。
     * 在判断的同时用一个偏移量来记录自己移动了多少次，就是有多少位变成了0。
     * 在移动的时候要判断m移动后不能为0，否则回头进行左位移恢复的时候就没用了。
     *
     * @param m 上限
     * @param n 下限
     * @return 返回区间所有数字的与运算结果
     */
    public static int rangeBitwiseAnd1(int m, int n) {
        if (m == n) return m;
        if ((m & n) == 0) return 0;

        int offset = 0;
        while (m < n) { // 不断右移，找到第一个同号为1的地方，如果一直位移到m等于1，他俩还不相等，则所有数字按位与必然是0
            if (m == 0 && n != 0) return 0;
            m >>>= 1;
            n >>>= 1;
            offset++;
        }
        return m << offset;
    }

    // 暴力破解
    public static int rangeBitWiseAnd2(int m, int n) {
        if (m == n) return m;
        if (m == 0 || (m & n) == 0) return 0;
        if (m == Integer.MAX_VALUE) return m;

        int temp = m;
        // for循环容易溢出。
        for (int i = m + 1; i <= n; i++) {
            temp = temp & i;
            if (temp == 0 || i == Integer.MAX_VALUE) {
                break;
            }
        }
        return temp;
    }

    // 他人解法
    public static int rangeBitWiseAnd3(int m, int n) {
        //求m和n的"左侧公共前缀"
        if (m == n) return m;
        int count1 = 0;
        int m1 = m;
        int n1 = n;
        while (m1 != 0 || n1 != 0) {
            m1 >>= 1;
            n1 >>= 1;
            count1++;
        }
        int count2 = 0;
        int ans = 0;
        for (int i = count1 - 1; i > 0; i--) {
            if ((m >> i & 1) == (n >> i & 1)) {
                ans <<= 1;
                ans += m >> i & 1;
                count2++;
            } else {
                break;
            }
        }
        ans <<= (count1 - count2);
//        System.out.println("count1:" + count1 + ";" + "count2:" + count2 + ";"+ "ans:" + ans);
//        System.out.println("count1:" + count1 + ";" + "count2:" + count2 + ";");
//        int ans = 0;
//        for (int i = 0; i < count2; i++) {
//            ans <<= 1;
//            ans += 1;
//        }
//        System.out.println("ans:" + ans);
//        ans <<= (count1 - count2);
//        System.out.println("ans:" + ans);

//        System.out.println("count1:" + count1 + ";" + "count2:" + count2 + ";" + "ans:" + ans);
        return ans;
    }
}

/**
 * 1011
 * 110 ->0010
 * <p>
 * 1010 ->0010
 */
