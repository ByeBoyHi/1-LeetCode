package Middle.找到字符串中所有字母异位词;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindAllAnagramsInAString {
    // 暴力破解
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        int sLen = s.length();
        int pLen = p.length();
        char[] pChar = p.toCharArray();
        Arrays.sort(pChar);
        for (int i = 0; i < sLen - pLen + 1; i++) {
            char[] curChars = s.substring(i, i + pLen).toCharArray();
            Arrays.sort(curChars);
            int j = 0;
            for (; j < curChars.length; j++) {
                if (curChars[j] != pChar[j]) {
                    break;
                }
            }
            if (j == curChars.length) {
                ans.add(i);
            }
        }
        return ans;
    }

    // 滑动窗口
    /*
        思路：
        1. 使用一个数组，长度是p的长度，用来记录从 0 ~ pLen-1，这个范围s的值是多少
        2. 每次都用Arrays内置的equals进行比较
        3. 比较完一次，就让 s 的数组，去除当前位置的值，然后加上下一个位置的值
        4. 这个循环长度是 sLen-pLen这个范围
            比如 sLen=4, pLen=2，那么我们需要走到倒数第二个，才能保证最后两个字母被判断到
            也就是 sLen-pLen=2，我们范围小于 sLen-pLen+1 即可。
     */
    public List<Integer> findAnagrams2(String s, String p) {
        int sLen = s.length();
        int pLen = p.length();

        if (sLen < pLen) {  // 长度小，肯定没有异位词
            return new ArrayList<>();
        }

        // 定义两个字符数组来存储他们这个区间的字符串的值
        // 因为都是小写字母，所以用26长度
        int[] sCounts = new int[26];
        int[] pCounts = new int[26];
        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < pLen; i++) {
            sCounts[s.charAt(i) - 'a']++;
            pCounts[p.charAt(i) - 'a']++;
        }

        if (Arrays.equals(sCounts, pCounts)) {
            ans.add(0);  // 第一个位置合理
        }

        // 因为我们是减去当前值，加上下一个末尾值
        // 所以我们只需要走到 sLen-pLen-1，那么这时候，我们算的其实就是sLen-pLen
        // 因为我们会减去sLen-pLen-1这个位置的值，然后比较以sLen-pLen开头的串
        for (int i = 0; i < sLen - pLen; i++) {
            // 每一次试探性的加上末尾的值，并且减去当前的首字母的值
            --sCounts[s.charAt(i) - 'a'];
            ++sCounts[s.charAt(i + pLen) - 'a'];
            if (Arrays.equals(sCounts, pCounts)) {
                ans.add(i + 1);
            }
        }

        return ans;
    }

    // 滑动窗口的优化
    /*
        上面的滑动窗口，我们统计的是相同的位置，那么现在我们可以统计相差有多少
        如果相差为0，那么完全ok
        对于26个字母，他们int值相差最大是26
        对于ASCI值，有a+z=b+y

        * 这个思路有问题，对于数值，后面会有加和乘积的重复问题。
    */
    /*
        换个思路：
     */
    public List<Integer> findAnagrams3(String s, String p) {
        int sLen = s.length();
        int pLen = p.length();

        if (sLen<pLen){
            return new ArrayList<>();
        }

        List<Integer> ans = new ArrayList<>();
        int[] counts = new int[26];
        for (int i=0; i<pLen; i++){
            counts[p.charAt(i)-'a']++;
            counts[s.charAt(i)-'a']--;
        }

        if (isEqual(counts)){
            ans.add(0);
        }

        for (int i=0; i<sLen-pLen; i++){
            counts[s.charAt(i)-'a']++;
            counts[s.charAt(i+pLen)-'a']--;
            if (isEqual(counts)){
                ans.add(i+1);
            }
        }

        return ans;
    }

    private boolean isEqual(int[] arr){
        for (int j : arr) {
            if (j != 0) {
                return false;
            }
        }
        return true;
    }

    // 滑动窗口优化：用一个变量来记录两个字符串的字母的差
    public List<Integer> findAnagrams4(String s, String p) {
        int sLen = s.length();
        int pLen = p.length();

        if (sLen<pLen){
            return new ArrayList<>();
        }

        List<Integer> ans = new ArrayList<>();
        int[] counts = new int[26];
        int differs = 0;
        for (int i=0; i<pLen; i++){
            counts[p.charAt(i)-'a']++;
            counts[s.charAt(i)-'a']--;
        }

        for (int i=0; i<26; i++){
            if (counts[i]!=0){
                differs++;
            }
        }
        if (differs==0){
            ans.add(0);
        }

        for (int i=0; i<sLen-pLen; i++){
            int curSi = ++counts[s.charAt(i) - 'a'];
            if (curSi == 0){ // 从不同变成相同
                differs--;
            }else if (curSi==1){  // 从相同变成不同
                differs++;
            }

            int curSiPLen = --counts[s.charAt(i+pLen)-'a'];
            if (curSiPLen==0){  // 从不同变成相同
                differs--;
            }else if (curSiPLen==-1){  // 从相同变成不同
                differs++;
            }
            // 其他数字的情况不用考虑，比如有两个或者多个不同的时候，不用管
            // 因为只要他们往相同靠近，最后都会要判断1或者-1
            if (differs==0){
                ans.add(i+1);
            }
        }

        return ans;
    }

    // 左右指针的滑动窗口
    public List<Integer> findAnagrams5(String s, String p) {
        int sLen = s.length();
        int pLen = p.length();
        if (sLen<pLen){
            return new ArrayList<>();
        }

        List<Integer> ans = new ArrayList<>();
        int[] sChar = new int[26];
        int[] pChar = new int[26];
        for (int i=0; i<pLen; i++){
            sChar[s.charAt(i)-'a']++;
            pChar[p.charAt(i)-'a']++;
        }

        int left = 0;
        int right = pLen-1;
        while (right<sLen){
            if (left!=0){  // 滑动了
                sChar[s.charAt(left-1)-'a']--;
                sChar[s.charAt(right)-'a']++;
            }
            boolean flag = true;
            for (int i=0; i<26; i++){
                if (sChar[i]!=pChar[i]){
                    flag = false;
                    break;
                }
            }
            if (flag){
                ans.add(left);
            }
            left++;
            right++;
        }
        return ans;
    }
}
