package 动态规划.中等.解码方式;

public class Solution {
    /**
     * 解码可能有两种：f[i] 和 f[i]*10+f[i+1]
     * 所以 f[i]=f[i-1]+f[i-2] 当 f[i-2]满足上面的右边的时候
     * 认定空串为 1，并且最后会递归到n+1，因为我们要保存 n 和 n-1的和，
     */
    public int numDecodings(String s) {
        if (s.charAt(0)=='0') return 0;
        int n = s.length();
        int ans=0;
        int fi1=1;
        int fi2=0;
        for (int i=1; i<=n; i++){
            ans = 0;  // 每次都要对 ans 清零，如果保留的话，上一个值会被不断的double胡乱生长，最终的结果也不是 f[i-1]+f[i-2]。
            if (s.charAt(i)!='0'){  // 0 不对应字母
                ans+=fi1;
            }else{  // 遇到0，并且前一位不是1或者2，无法对0进行保留
                if(s.charAt(i-2)!='1' && s.charAt(i-2)!='2'){
                    return 0;
                }
            }
            if (i>1 && s.charAt(i-2)!='0' && (s.charAt(i-2)*10+s.charAt(i-1)<=26)){
                ans+=fi2;
            }
            fi2 = fi1;
            fi1 = ans;
        }
        return ans;
    }
}
