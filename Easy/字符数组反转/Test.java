package Easy.字符数组反转;

public class Test {
    public static void main(String[] args){
        char[] s = {'H','a','n','n','a','h'};
        reverseString(s);
        System.out.println(s);
    }

    public static void reverseString(char[] s) {
        for (int i=0; i<s.length/2; i++){
            char  temp = s[i];
            s[i] = s[s.length-1-i];
            s[s.length-1-i] = temp;
        }
    }
}