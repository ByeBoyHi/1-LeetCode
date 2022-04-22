package 周赛.第266场周赛.统计字符串中的元音子字符串;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().countVowelSubstrings("uieaouieaouieaouieaouieaouieaouieaouieaouieaouieaouieaouieaouieaouieaouieaouieaouieaouieaouieaouieao"));
    }
    public int countVowelSubstrings(String word) {
        int[] record = new int[5];
        Set<String> set = new HashSet<>();
        for (int k=0; k<word.length()-4; k++) {
            StringBuilder s = new StringBuilder();
            for (int i = k; i < word.length(); i++) {
                if (word.charAt(i) - 'a' == 0) {
                    if (s.length()==0){
                        s.append(i);
                    }
                    s.append('a');
                    record[0]++;
                    if (isEffect(record)) {
                        set.add(s.toString());
                    }
                } else if (word.charAt(i) - 'a' == 4) {
                    if (s.length()==0){
                        s.append(i);
                    }
                    record[1]++;
                    s.append('e');
                    if (isEffect(record)) {
                        set.add(s.toString());
                    }
                } else if (word.charAt(i) - 'a' == 8) {
                    if (s.length()==0){
                        s.append(i);
                    }
                    record[2]++;
                    s.append('i');
                    if (isEffect(record)) {
                        set.add(s.toString());
                    }
                } else if (word.charAt(i) - 'a' == 14) {
                    if (s.length()==0){
                        s.append(i);
                    }
                    record[3]++;
                    s.append('o');
                    if (isEffect(record)) {
                        set.add(s.toString());
                    }
                } else if (word.charAt(i) - 'a' == 20) {
                    if (s.length()==0){
                        s.append(i);
                    }
                    record[4]++;
                    s.append('u');
                    if (isEffect(record)) {
                        set.add(s.toString());
                    }
                } else {  // 出现了非元音
                    record = clear(record);  // 清理记录数组
                    s = new StringBuilder();
                }
            }
            record = clear(record);
        }
        return set.size();
    }

    private boolean isEffect(int[] arr){
        for (int j : arr) {
            if (j == 0) {
                return false;
            }
        }
        return true;
    }

    private int[] clear(int[] arr){
        return new int[arr.length];
    }
}
