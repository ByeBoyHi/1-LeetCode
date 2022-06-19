package Easy.字符的最短距离;

public class ShortestToChar {
    /*
        两个数组，左右各遍历一遍
        第一个存储，左边离自己最近的c的距离
        第二个存储，右边离自己最近的c的距离
     */
    public int[] shortestToChar(String s, char c) {
        int len = s.length();
        int[] left = new int[len];
        int[] right = new int[len];
        left[0] = s.charAt(0)==c?0:0x3f3f3f3f;  // 一定不会出现的极大值（注意，不要用最大值，因为后面的循环里面有+1操作，可能从最大变成最小）
        right[len-1] = s.charAt(len-1)==c?0:0x3f3f3f3f;

        for (int i = 1; i < len; i++) {
            if (s.charAt(i)==c) {
                left[i] = 0;  // 如果自己就是c，那么距离是0
            }else {
                // 否则距离就是前一个距离的+1
                left[i] = left[i-1]+1;
            }
        }

        for (int i = len-2; i >= 0; i--) {
            if (s.charAt(i)==c){
                right[i] = 0;
            }else {
                right[i] = right[i+1]+1;
            }
        }

        int[] ans = new int[len];
        for (int i = 0; i < len; i++) {
            ans[i] = Math.min(left[i], right[i]);
        }
        return ans;
    }
}
