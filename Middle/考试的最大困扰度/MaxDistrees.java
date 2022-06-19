package Middle.考试的最大困扰度;

public class MaxDistrees {
    public int maxConsecutiveAnswers(String answerKey, int k) {
        return Math.max(process(answerKey, 'T', k), process(answerKey, 'F', k));
    }

    // 滑动窗口
    public int process(String str, char ch, int k) {
        int sum = 0;  // 记录在 left 到 right 区间，有多少个 非ch 字符，且不能超过k个
        int ans = 0;  // 记录在整个过程中最长的连续字符 ch
        int n = str.length();
        for (int left=0, right=0; right<n; right++){
            sum += str.charAt(right)!=ch?1:0;  // 当前不是ch，就+1，表示多了一个可以变成ch的字符
            while (sum>k){  // 只要可变字符数量超过k，就需要减小窗口大小，在新的窗口重新统计
                // 划出去的字符如果是 ch，那么说明我们需要变的字符数量没有变，则减零，接着往下找
                sum-=str.charAt(left++)!=ch?1:0;
            }
            // 每次都统计新的窗口大小和上次的窗口大小谁的更大
            ans = Math.max(ans, right-left+1);
        }
        return ans;
    }

    //  自滑动
    public int maxConsecutiveAnswers2(String answerKey, int k) {
        char[] cnt = new char[128];
        int i = 0, max = 0, n = answerKey.length();
        char[] A = answerKey.toCharArray();
        for (int j = 0; j < n; j++) {
            cnt[A[j]]++;
            // 获取目前最长的数目
            max = Math.max(max, cnt[A[j]]);
            while (j + 1 - i - max > k) {
                cnt[A[i]]--;
                i++;
            }
        }
        return n - i;
    }
}
