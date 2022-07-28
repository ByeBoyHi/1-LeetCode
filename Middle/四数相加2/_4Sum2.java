package Middle.四数相加2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class _4Sum2 {

    // TLE
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int n = nums1.length;
        if (n==0) return 0;
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        Arrays.sort(nums3);
        Arrays.sort(nums4);

        int ans = 0;
        int pre = 0;
        for (int i=0; i<n; i++){
            if (i>0 && nums1[i]==nums1[i-1]){
                ans+=pre;
                continue;
            }
            pre = 0;
            for (int j=0; j<n; j++){
                for (int k=0; k<n; k++){
                    for (int m=0; m<n; m++){
                        int cur = nums1[i]+nums2[j]+nums3[k]+nums4[m];
                        if (cur>0
                                ||
                                nums1[i]+nums2[j]+nums3[k]+nums4[n-1]<0
                                ||
                                nums1[i]+nums2[j]+nums3[k]+nums4[0]>0){
                            break;
                        }
                        if (cur==0){
                            ans++;
                            pre++;
                        }
                    }
                }
            }
        }
        return ans;
    }

    // 分组：A B一组，C D一组
    // 用一个哈希表把A+B的值算出来做个记录，然后取算C+D是否能找到-(C+D)
    public int fourSumCount2(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int n = nums1.length;
        if (n==0) return 0;
        int ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int k : nums1) {
            for (int j: nums2) {
                int cur = k + j;
                map.put(cur, map.getOrDefault(cur, 0) + 1);
            }
        }
        for (int k : nums3) {
            for (int j: nums4) {
                if (map.containsKey(-(k+j))){
                    ans+=map.get(-(k+j));
                }
            }
        }
        return ans;
    }
}
