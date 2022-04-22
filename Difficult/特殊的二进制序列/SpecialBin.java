package Difficult.特殊的二进制序列;

import java.util.ArrayList;
import java.util.List;

public class SpecialBin {
    /*
        对于每一次拼接，都要求是特殊二进制： 1 和 0 的数量一样
        1. 可以记录并且找到每一个特殊二进制
        2. 对找到的二进制进行排序
        3. 再依次拼接，就可以得到一个字典序最大的序列
        4. base case 是 字符串长度小于 2
     */
    public String makeLargestSpecial(String s) {
        if (s.length() < 2) return s;

        // 用来存储每一个特殊二进制
        List<String> list = new ArrayList<>();
        // 用于最后拼接返回
        StringBuilder sb = new StringBuilder();
        // 开始位置
        int start = 0;
        // 计数器，记录当前是否已经是一个特殊二进制
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            count += s.charAt(i) == '1' ? 1 : -1;  // Increase if is 1 or decrease.
            if (count == 0) {  // 当前是一个特殊的二进制
                String sub = s.substring(start + 1, i);  // 这里的取值范围去掉了头和尾的 1 和 0
                String res = makeLargestSpecial(sub);  // 对子串进行处理
                list.add("1" + res + "0");  // 对处理好的子串加进列表里面
                start = i + 1;  // 从新的位置继续找
            }
        }
        String[] strings = list.toArray(new String[list.size()]);
        quickSort(strings, 0, strings.length - 1);
        for (String str : strings) {
            sb.append(str);
        }
        return sb.toString();
    }

    // 快速排序：从大到小排序
    public void quickSort(String[] strs, int low, int high) {
        int i = low;  // 向后搜索的指针
        int j = high; // 向前搜索的指针
        String tmp = strs[i];
        while (i < j) {  // 找partition
            while (i < j && strs[j].compareTo(tmp) <= 0) {
                j--;
            }
            if (i < j) {
                strs[i++] = strs[j];
            }
            while (i < j && strs[i].compareTo(tmp) >= 0) {
                i++;
            }
            if (i < j) {
                strs[j--] = strs[i];
            }
        }
        strs[i] = tmp;  // 放入当前位置
        if (low < i - 1) {   // 处理左区间：等于i-1的时候不用处理，因为左边只有一个元素
            quickSort(strs, low, i - 1);
        }
        if (high > i + 1) {  // 处理右区间：等于i+1的时候不用处理，因为右边只有一个元素
            quickSort(strs, i + 1, high);
        }
    }
}
