package Middle.无重复字符的最长子串;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        System.out.println(Solution.lengthOfLongestSubstring1("pwwkew"));
        System.out.println(Solution.lengthOfLongestSubstring1("bbb"));
        System.out.println(Solution.lengthOfLongestSubstring1("abcabcbb"));
        System.out.println(Solution.lengthOfLongestSubstring1(""));
        System.out.println(Solution.lengthOfLongestSubstring1("au"));
        System.out.println(Solution.lengthOfLongestSubstring1("dvdf"));
        System.out.println(Solution.lengthOfLongestSubstring1("ohomm"));
        System.out.println(Solution.lengthOfLongestSubstring1("aabaab!bb"));
//        System.out.println(reverse2("hello world"));
//        System.out.println(reverse2(reverse2("hello world")));
        System.out.println(Solution.lengthOfLongestSubstring1("asdawbcac"));
    }

    // 个人思路
    public static int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        int maxLen = 0;
        List<Character> list = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (!list.contains(s.charAt(i))) { // 暂不重复
                list.add(s.charAt(i));
                // 最大值更新
                if (maxLen < list.size()) {
                    maxLen = list.size();
                }
            } else { //重复了

                // 删除重复元素及之前的全部数据
                int len = list.size();
                int cur = list.indexOf(s.charAt(i));
                list = list.subList(cur + 1, len);
                list.add(s.charAt(i));
                // 剩余长度小于maxLen，直接退出循环
                if (s.length() - 1 - i + list.size() <= maxLen) break;
            }
        }
        return maxLen;
    }

    // 官解：滑动窗口（感觉像暴力破解）
    /*
     * 假设一个字符串是：abschana。那么以每个字符为起点的最长子串是：
     *  (a)bschana  --> (absch)ana
     *  a(b)schana  --> a(bschan)a
     *  ab(s)chana  --> ab(schan)a
     *  abs(c)hana  --> abs(chan)a
     *  absc(h)ana  --> absc(han)a
     *  absch(a)na  --> absch(an)a
     *  abscha(n)a  --> abscha(na)
     *  abschan(a)  --> abschan(a)
     *  这些子串就像在往后面滑动一样，我们只需要记录这些子串的长度，然后和上一次的最大长度比较就可以了
     */
    public static int lengthOfLongestSubstring1(String s) {
        HashSet<Character> set = new HashSet<>();  // 用来存储不重复子串，记录每次的个数
        int n = s.length();  // 存储字符串长度
        int res = 0;
        // rk记录以这个字符
        int rk = -1;  // rk取-1，可以让下面的0位置的元素加入到set里面
        for (int i = 0; i < n; i++) {
            // i记录当前出发的首字母
            if (i != 0) {  // 除了第一个元素，删除上一个已经记录过的元素
                set.remove(s.charAt(i-1));
            }

            // 假如contains找到的是中间的某个元素，那么这里的while会一直不执行
            // 直到上面的if一直删除到重复的那个元素位置
            // 因为从当前元素到重复元素之间的元素，没必要再检测了，肯定比这一次的长度短
            while (rk + 1 < n && !set.contains(s.charAt(rk + 1))) {
                set.add(s.charAt(rk + 1));
                rk++;
            }

            // 每一次检测完子串后，进行最大子串长度的存储。
            // rk是记录的未重复元素的个数-1
            // -i就是把之前的元素个数全部去掉
            res = Math.max(res, set.size());
        }
        return res;
    }

    // 大佬解法
    public static int lengthOfLongestSubstring2(String s) {
        // 记录字符上一次出现的位置
        int[] last = new int[128];
        for(int i = 0; i < 128; i++) { // 把所有初值赋值为-1
            last[i] = -1;
        }
        int n = s.length();

        int res = 0;
        int start = 0;  // 开始位置
        for(int i = 0; i < n; i++) {
            // 取到当前字符的ascii值
            int index = s.charAt(i);

            // 判断这个index元素是否出现，出现了的话，就要从下一个位置开始记录子串
            start = Math.max(start, last[index] + 1);

            // 取当前位置到起始位置的长度和之前的长度的最大值
            res   = Math.max(res, i - start + 1);

            // 记录当前元素已经出现的位置
            last[index] = i;
        }

        return res;
    }

    public static void reverse(Object[] objs) {
        for (int i = 0; i < objs.length / 2; i++) {
            Object o = objs[i];
            objs[i] = objs[objs.length - 1 - i];
            objs[objs.length - 1 - i] = o;
        }
    }

    // 递归实现
    public static String reverse2(String str) {
        if (str.length() == 0) return "";
        char cur = str.charAt(0);
        str = str.substring(1);
        return reverse2(str) + cur;
    }
}
