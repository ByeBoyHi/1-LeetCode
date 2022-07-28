package Easy.数字的补数;

public class Solution {
    public static void main(String[] args) {

    }

    public int findComplement(int num) {
        int ans = 0;
        int cur = 0;
        while (num!=0){
            if (num%2==0){
                ans = ans + (1<<cur);
            }
            cur++;
            num = num>>1;
        }
        return ans;
    }
}
