package 动态规划.困难.接雨水;

import java.util.Stack;

public class Solution {
    /**
     * 双指针：
     * 1. 维护左右两个指针，分别初始化为最左端left和最右端right；并且维护两个左边最大值leftMax和右边最大值rightMax。
     * 2. 水洼的形成会依赖于低的一方，所以判断左右高度，从低的一方开始走。
     * 3. 假设 left更低，首先判断当前位置是否小于 leftMax，
     * 如果小于那么可以存入水洼 leftMax-height[left]；否则leftMax = height[left]。
     * 然后left++，向右移动。
     * 4. 对于right指针也是类似的。
     * 5. 退出跳进是left和right走到了同一个位置。
     */
    public int trap(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int leftMax = 0;
        int rightMax = 0;
        int rain = 0;  // 存水滴
        while (left < right) {
            if (height[left] < height[right]) {  // 以左边为依赖
                if (leftMax > height[left]) {  // 水位低于左边最大值
                    rain += (leftMax - height[left]);
                } else {  // 高于左边最大值，更新左边最大值
                    leftMax = height[left];
                }
                left++;
            } else {   // 以右边为依赖
                if (rightMax > height[right]) {
                    rain += (rightMax - height[right]);
                } else {
                    rightMax = height[right];
                }
                right--;
            }
        }
        return rain;
    }

    /**
     * 动态规划：
     * 1. 维护两个数组，用来存储每个位置的左边高度的最大值和右边高度的最大值。
     * 2. 当前位置的水洼形成就等于左右高度低的一方的最大高度减去当前墙的高度。
     */
    public int trap2(int[] height) {
        int[] leftMaxArr = new int[height.length];
        leftMaxArr[0] = height[0];
        int[] rightMaxArr = new int[height.length];
        rightMaxArr[height.length - 1] = height[height.length - 1];

        int rain = 0;

        for (int i = 1; i < height.length; i++) {
            leftMaxArr[i] = Math.max(height[i], leftMaxArr[i - 1]);
        }
        for (int i = height.length - 2; i >= 0; i--) {
            rightMaxArr[i] = Math.max(height[i], rightMaxArr[i + 1]);
        }

        for (int i = 0; i < height.length; i++) {
            rain += (Math.min(leftMaxArr[i], rightMaxArr[i]) - height[i]);
        }

        return rain;
    }

    /**
     * 栈的运用：
     * 首先栈会存储两个元素，才能进行计算：比如 高度1 和 高度2。
     * 然后 高度1 是高于 高度2 的，并且和当前高度 height[i] 进行比较。
     * 这三个高度形成的水洼的高度 H 是：min(高度1, height[i]) - 高度2
     * 形成的宽度 W：i - 高度1的索引 - 1
     * 面积：H * W
     *
     * 思路：
     * 1. 用一个 stack 来维护高度的索引；用一个变量 i 来记录当前遍历到的索引位置。
     * 2. 外层循环用来保护 i 不越界。
     * 3. 内层循环用来判断 stack 是否为空并且当前高度 height[i] 是否大于 stack.peek。
     * 4. 符合内层判断后，取出栈顶元素作为底部，判断 stack 里面是否还有墙作为左边的墙来形成水洼。
     * 5. 符合之后，计算形成水洼的高度和宽度，并加入水洼和之中。
     * 6. 重复 3-5，直到无法形成水洼的时候，就退出循环。
     * 6. 内层循环之后，把当前点 i 加入栈里面，并让 i++ 位置后移。
     * 注意：只有两个墙是形成不了水洼的。
     */
    public int trap3(int[] height) {
        int rain = 0;
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        while (i<height.length){
            // 如果height比stack.peek的高度矮的话，会直接push进去，到后面就可能形成水洼。
            while (!stack.isEmpty() && height[i]>height[stack.peek()]){
                int top = stack.pop();  // 取出栈顶索引
                if (stack.isEmpty()) break;  // 没有围墙了
                int H = Math.min(height[stack.peek()], height[i])-height[top];
                int W = i-stack.peek()-1;
                rain+=(H*W);
            }
            stack.push(i++);
        }
        return rain;
    }

    /**
     * 暴力破解：
     * 取每一个位置，然后计算这个位置左边的最高高度，和右边的最高高度，然后计算当前位置的水洼
     * 最左边的位置和最右边的位置不管，因为两边不是都有墙，无法形成水洼
     */
    public int trap4(int[] height){
        int rain = 0;
        for (int i=1; i<height.length-1; i++){
            int L = 0;
            int R = 0;
            for (int j=0; j<i; j++){
                if (height[j]>L){
                    L = height[j];
                }
            }
            for (int k=i+1; k<height.length; k++){
                if (height[k]>R){
                    R = height[k];
                }
            }
            if (height[i]<Math.min(L, R)){
                rain += (Math.min(L, R)-height[i]);
            }
        }
        return rain;
    }
}
