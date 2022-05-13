package Middle.乘积小于K的子数组;

public class SubArrayProductLessK {
    // 1000ms
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                if (i == j) {
                    if (nums[i] < k) {
                        ans++;
                        sum = nums[i];
                    } else {
                        break;
                    }
                } else {
                    if (sum * nums[j] < k) {
                        ans++;
                        sum *= nums[j];
                    } else {
                        break;
                    }
                }
            }
        }
        return ans;
    }

    /*
        要计算连乘小于k，那么可以取对数，实现求连续对数和小于 log k。
        那么我们可以求从0开始往后的累加对数后，然后所有所有比log k小的区间。
        由于取对数后，double只能保证15位有效数字是精确的，因此需要加上10^(-10)来避免这个误差。

        在[0, j]区间，找一个l，使得[l, j]区间和小于 log k。
        即： logPrefix[j]-logPrefix[l]<log k
        --> logPrefix[l]>logPrefix[j]-log k + 10^(-10)
        那么找到最小的符合上述不等式的l，[l,j]之间的个数就是该区间达标的个数。

        为了便于理解，做出以下例子：
            比如，[l,j]：指的就是从l连乘到j。
                 [l+1,j]：指的就是从l+1连乘到j
                 ...
            这之间的和指的就是有多少个位置可以连乘到j，且达标。
     */
    public int log(int[] nums, int k){
        int n = nums.length;
        if (k==0) return 0;  // 所有数字都是正数
        // 记录前缀和
        double[] prefixLog = new double[n+1];
        for (int i=0; i<n; i++){
            prefixLog[i+1] = prefixLog[i] + Math.log(nums[i]);
        }
        double logk = Math.log(k);
        int ret = 0;
        for (int j=0; j<n; j++){
            int l = 0;
            int r = j+1;  // j+1指的是prefixLog的第j个
            int idx = j+1;
            double val = prefixLog[j+1]-logk+Math.pow(10,-10);
            while (l<=r){
                int mid = l + ((r-l)>>1);
                if (prefixLog[mid]>val){
                    r = mid-1;
                    idx = mid;
                }else{
                    l = mid+1;
                }
            }
            ret += j+1-idx;
        }
        return ret;
    }

    // 滑动窗口
    /*
        依然固定右侧的位置j，在左侧找第一个临界点，在临界点左侧，不达标，在临界点右侧，达标
     */
    public static int SlideWindow(int[] nums, int k){
        if (k==0) return 0;
        int ret = 0;
        int prod = 1;  // 前一个乘积
        int j = 0;
        for (int i=0; i<nums.length; i++){
            prod*=nums[i];
            while (j<=i && prod>=k){
                prod/=nums[j];
                j++;
            }
            // 如果j>i，即j=i+1，那么这里+0不影响
            ret+=i-j+1;
        }
        return ret;
    }
}
