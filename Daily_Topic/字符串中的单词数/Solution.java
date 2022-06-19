package Daily_Topic.字符串中的单词数;

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().countSegments("Hello,my name is John"));
    }

    //这里的单词指的是连续的不是空格的字符
    public int countSegments(String s) {
        s = s.trim();
        if (s.length()==0) return 0;
        int num = 0;
        int len = s.length();
        for(int i=0; i<len; i++){
            // 过掉挨在一起的空格
            // 因为两端的空格是去掉了的，所以空格++不用判断越界
            while (s.charAt(i)==' ')
                i++;

            // 空格走完就有单词会出现
            num++;

            // 过掉挨在一起的字符，除了空格的
            // 在++的时候要判断是否越界
            while (i<len && s.charAt(i)!=' ')
                i++;

        }
        return num;
    }
}
