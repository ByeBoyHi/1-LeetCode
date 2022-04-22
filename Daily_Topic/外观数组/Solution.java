package Daily_Topic.外观数组;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().countAndSay(5));
    }

    public String countAndSay(int n) {
        if (n==1){
            return "1";
        }
        String str = countAndSay(n-1);
        StringBuilder ans = new StringBuilder();
        char cur = '*';
        int num = 0;
        for (int i=0; i<str.length(); i++){
            if (cur=='*'){
                cur = str.charAt(i);
            }
            if (str.charAt(i)==cur){
                num++;
            }
            if(i!=str.length()-1 && str.charAt(i+1)!=cur){
                ans.append(num).append(cur);
                num=0;
                cur='*';
            }
        }
        ans.append(num).append(cur);
        return ans.toString();
    }
}
