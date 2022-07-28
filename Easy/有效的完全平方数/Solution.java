package Easy.有效的完全平方数;

public class Solution {
    public boolean isPerfectSquare(int num) {
        int n = (int)Math.sqrt(num);
        return n*n==num;
    }

    // 不用sqrt实现
    // 1 <= num <= MAX_VALUE
    // 暴力
    public boolean isPerfectSquare2(int num) {
        long square = 1;
        int i = 1;
        while (square<num){
            i++;
            square = (long)i*i;
        }
        return square==num;
    }

    // 二分
    public boolean isPerfectSquare3(int num){
        int low = 1;
        int high = num;
        while (low<=high){
            int mid = low + ((high - low) >> 1);
            long square = (long) mid *mid;
            if (square==num){
                return true;
            }else if (square<num){
                low = mid+1;
            }else {
                high = mid-1;
            }
        }
        return false;
    }
}
