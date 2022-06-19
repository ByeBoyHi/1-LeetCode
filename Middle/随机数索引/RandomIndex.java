package Middle.随机数索引;

import java.util.*;

public class RandomIndex {
}

class Solution {

    Map<Integer, List<Integer>> map = new HashMap<>();

    // 预处理
    // 时间复杂度：O(n)
    public Solution(int[] nums) {
        for (int i=0; i<nums.length; i++) {
            if (map.containsKey(nums[i])){
                map.get(nums[i]).add(i);
            }else{
                map.put(nums[i], new ArrayList<>());
                map.get(nums[i]).add(i);
            }
        }
    }

    public int pick(int target) {
        List<Integer> list = map.get(target);  // 获取到对应的数组
        return list.get((int)(Math.random()*list.size()));
    }
}

// 水塘抽样
class Solution2{
    int[] nums;
    Random random;

    public Solution2(int[] nums) {
        this.nums = nums;
        this.random = new Random();
    }

    /*
        T(k) = 1/2 * 2/3 * 3/4 * ... * 1/k * k/(k+1) * (k+1)/(k+2) * ... * (n-1)/n = 1/n
        也就是选中第k个的概率是1/n，是n的均分。
        整个的意思是：前k-1个没有选上，k往后的也没有选上，只有k选上了
     */
    public int pick(int target) {
        int cnt = 0; // 记录出现target的次数
        int res = 0;
        for (int i=0; i<nums.length; i++){
            if (nums[i]==target){
                cnt++;
                if (random.nextInt(cnt)==0){
                    res = i;
                }
            }
        }
        return res;
    }
}
