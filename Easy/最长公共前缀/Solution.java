package Easy.最长公共前缀;

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 * <p>
 * 示例 1:
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * <p>
 * 示例 2:
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 */
public class Solution {
    public static String longestCommonPrefix1(String[] strs) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < strs.length; i++) {
            if (strs[i].equals("")) {
                return "";
            }else {
                if (strs[i].length()<min){
                    min = strs.length;
                }
            }
        }
        StringBuffer stringBuffer = new StringBuffer();
        char[] c = strs[0].toCharArray();
        boolean b = true;
        int k = 1;
        for (int j = 0; j < min; j++) {
            while (k < strs.length) {
                char[] temp = strs[k].toCharArray();
                if (c[j] != temp[j]) {
                    b = false;
                    break;
                }
                k++;
            }
            if (b==true) {
                stringBuffer.append(c[j]);
                k=1;
            } else {
                break;
            }
        }

        return stringBuffer.toString();
    }

    public static String longestCommonPrefix2(String[] strs){
        if (strs.length==0){
            // 如果数组为空，则返回空串
            return "";
        }
        // 把字符串数组的第一个存下来，依次对后面的进行比较
        String res = strs[0];
        for (int i=1;i<strs.length;i++){
            int j=0;
            for (;j<res.length() && j<strs[i].length(); j++){
                if (res.charAt(j) != strs[i].charAt(j)){
                    // 如果字符串的第j个位置相等，就继续比下去
                    // 如果不相等，就break，退出内层循环
                    break;
                }
            }
            // 上述for 循环比较晚了后，将从0到j之间的所有元素存下来（因为这部分是break之前的公共前缀，且不包括j位的元素）
            res = res.substring(0,j);
            if (res.equals("")){
                // 如果出现没有公共前缀的，则直接返回空串
                return "";
            }
        }
        return res;
    }

    public static String longestCommonPrefix3(String[] strs) {
        int len = strs.length;
        if (len==0) return "";
        if (len==1) return strs[0];
        String res = strs[0];
        for (int i=1; i<strs.length; i++){
            while (!strs[i].startsWith(res)){
                res = res.substring(0, res.length()-1);
            }
        }
        return res;
    }

        public static void main(String[] args) {
            String[] s = {"flower", "flow", "flight"};
        System.out.println(longestCommonPrefix3(s));
//        System.out.println(longestCommonPrefix2(s));
    }
}
