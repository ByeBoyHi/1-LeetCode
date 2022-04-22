package Easy.检测大写字母;

public class Solution {
    /**
     * 只有以下三种情况的单词是合理的：
     * 1. 全部字母都是小写
     * 2. 全部字母都是大写
     * 3. 首字母大写，其他小写
     */
    public boolean detectCapitalUse(String word) {
        // 用一个变量记录首字母是否大写
        boolean isUpper = (word.charAt(0) >= 'A' && word.charAt(0) <= 'Z');
        int len = word.length();
        for (int i = 1; i < len; i++) {
            boolean b = word.charAt(i) >= 'A' && word.charAt(i) <= 'Z';
            if (isUpper){  // 首字母大写
                if (i!=1) {
                    // 全大写
                    if (
                            b  // 当前字母大写
                                    &&
                                    !(word.charAt(i - 1) >= 'A' && word.charAt(i - 1) <= 'Z')  // 前一个字母小写
                    ) {
                        return false;
                        // 只有首字母大写
                    } else if (
                            (word.charAt(i) >= 'a' && word.charAt(i) <= 'z')  // 当前字母小写
                                    &&
                                    !(word.charAt(i - 1) >= 'a' && word.charAt(i - 1) <= 'z')   // 前一个字母大写
                    ) {
                        return false;
                    }
                }
            }else {  // 首字母小写
                if (b){  //其他字母出现了大写
                    return false;
                }
            }
        }
        return true;
    }
}
