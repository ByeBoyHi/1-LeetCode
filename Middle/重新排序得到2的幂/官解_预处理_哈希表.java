package Middle.重新排序得到2的幂;

import java.util.HashSet;
import java.util.Set;

public class 官解_预处理_哈希表 {
    /**
     * 思路：
     * 1. 由于我们可以按照任何顺序对数字进行排列，那么两个不同的数字 a 和 b，
     *    如果其十进制字符从小到大排序后是一样的，那么他俩是否能够2的次幂的结果也是一样的。
     * *2. 进一步的，只要数字 a 和 b的十进制字符数组中， 0~9 每个字符出现的次数是一样的，那么他们能否得到 2 的次幂也是一样的。
     * 3. 由于 2^29 < 10^9 < 2^30，那么，在 10^9这个范围内，有30个2的次幂的数字。
     *    对于这30个数字，我们可以预处理其 0~9 每个字符出现的次数，存在一个长度为 10 的数组里面，然后把数组放在哈希表里面存储记录。
     *    对于数字 n，我们同样对其 0~9 每个数字出现的次数进行计算，然后得到一个长度为 10 的数组，去哈希表里面找，如果有就可以，否则不行。
     */
    // 存数组，数组长度为10
    Set<String> powerOf2Digits = new HashSet<>();

    public boolean reorderedPowerOf2(int n) {
        initialization();
        return powerOf2Digits.contains(countNum(n));
    }

    private void initialization(){
        // 小于10^9
        for (int n=1; n<=1e9; n<<=1){
            powerOf2Digits.add(countNum(n));
        }
    }

    // 把一个数字传进去，然后生成对应的各个字符出现的频数的字符串
    private String countNum(int num){
        char[] cnt = new char[10];
        while (num>0){
            cnt[num%10]++;
            num = num/10;
        }
        return new String(cnt);
    }
}