package Daily_Topic.Convert_To_Hexadecimal;

public class Solution {
    public static void main(String[] args) {
        System.out.println(Solution.toHex(-2653151));
    }
    public static String toHex(int num) {
        if (num == 0) return "0";
        char[] c = new char[]{'a', 'b', 'c', 'd', 'e', 'f'};

        StringBuilder s = new StringBuilder();
        while (num!=0 && s.length()<8){
            int cur = num&15;
            if (cur>9){
                s.append(c[cur-10]);
            }else {
                s.append(cur);
            }
            num = num>>4;
        }
        return s.reverse().toString();
    }
}
