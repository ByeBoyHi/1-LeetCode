package Middle.重复叠加字符串匹配;

import java.time.Clock;

public class RepeatedStringMatch {
    public int repeatedStringMatch (String A, String B){
        int d = B.length() / A.length();
        StringBuilder sb = new StringBuilder();
        sb.append(A.repeat(d));

        if (isSubString(B, sb.toString())) {
            return d;
        }
        if (isSubString(B, sb.append(A).toString())) {
            return d + 1;
        }
        if (isSubString(B, sb.append(A).toString())) {
            return d + 2;
        }
        return -1;
    }
    public boolean isSubString (String s, String t){
        //判断s是否为t的子串
        if (s.length() > t.length()) {
            return false;
        }
        for (int i = 0; i <= t.length() - s.length(); i++) {
            if (s.equals(t.substring(i, i + s.length()))) {
                return true;
            }
        }
        return false;
    }


    // 首先对 a 进行复制确保长度大于等于 b，然后在 一定时间 内，不断的「复制 - 检查」，如果在规定时间内能够找到则返回复制次数，否则返回 -1。
    public int repeatedStringMatch2(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int ans = 0;
        while (sb.length() < b.length() && ++ans > 0) sb.append(a);
        Clock clock = Clock.systemDefaultZone();
        long start = clock.millis();
        while (clock.millis() - start < 100) {
            if (sb.indexOf(b) != -1) return ans;
            sb.append(a);
            ans++;
        }
        return -1;
    }


    // 长度至少大于等于才有可能匹配成功。
    // 在恰好超过的时候，再把自己追加一次，后面的没必要再追加了，因为后面都是重复添加了。
    // 如果最后的匹配位置的长度大于我前面while拼接的次数，说明实在后面追击的时候匹配上的，返回ans+1。
    // 否则就是在最后一次拼接之前匹配上的，返回ans。
    public int repeatedStringMatch3(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int ans = 0;
        while (sb.length() < b.length() && ++ans > 0) sb.append(a);
        sb.append(a);
        int idx = sb.indexOf(b);
        if (idx == -1) return -1;
        return idx + b.length() > a.length() * ans ? ans + 1 : ans;
    }


    // 利用kmp代替上面的indexOf，可以让速度更快
    public int strStr(String ss, String pp) {
        if (pp.isEmpty()) return 0;

        // 分别读取原串和匹配串的长度
        int n = ss.length(), m = pp.length();
        // 原串和匹配串前面都加空格，使其下标从 1 开始
        ss = " " + ss;
        pp = " " + pp;

        char[] s = ss.toCharArray();
        char[] p = pp.toCharArray();

        // 构建 next 数组，数组长度为匹配串的长度（next 数组是和匹配串相关的）
        int[] next = new int[m + 1];
        // 构造过程 i = 2，j = 0 开始，i 小于等于匹配串长度 【构造 i 从 2 开始】
        for (int i = 2, j = 0; i <= m; i++) {
            // 匹配不成功的话，j = next(j)
            while (j > 0 && p[i] != p[j + 1]) j = next[j];
            // 匹配成功的话，先让 j++
            if (p[i] == p[j + 1]) j++;
            // 更新 next[i]，结束本次循环，i++
            next[i] = j;
        }

        // 匹配过程，i = 1，j = 0 开始，i 小于等于原串长度 【匹配 i 从 1 开始】
        for (int i = 1, j = 0; i <= n; i++) {
            // 匹配不成功 j = next(j)
            while (j > 0 && s[i] != p[j + 1]) j = next[j];
            // 匹配成功的话，先让 j++，结束本次循环后 i++
            if (s[i] == p[j + 1]) j++;
            // 整一段匹配成功，直接返回下标
            if (j == m) return i - m;
        }
        return -1;
    }


    // 也可以用计算哈希值的方式，来代替indexOf
    // 比较目标串ss的某一个字串和b的哈希值是否相等来表示是否匹配，以及返回对应首位置
    public int strHash(String ss, String b) {
        int P = 131;
        int n = ss.length(), m = b.length();
        String str = ss + b;
        int len = str.length();
        int[] h = new int[len + 10], p = new int[len + 10];
        h[0] = 0; p[0] = 1;
        for (int i = 0; i < len; i++) {
            p[i + 1] = p[i] * P;
            h[i + 1] = h[i] * P + str.charAt(i);
        }
        int l = len - m + 1;
        int target = h[len] - h[l - 1] * p[len - l + 1]; // b 的哈希值
        for (int i = 1; i <= n; i++) {
            int j = i + m - 1;
            int cur = h[j] - h[i - 1] * p[j - i + 1]; // 子串哈希值
            if (cur == target) return i - 1;
        }
        return -1;
    }
}
