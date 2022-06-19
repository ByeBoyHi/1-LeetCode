package Middle.最长快乐字符串;

import java.util.Arrays;

public class LongestHappyString {
    public String longestDiverseString(int a, int b, int c) {
        StringBuilder res = new StringBuilder();
        Pair[] pairs = new Pair[]{
                new Pair(a, 'a'),
                new Pair(b, 'b'),
                new Pair(c, 'c')
        };
        while (true){
            Arrays.sort(pairs, (x, y)->y.freq-x.freq);
            boolean hasNext = false;
            for (Pair pair: pairs){
                if (pair.freq<=0) break;  // 当前的都用完了，自然退出
                int m = res.length();
                if (m>=2 && res.charAt(m-2)==pair.ch && res.charAt(m-1)==pair.ch) continue;  // 当前再加入就重复了
                // 还能添加
                hasNext = true;
                // 加上去
                res.append(pair.ch);
                pair.freq--;
                break;  // 本次退出，需要重新排序
            }
            if (!hasNext) return res.toString();
        }
    }

    static class Pair{
        int freq;
        char ch;
        public Pair(int freq, char ch){
            this.freq = freq;
            this.ch = ch;
        }
    }
}
