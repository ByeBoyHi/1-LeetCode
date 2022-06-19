package Easy.下一个更大元素1;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Solution {
    public static void main(String[] args) {
    }

    // 暴力破解
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] ans = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {  // 依次取每一个nums1的元素，取nums2里面找第一个比这个位置大的元素
            int cur = -1;
            int res = -1;
            for (int j = 0; j < nums2.length; j++) {// 找和nums1[i]对应元素相等的元素
                if (nums1[i] == nums2[j]) {
                    cur = j;
                    break;
                }
            }
            for (int j = cur + 1; j < nums2.length; j++) {  // 找右边第一个比他大的元素
                if (nums2[j] > nums2[cur]) {
                    res = nums2[j];
                    break;
                }
            }
            ans[i] = res;
        }
        return ans;
    }

    // 对nums2进行预处理后，nums1去取值找下一个较大值就不需要再遍历了

    /**
     * 用一个HashMap进行预处理，取值时间复杂度较高。
     */
    public int[] nextGreaterElement2(int[] nums1, int[] nums2) {
        int[] ans = new int[nums1.length];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            for (int j = i + 1; j < nums2.length; j++) {
                if (nums2[i] < nums2[j]) {
                    map.put(nums2[i], nums2[j]);  // 当前值的右边第一个较大值
                    break;
                }
            }
        }
        for (int i = 0; i < nums1.length; i++) {
            ans[i] = map.getOrDefault(nums1[i], -1);  // 取nums1[i]对应的Value值，如果没有就取-1
        }
        return ans;
    }

    // 单调栈

    /**
     * 逆序遍历，存储元素入栈，如果当前元素比栈顶元素小，那么栈顶元素就是当前元素的右边第一个较大值
     * 当前元素再入栈，判断下一个元素。
     * 如果当前元素比栈顶元素大，那么不断的出栈，直到有比当前元素大的元素出现，否则就是-1，当前元素再次入栈。
     * 如此重复，直预处理完整个数组nums2。
     */
    public int[] nextGreaterElement3(int[] nums1, int[] nums2) {
        int[] ans = new int[nums1.length];
        Stack<Integer> stack = new Stack<>();  // 单调栈来找较大值
        Map<Integer, Integer> map = new HashMap<>();  // 存储当前和和较大值
        for (int i = nums2.length - 1; i >= 0; i--) {
//            int cur = -1;
//            while (!stack.isEmpty()){
//                int num = stack.peek();  // 拿出来看一下
//                if (num>nums2[i]){  // 如果比num大，那么就存入map里面
//                    cur = num;
//                    break;
//                }
//                // 如果比num小，就移除了
//                stack.pop();
//            }
            while (!stack.isEmpty() && nums2[i] > stack.peek())
                stack.pop();
            map.put(nums2[i], stack.isEmpty() ? -1 : stack.peek());
            stack.push(nums2[i]);
        }
        for (int i = 0; i < nums1.length; i++) {  // 这个题目：nums1里面的元素在nums2里面一定是存在的
            ans[i] = map.get(nums1[i]);
        }
        return ans;
    }
}
