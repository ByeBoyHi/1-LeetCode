package Difficult.缺失的第一个正数;

public class FirstMissingPositive {
    public static void main(String[] args) {
        System.out.println(new FirstMissingPositive().firstMissingPositive2(new int[]{
                3,4,-1,1
        }));
    }

    // 置换：对于一个数组，里面的数字，我们要找出里面没有出现的最小正数
    // 假设长度是N，那么里面的数字可以放 1 到 N+1
    // 那么我们可以采取置换的方式
    // 假如出现了数字 5，那么就让5和索引4的数字交换
    // 从前往后遍历，只要有个正数位置没有出现交换，那么就是最小正整数
    // 给一个值记录一下边界外的最小值
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for (int i=0; i<n; ){
            // 1. 大于0
            // 2. 小于长度可放范围
            // 3. 不是之前出现的重复值
            // 做交换后，需要判断新来的数字，所以i不动
            if (nums[i]>0 && nums[i]<n+1 && nums[nums[i]-1]!=nums[i]){
                int temp = nums[nums[i]-1];
                nums[nums[i]-1] = nums[i];
                nums[i] = temp;
            }else { // 当前数字不符合要求，i后移判断下一位
                i++;
            }
        }
        for (int i=0; i<n-1; i++){
            // 当前不等，当前是最小正整数
            if (i+1!=nums[i]){
                return i+1;
            }
        }

        return nums[n-1]!=n?n:n+1;
    }

    /*
        负号占位思路：
            1. 因为数组的长度是N，那么我们只需要考虑 1到N 的数字范围即可。
            2. 也就是如果某个数字是小于0的，我们可以让他等于N+1。（只要大于N即可）
            3. 在一轮变化中，所有负数都被我们改造了，剩下的都是正数。
            4. 在都是正数的情况下，我们就可以用符号进行占位处理。
            5. 再次遍历数组，只要这个数字在1到N的范围，我们就让第N-1个位置的数字变成负数，如果已经是负数了就不管。
            6. 处理完一遍后，对应位置上1到N所有出现过的数字都被标记了负号。
            7. 再次遍历，如果1到N所有数字都变成了负号，说明1到N的数字都出现过，我们返回N+1。
            8. 否则返回第一个正数的下标索引+1。
            9. 我们就得到了一个最小的未出现的正整数。
     */
    public int firstMissingPositive2(int[] nums) {
        int n = nums.length;
        for (int i=0; i<n; i++){  // 1.处理所有非正数
            if (nums[i]<=0){
                nums[i] = n+1;
            }
        }

        // 2.处理所有合格数字为负数
        for (int i=0; i<n; i++){
            // 这里需要取绝对值
            // 因为前面可能处理的时候，让这里一个合理的数字变成了负数，使得前面一个应该变成负数的位置没有变
            int num = Math.abs(nums[i]);
            if (num>=1 && num<=n && nums[num-1]>0){
                nums[num-1]*=-1;
            }
        }

        for (int i=0; i<n; i++){  // 3.找是否有第一个正数出现
            if (nums[i]>0){
                return i+1;
            }
        }

        return n+1;
    }
}
