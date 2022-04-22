package Difficult.强密码检验器;

public class PwdChecker {

    /*
        修改在于记录操作次数，不必真正的去修改这个字符串

        未修复："bbaaaaaaaaaaaaaaacccccc"
        优先删除 k mod 3 == 0 的字符。
     */
    public int strongPasswordChecker(String password) {
        int len = password.length();
        if (len < 3) return 6 - len;  // 小于3说明没有3个连续的，且后面补的也可以是三种不同的字符
        int digit = 0;  // 记录数字的个数
        int small = 0;  // 记录小写字母的个数
        int big = 0;    // 记录大写字母个数
        int rep = 1;    // 记录当前字符的连续次数
        int ans = 0;    // 记录答案
        int total = 0;  // 记录插入和替换操作的总次数

        // 讨论第一个字符是什么类型，便于直接从第一个判断连续字符
        char c = password.charAt(0);
        if (c >= 'a' && c <= 'z') {
            small++;
        } else if (c >= 'A' && c <= 'Z') {
            big++;
        } else if (c >= '0' && c <= '9') {
            digit++;
        }

        int newLen = len;  // 假如len的长度没有在合理范围范围内，那么记录在删除插入操作后，新的长度

        for (int i = 1; i < len; i++) {
            if (password.charAt(i) == password.charAt(i - 1)) {
                rep++;  // 出现重复字符
                if (rep >= 3) {  // 有三个重复了
                    ans++; // 修改当前字符
                    if (len > 20) {  // 如果密码长度大于20，优先删除，删除后继续判断当前字符，当前字符被删除后，我们应该去判断下一个字符
                        newLen--;
                        // 可能出现 aaaa --> aaa
                    } else if (len < 6) {  // 如果长度小于6，优先插入，插入新的字符后，判断下一个字符，因为当前字符因为插入操作已经安全
                        newLen++;
                        total++;
                        rep = 1;
                    } else {
                        i++;  // 否则，就不删除，直接替换，i跳过当前字符：发生替换，必然是不止一个同类字符，所以可以换成别的
                        total++;
                        rep = 1;
                    }
                }

            } else {  // 一切安全，i正常++
                rep = 1;  // 出现新的字符
                // 记录字符是否和标准
                c = password.charAt(i);
                if (c >= 'a' && c <= 'z') {
                    small++;
                } else if (c >= 'A' && c <= 'Z') {
                    big++;
                } else if (c >= '0' && c <= '9') {
                    digit++;
                }
            }
        }

        int newT = 0;  // 记录多的插入次数
        // 操作完了之后新的长度
        if (newLen < 6) {
            newT += (6 - newLen); // 因为长度不够，可以再次进行这么多次插入操作
        }

        // 如果有一种字符还没有出现，就用中间的插入替换操作来做，否则就需要总的操作次数+1
        if (digit == 0) {
            if (total > 0) {  // 之前的操作有剩下的插入替换
                total--;
            } else if (newT > 0) {
                newT--;
                newLen++;
                ans++;
            } else {
                ans++;
            }
        }
        if (small == 0) {
            if (total > 0) {
                total--;
            } else if (newT > 0) {
                newT--;
                newLen++;
                ans++;
            } else {
                ans++;
            }
        }
        if (big == 0) {
            if (total == 0) {
                if (newT > 0) {
                    newLen++;
                }
                ans++;
            }
        }

        // 操作完之后，还缺长度
        if (newLen < 6) {
            ans += (6 - newLen);
        }

        // 多出来的长度
        if (newLen > 20) {
            ans += (newLen - 20);
        }

        return ans;
    }
}


// 官解
class Solution {
    public int strongPasswordChecker(String password) {
        int n = password.length();
        int hasLower = 0, hasUpper = 0, hasDigit = 0;
        for (int i = 0; i < n; ++i) {
            char ch = password.charAt(i);
            if (Character.isLowerCase(ch)) {
                hasLower = 1;
            } else if (Character.isUpperCase(ch)) {
                hasUpper = 1;
            } else if (Character.isDigit(ch)) {
                hasDigit = 1;
            }
        }
        int categories = hasLower + hasUpper + hasDigit;

        if (n < 6) {
            return Math.max(6 - n, 3 - categories);
        } else if (n <= 20) {
            int replace = 0;
            int cnt = 0;
            char cur = '#';

            for (int i = 0; i < n; ++i) {
                char ch = password.charAt(i);
                if (ch == cur) {
                    ++cnt;
                } else {
                    replace += cnt / 3;
                    cnt = 1;
                    cur = ch;
                }
            }
            replace += cnt / 3;
            return Math.max(replace, 3 - categories);
        } else {
            // 替换次数和删除次数
            int replace = 0, remove = n - 20;
            // k mod 3 = 1 的组数，即删除 2 个字符可以减少 1 次替换操作
            int rm2 = 0;
            int cnt = 0;
            char cur = '#';

            for (int i = 0; i < n; ++i) {
                char ch = password.charAt(i);
                if (ch == cur) {
                    ++cnt;
                } else {
                    if (remove > 0 && cnt >= 3) {
                        if (cnt % 3 == 0) {
                            // 如果是 k % 3 = 0 的组，那么优先删除 1 个字符，减少 1 次替换操作
                            --remove;
                            --replace;
                        } else if (cnt % 3 == 1) {
                            // 如果是 k % 3 = 1 的组，那么存下来备用
                            ++rm2;
                        }
                        // k % 3 = 2 的组无需显式考虑
                    }
                    replace += cnt / 3;
                    cnt = 1;
                    cur = ch;
                }
            }
            if (remove > 0 && cnt >= 3) {
                if (cnt % 3 == 0) {
                    --remove;
                    --replace;
                } else if (cnt % 3 == 1) {
                    ++rm2;
                }
            }
            replace += cnt / 3;

            // 可以看 img_2
            // 使用 k % 3 = 1 的组的数量，由剩余的替换次数、组数和剩余的删除次数共同决定
            int use2 = Math.min(Math.min(replace, rm2), remove / 2);
            replace -= use2;
            remove -= use2 * 2;
            // 由于每有一次替换次数就一定有 3 个连续相同的字符（k / 3 决定），因此这里可以直接计算出使用 k % 3 = 2 的组的数量
            int use3 = Math.min(replace, remove / 3);
            replace -= use3;
            return (n - 20) + Math.max(replace, 3 - categories);
        }
    }
}