package 动态规划.中等.乘积为为正数的最长子数组长度;

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().getMaxLen1(new int[]{
                5,-20,-20,-39,-5,0,0,0,36,-32,0,7,-10,-7,21,20,-12,-34,26,2
        }));
    }

    // 暴力破解
    public int getMaxLen(int[] nums) {
        if (nums.length==1){
            if (nums[0]>0) return 1;
            else return 0;
        }
        int ans = 0;
        int j;
        int flag;
        for (int i=nums.length-1; i>=0; i--){
            flag = 1;
            j = i;
            if (i<ans){
                break;
            }
            while (j>=0 && nums[j]!=0){
                if (nums[j]*flag>0){
                    ans = Math.max(ans, i-j+1);
                }
                flag = flag*nums[j]>0?1:-1;
                j--;
            }
        }
        return ans;
    }

    // 动态规划
    /**
     * 定义两个变量：positive和negative，用来保存当前位置结尾的最长子数组长度，positive记录乘积为正的长度，negative记录乘积为负的长度
     * 再用一个maxLen记录最大长度。
     * 当nums[i]>0的时候，不影响正负性：
     *      positive = positive+1
     *      negative = negative+1, negative>0的时候；或者 = 0, negative=0的时候。
     * 当nums[i]<0的时候，影响正负：
     *      negative = positive+1
     *      positive = negative+1, negative>0的时候；或者 = 0, negative=0的时候。
     * 当nums[i]=0的时候：negative=positive=0
     * 初始：i=0的时候，nums[0]>0 --> positive=1; nums[0]<0 --> negative=1
     */
    public int getMaxLen1(int[] nums){
        int positive = nums[0]>0?1:0;
        int negative = nums[0]<0?1:0;
        int ans = positive;  // 直接赋值为positive，因为这就是最开始的答案
        for (int i=1; i<nums.length; i++){
            if (nums[i]>0){
                positive = positive+1;
                if (negative>0){
                    negative = negative+1;
                }else {
                    negative = 0;
                }
            }else if (nums[i]<0){
                // 这里需要两个新的变量存储一下之前的值，防止值变化导致结果出现问题。
                int oldPositive = positive;
                int oldNegative = negative;
                if (oldNegative>0) {
                    positive = oldNegative+1;
                }else {
                    positive = 0;
                }
                negative = oldPositive+1;
            }else{
                positive = 0;
                negative = 0;
            }
            ans = Math.max(ans, positive);  // 每次都要更新一下最长长度
        }
        return ans;
    }
}
