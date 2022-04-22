package Easy.至少是其他数字两倍的最大数;

public class AtLeastTwiceOfOthers {
    public int dominantIndex(int[] nums) {
        int max = -1;
        int idx = 0;
        int n = nums.length;
        for (int i=0; i<n; i++){
            if (nums[i]>max){
                max = nums[i];
                idx = i;
            }
        }
        boolean flag = false;
        for (int num: nums){
            if (num==max && !flag){
                flag = true;
            }else {
                if (num*num>max){
                    return -1;
                }
            }
        }
        return idx;
    }
}
