package Easy.实现strStr;

/**
 * java.lang.ArrayIndexOutOfBoundsException: Index 0 out of bounds for length 0
 *   at line 12, Solution.strStr
 *   at line 54, __DriverSolution__.__helper__
 *   at line 87, __Driver__.main
 */

/**
 * 实现 strStr() 函数。
 * <p>
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回 -1。
 * <p>
 * 示例 1:
 * <p>
 * 输入: haystack = "hello", needle = "ll"
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: haystack = "aaaaa", needle = "bba"
 * 输出: -1
 * 说明:
 * <p>
 * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 * <p>
 * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
 */

class Solution {

    public static void main(String[] args) {
        String h = "mississippi";
        String n = "issip";
        System.out.println(new Solution().strStr2(h, n));
    }

    // 1. 暴力破解：会超出时间限制
    public static int strStr1(String haystack, String needle) {
        int key = 0;
        int count = 0;
        for (int i = 0; i < haystack.length(); i++) {
            count = i;
            for (int j = 0; j < needle.length(); j++) {
                if (i < haystack.length() && haystack.charAt(i) == needle.charAt(j)) {
                    key++;
                    i++;
                }
            }
            if (key == needle.length())
                break;
            else
                key = 0;
            i = count;
        }
        if (key == needle.length())
            return count;
        else
            return -1;
    }

    // 2. 拒绝回溯
    public int strStr2(String haystack, String needle) {
        if (needle==null || haystack==null)
            return -1;
        if (haystack.equals("")){
            if (needle.equals(""))
                return 0;
            else return -1;
        } else if(needle.equals("")){
            return 0;
        }
        // 1. 构建next数组，避免回溯
        int[] next = new int[needle.length()];
        next[0] = -1;
        int i = 1, j = -1;
        while (i < next.length) {
            if (j == -1 || needle.charAt(i) == needle.charAt(j)) {
                j++;
                if (j < needle.length() - 1) {
                    next[i] = j;
                }
            } else {
                j = next[j];
            }
            i++;
        }

        // 2. 进行判断
        i = 0;
        for (j = 0; j < needle.length(); ) {
            if (j == -1) {
                j++;
                i++;
            }
            if (i < haystack.length()) {
                if (haystack.charAt(i) != needle.charAt(j)) {
                    j = next[j];
                } else {
                    i++;
                    j++;
                }
            }
            if (haystack.length() - i < needle.length() - j) {
                break;
            }
        }

        return j == needle.length() ? (i - j) : -1;
    }
}
