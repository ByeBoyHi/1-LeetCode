package Middle.最优除法;

public class OptDivision {

    // 数学思路
    // 要使添加任何括号后，最终结果最大，那么保留第一个数字，让剩下的数字挨个除完，使得分母最大，分子最小，最终即最大
    // 注：因为每个数字都是大于1的数字
    public String optimalDivision(int[] nums) {
        int n = nums.length;
        if (n==1){
            return nums[0]+"";
        }
        if (n==2){
            return nums[0]+"/"+nums[1];
        }
        StringBuilder sb = new StringBuilder();
        sb.append(nums[0]).append("/").append("(");
        for (int i=1; i<n; i++){
            sb.append(nums[i]).append("/");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append(")");
        return sb.toString();
    }
}

