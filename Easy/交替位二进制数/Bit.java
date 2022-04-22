package Easy.交替位二进制数;

public class Bit {
    public boolean hasAlternatingBits(int n) {
        boolean flag = (n&1)==1;  // 第一位是1则为true，否则为false
        n>>=1;
        while (n!=0){
            if (
                    (flag && (n&1)==1)  // 上一次为1，这一次还是1
                ||
                    (!flag && (n&1)==0)  // 上一次是0，这一次还是0
            ){
                return false;
            }
            flag = !flag;
            n>>=1;
        }
        return true;
    }
}
