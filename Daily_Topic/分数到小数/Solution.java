package Daily_Topic.分数到小数;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().fractionToDecimal(-1, -2147483648));
    }

    // 分子 分母
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator==0) return "0";

        // 判断结果正负
        String c = "";
        if((numerator ^ denominator) < 0) c = "-";

        // 要先进性类型转换，在进行绝对值
        // 因为有可能分子分母已经超过了int的范围，这时候先取绝对值是没有反应的，是负数还是负数，是正数还是正数
        long denominatorLong = Math.abs((long) denominator);
        long numeratorLong = Math.abs((long) numerator);

        long quotient =  numeratorLong / denominatorLong;
        long remainder = numeratorLong % denominatorLong;

        if (remainder == 0) return c+quotient;  // 能整除的情况

        StringBuilder s = new StringBuilder();
        // 记录每一个余数出现在第几次，也就是对应的商数的位置，便于找循环节
        HashMap<Long, Integer> remainderIndexMap = new HashMap<>();
        int index = 0;
        while (remainder!=0 && !remainderIndexMap.containsKey(remainder)){
            remainderIndexMap.put(remainder, index);  // 把上一个余数放进来
            remainder*=10;
            s.append(remainder/denominatorLong); // 余数除以分母，得到的商放进字符串里面
            remainder = remainder % denominatorLong; // 更新余数
            index++;
        }
        if (remainder==0){  //有限小数
            return c+quotient+"."+s;
        }

        // 无限小数
        int insertIndex = remainderIndexMap.get(remainder);
        s.insert(insertIndex, "(");

        return c+quotient+"."+s+")";
    }

    // 官解
    public String fractionToDecimal2(int numerator, int denominator) {
        long numeratorLong = (long) numerator;
        long denominatorLong = (long) denominator;
        if (numeratorLong % denominatorLong == 0) {
            return String.valueOf(numeratorLong / denominatorLong);
        }

        StringBuffer sb = new StringBuffer();
        if (numeratorLong < 0 ^ denominatorLong < 0) {
            sb.append('-');
        }

        // 整数部分
        numeratorLong = Math.abs(numeratorLong);
        denominatorLong = Math.abs(denominatorLong);
        long integerPart = numeratorLong / denominatorLong;
        sb.append(integerPart);
        sb.append('.');

        // 小数部分
        StringBuffer fractionPart = new StringBuffer();
        Map<Long, Integer> remainderIndexMap = new HashMap<Long, Integer>();
        long remainder = numeratorLong % denominatorLong;
        int index = 0;
        while (remainder != 0 && !remainderIndexMap.containsKey(remainder)) {
            remainderIndexMap.put(remainder, index);
            remainder *= 10;
            fractionPart.append(remainder / denominatorLong);
            remainder %= denominatorLong;
            index++;
        }
        if (remainder != 0) { // 有循环节
            int insertIndex = remainderIndexMap.get(remainder);
            fractionPart.insert(insertIndex, '(');
            fractionPart.append(')');
        }
        sb.append(fractionPart.toString());

        return sb.toString();
    }
}
