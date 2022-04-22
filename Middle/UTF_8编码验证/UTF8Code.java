package Middle.UTF_8编码验证;

public class UTF8Code {

    public static boolean validUtf8(int[] data) {
        /*
            理解题：
                1. 一字节的时候，第一位为0即可。
                2. 其他的数据只能是2~4字节。
                3. 超过4字节的，则不合格。
                4. 假设，对于3字节，那么就是 1110xxxx ，且后面跟着的两位字节的开头必须是 10xxxxxx。
                5. 对于3字节，后面至少有2个字节数据，否则不合格。
         */
        // 得到这是一个几字节的数据
        for (int i=0; i<data.length; ){
            int bs = getBytes(data[i]);
            if (bs==0) {
                i++;
                continue;
            }
            // 这里不能取等号
            if (bs==1 || bs>4 || i+bs>data.length) {
                return false;
            }
            for (int j=i+1; j<i+bs; j++){
                if ((data[j] & 128) == 0 || (data[j] ^ 64) == 0) {
                    return false;
                }
            }
            i = i+bs;
        }
        return true;
    }

    // 根据传入的数字，判断这个是几字节的数据
    public static int getBytes(int num){
        int cur = 128;
        int ans = 1;
        while ((num&cur)!=0) {
            ans++;
            cur>>=1;
        }
        return ans;
    }

    // 生成随机数组
    public static int[] generateRandomArray(int min, int max, int len){
        int[] ans = new int[len];
        for (int i=0; i<len; i++){
            ans[i] = (int) Math.floor(Math.random()*(max-min)+min);
        }
        return ans;
    }
}
