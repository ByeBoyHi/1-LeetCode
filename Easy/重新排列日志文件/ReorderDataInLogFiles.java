package Easy.重新排列日志文件;

import java.util.Arrays;

public class ReorderDataInLogFiles {
    /*
        字母日志在数字日志之前
        字母日志之间按照字母顺序排，数字日志之间保留原来的顺序
     */
    public String[] reorderLogFiles(String[] logs) {
        Arrays.sort(logs, (log1, log2) -> {  // lambda排序
            String[] split1 = log1.split(" ", 2);
            String[] split2 = log2.split(" ", 2);
            boolean isDigit1 = Character.isDigit(split1[1].charAt(0));
            boolean isDigit2 = Character.isDigit(split2[1].charAt(0));
            // 都是字母的情况下
            if (!isDigit1 && !isDigit2) {
                int cmp = split1[1].compareTo(split2[1]);
                if (cmp != 0) return cmp;
                // 比较前面是因为就算都是字母日志，前面可能前缀不一样
                // 比如同样的字母日志，大家用的标志前缀可能不一样
                return split1[0].compareTo(split2[0]);
            }
            // 其中有一个是数字
            // 负数在前面，正数在后
            // 因为小于的话，compareTo返回负值，就会被放在前面，这里如果是字母日志，就返回负值，放在前面。
            return isDigit1 ? (isDigit2 ? 0 : 1) : -1;
        });
        return logs;
    }
}
