package Easy.二进制求和;

public class BiSum {
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int len = Math.max(a.length(), b.length());
        int carry = 0;
        for (int i = 0; i < len; i++) {
            // 没有越位的时候，把这个数字加上
            carry += i < a.length() ? a.charAt(a.length() - 1 - i) - '0' : 0;
            carry += i < b.length() ? b.charAt(b.length() - 1 - i) - '0' : 0;
            // 把余数加上，也就是未进位的值
            sb.append(carry%2);
            // 得到进位值
            carry/=2;
        }
        // 最后算完后，carry可能还有进位没有加
        if (carry>0){
            sb.append(1);
        }

        return sb.reverse().toString();
    }

    // 系统自带的值
    public String addBinary2(String a, String b){
        return Integer.toBinaryString(
                // 对二进制字符串转十进制后相加，再转为二进制
                // 健壮性：对于极大的二进制数字，会无法转换
                /*
                    字符串长度：
                        1. 超过32，则无法转为Integer
                        2. 超过64，则无法转为Long
                        3. 超过500000001，则无法转为BigInteger
                 */
                Integer.parseInt(a, 2)+Integer.parseInt(b, 2)
        );
    }
}
