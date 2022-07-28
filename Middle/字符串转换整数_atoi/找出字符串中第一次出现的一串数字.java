package Middle.字符串转换整数_atoi;

public class 找出字符串中第一次出现的一串数字 {
    public int myAtoi(String s) {
        StringBuilder ans = new StringBuilder();
        int i=0;
        // 过滤掉前面的非数字和非负号
        while(i<s.length() && (s.charAt(i)<'0' || s.charAt(i)>'9') && s.charAt(i)!='-')
            i++;

        while(i<s.length() && ((s.charAt(i)>='0' && s.charAt(i)<='9') || s.charAt(i)=='-')){
            if(s.charAt(i)=='-'){
                if(ans.length()==0){  // 如果是第一个符号，就添加进去
                    ans.append("-");
                    i++;
                    continue;
                }else{  // 如果是半路的符号，就break了
                    break;
                }
            }
            // 去掉前导零
            if(s.charAt(i)=='0' && (ans.length()==0 || (ans.length()==1 && ans.toString().equals("-")))){
                i++;
                continue;
            }
            ans.append(s.charAt(i));
            i++;
        }
        if(ans.length()==0 || (ans.length()==1 && ans.toString().equals("-")))
            return 0;
        long res = Long.parseLong(ans.toString());
        if (res<Integer.MIN_VALUE){
            return Integer.MIN_VALUE;
        }else if (res>Integer.MAX_VALUE){
            return Integer.MAX_VALUE;
        }else {
            return (int)res;
        }
    }
}

