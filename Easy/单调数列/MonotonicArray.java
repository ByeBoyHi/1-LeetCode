package Easy.单调数列;

public class MonotonicArray {
    public boolean isMonotonic(int[] nums) {
        if (nums.length==1) return true;
        int x = 0;
        int y = 1;
        while (y<nums.length && nums[x]==nums[y]){
            x++;
            y++;
        }
        if(y==nums.length) return true;
        boolean flag = nums[x]>nums[y];
        for (int i=y; i<nums.length; i++){
            if ((nums[i]-nums[i-1]>0 && flag)
                    ||
                (nums[i]-nums[i-1]<0 && !flag)){
                return false;
            }
        }
        return true;
    }
}
