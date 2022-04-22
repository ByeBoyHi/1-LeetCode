package Difficult.最长重复子串;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class LongestDupSubstring {
    // 官解
    public String longestDupSubstring(String s) {
        Random random = new Random();
        // 生成两个进制
        int a1 = random.nextInt(75) + 26;
        int a2 = random.nextInt(75) + 26;
        // 生成两个模
        int mod1 = random.nextInt(Integer.MAX_VALUE - 1000000007 + 1) + 1000000007;
        int mod2 = random.nextInt(Integer.MAX_VALUE - 1000000007 + 1) + 1000000007;
        int n = s.length();
        // 先对所有字符进行编码
        int[] arr = new int[n];
        for (int i = 0; i < n; ++i) {
            arr[i] = s.charAt(i) - 'a';
        }
        // 二分查找的范围是[1, n-1]
        int l = 1, r = n - 1;
        int length = 0, start = -1;
        while (l <= r) {
            int m = l + (r - l + 1) / 2;
            int idx = check(arr, m, a1, a2, mod1, mod2);
            if (idx != -1) {
                // 有重复子串，移动左边界
                l = m + 1;
                length = m;
                start = idx;
            } else {
                // 无重复子串，移动右边界
                r = m - 1;
            }
        }
        return start != -1 ? s.substring(start, start + length) : "";
    }

    public int check(int[] arr, int m, int a1, int a2, int mod1, int mod2) {
        int n = arr.length;
        long aL1 = pow(a1, m, mod1);
        long aL2 = pow(a2, m, mod2);
        long h1 = 0, h2 = 0;
        for (int i = 0; i < m; ++i) {
            h1 = (h1 * a1 % mod1 + arr[i]) % mod1;
            h2 = (h2 * a2 % mod2 + arr[i]) % mod2;
            if (h1 < 0) {
                h1 += mod1;
            }
            if (h2 < 0) {
                h2 += mod2;
            }
        }
        // 存储一个编码组合是否出现过
        Set<Long> seen = new HashSet<Long>();
        seen.add(h1 * mod2 + h2);
        for (int start = 1; start <= n - m; ++start) {
            h1 = (h1 * a1 % mod1 - arr[start - 1] * aL1 % mod1 + arr[start + m - 1]) % mod1;
            h2 = (h2 * a2 % mod2 - arr[start - 1] * aL2 % mod2 + arr[start + m - 1]) % mod2;
            if (h1 < 0) {
                h1 += mod1;
            }
            if (h2 < 0) {
                h2 += mod2;
            }

            long num = h1 * mod2 + h2;
            // 如果重复，则返回重复串的起点
            if (!seen.add(num)) {
                return start;
            }
        }
        // 没有重复，则返回-1
        return -1;
    }

    public long pow(int a, int m, int mod) {
        long ans = 1;
        long contribute = a;
        while (m > 0) {
            if (m % 2 == 1) {
                ans = ans * contribute % mod;
                if (ans < 0) {
                    ans += mod;
                }
            }
            contribute = contribute * contribute % mod;
            if (contribute < 0) {
                contribute += mod;
            }
            m /= 2;
        }
        return ans;
    }

}

// 叶总：哈希+二分
class Solution {
    long[] h, p;
    public String longestDupSubstring(String s) {
        int P = 1313131, n = s.length();
        h = new long[n + 10]; p = new long[n + 10];
        p[0] = 1;
        for (int i = 0; i < n; i++) {
            p[i + 1] = p[i] * P;
            h[i + 1] = h[i] * P + s.charAt(i);
        }
        String ans = "";
        int l = 0, r = n;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            String t = check(s, mid);
            if (t.length() != 0) l = mid;
            else r = mid - 1;
            ans = t.length() > ans.length() ? t : ans;
        }
        return ans;
    }
    String check(String s, int len) {
        int n = s.length();
        Set<Long> set = new HashSet<>();
        for (int i = 1; i + len - 1 <= n; i++) {
            int j = i + len - 1;
            long cur = h[j] - h[i - 1] * p[j - i + 1];
            if (set.contains(cur)) return s.substring(i - 1, j);
            set.add(cur);
        }
        return "";
    }
}

// 叶总：后缀数组
class Solution2 {
    int N = 30010;
    int[] x = new int[N], y = new int[N], c = new int[N];
    int[] sa = new int[N], rk = new int[N], height = new int[N];
    void swap(int[] a, int[] b) {
        int n = a.length;
        int[] c = a.clone();
        for (int i = 0; i < n; i++) a[i] = b[i];
        for (int i = 0; i < n; i++) b[i] = c[i];
    }
    public String longestDupSubstring(String s) {
        int n = s.length(), m = 128;
        s = " " + s;
        char[] cs = s.toCharArray();
        // sa：两遍基数排序，板子背不下来也可以直接使用 sort，复杂度退化到 n \log^2 n
        for (int i = 1; i <= n; i++) {
            x[i] = cs[i]; c[x[i]]++;
        }
        for (int i = 2; i <= m; i++) c[i] += c[i - 1];
        for (int i = n; i > 0; i--) sa[c[x[i]]--] = i;
        for (int k = 1; k <= n; k <<= 1) {
            int cur = 0;
            for (int i = n - k + 1; i <= n; i++) y[++cur] = i;
            for (int i = 1; i <= n; i ++) {
                if (sa[i] > k) y[++cur] = sa[i] - k;
            }
            for (int i = 1; i <= m; i++) c[i] = 0;
            for (int i = 1; i <= n; i++) c[x[i]]++;
            for (int i = 2; i <= m; i++) c[i] += c[i - 1];
            for (int i = n; i > 0; i--) {
                sa[c[x[y[i]]]--] = y[i];
                y[i] = 0;
            }
            swap(x, y);
            x[sa[1]] = cur = 1;
            for (int i = 2; i <= n; i++) {
                if (y[sa[i]] == y[sa[i - 1]] && y[sa[i] + k] == y[sa[i - 1] + k]) x[sa[i]] = cur;
                else x[sa[i]] = ++cur;
            }
            if (cur == n) break;
            m = cur;
        }
        // height
        int k = 0;
        for (int i = 1; i <= n; i ++) rk[sa[i]] = i;
        for (int i = 1; i <= n; i++) {
            if (rk[i] == 1) continue;
            int j = sa[rk[i] - 1];
            k = k > 0 ? k - 1 : k;
            while (i + k <= n && j + k <= n && cs[i + k] == cs[j + k]) k++;
            height[rk[i]] = k;
        }
        int idx = -1, max = 0;
        for (int i = 1; i <= n; i++) {
            if (height[i] > max) {
                max = height[i];
                idx = sa[i];
            }
        }
        return max == 0 ? "" : s.substring(idx, idx + max);
    }
}
