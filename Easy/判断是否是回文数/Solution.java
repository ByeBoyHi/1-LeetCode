package Easy.判断是否是回文数;

class Solution {
    public static boolean isPalindrome1(int x) {
        StringBuffer s1 = new StringBuffer(x + "");
        String str1 = s1.toString();
        StringBuffer s2 = s1.reverse();
        String str2 = s2.toString();
        System.out.println(str1);
        System.out.println(str2);
        return str1.equals(str2);
    }

    public static boolean isPalindrome2(int x) {
        String s1 = x+"";
        System.out.println(s1);
        char[] c = s1.toCharArray();
        for (int i=0; i<c.length/2;i++){
            char temp = c[i];
            c[i] = c[c.length-1-i];
            c[c.length-1-i] = temp;
        }
        String s2 = new String(c);
        System.out.println(s2);
        return s1.equals(s2);
    }

    public static boolean isPalindrome3(int x){
        String s = x+"";
        System.out.println(s);
        String res = reverse(s);
        System.out.println(res);
        return s.equals(res);
    }

    public static String reverse(String s){
        char[] c = s.toCharArray();
        StringBuffer s2 = new StringBuffer();
        for (int i=c.length-1; i>=0 ;i--){
            s2.append(c[i]);
        }
        String res = s2.toString();
        return res;
    }

    public static void main(String[] args) {
        boolean b = isPalindrome1(-1213121);
        System.out.println(b);
        boolean b1 = isPalindrome2(-1113111);
        System.out.println(b1);
        boolean b2 = isPalindrome3(1113111);
        System.out.println(b2);
    }
}
