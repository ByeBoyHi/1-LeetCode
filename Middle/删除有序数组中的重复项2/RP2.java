package Middle.删除有序数组中的重复项2;

import java.util.Arrays;

public class RP2 {

    public static void main(String[] args) {
        int[] arr = new int[]{
                1,1,1,2,2,3
        };
        System.out.println(removeDuplicates(arr));
    }

    public static int removeDuplicates(int[] nums) {
        int n = nums.length;
        if (n <= 2) {
            return n;
        }
        int slow = 2, fast = 2;
        while (fast < n) {
            // 这里比较的是从slow往前两位的位置的值是否和fast相等
            // 在最开始，slow==fast，那么就相当于 1 2 3 三个连续的位置是否一样
            // 如果出现相等，那么说明至少 1 2 是相等的。
            // 如果fast移动过，那么说明1 2 .. fast-1是相等的。
            // 那么我们只需要让当前的fast位置的值去覆盖slow位置的值，即可。
            // 然后fast和slow都后移，再依次覆盖处理。
            // slow每一次覆盖成功都会+1，相等于记录的合格的数字范围。
            if (nums[slow - 2] != nums[fast]) {
                nums[slow] = nums[fast];
                ++slow;
            }
            ++fast;
        }
        System.out.println(Arrays.toString(nums));
        return slow;
    }
}
