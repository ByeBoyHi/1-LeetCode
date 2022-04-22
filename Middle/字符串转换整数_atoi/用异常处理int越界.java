package Middle.字符串转换整数_atoi;

import java.net.Inet4Address;
import java.util.Map;

public class 用异常处理int越界 {

    public static void main(String[] args) {
        System.out.println(new Solution().myAtoi("-91283472332"));
    }

    public int myAtoi(String s) {
        if (s.length()==0){
            return 0;
        }
        long ans = 0;
        int i = 0;
        int flag = 1;
        // 去除所有前置空格
        while (i<s.length() && s.charAt(i)==' ')
            i++;
        if (s.charAt(i)=='+'){
            i++;
        }
        while (i<s.length() && (s.charAt(i)=='-' || (s.charAt(i)>='0' && s.charAt(i)<='9'))){
            if (s.charAt(i)=='-'){
                if (i>0){
                    if (s.charAt(i-1)=='+') {  // 前一个是加号
                        return 0;
                    }
                    // 前一个是数字，那么这是一个中间的负号，没有意义，退出循环
                    if (s.charAt(i-1)>='0' && s.charAt(i-1)<='9'){
                        break;
                    }
                }
                flag = -1; // 改变正负
                i++;
                continue;
            }
            // 处理越界
            ans = ans*10+(s.charAt(i)-'0');
            if (ans> Integer.MAX_VALUE){
                return (int)ans*flag;
            }
            i++;
        }
        return (int)ans*flag;
    }
}