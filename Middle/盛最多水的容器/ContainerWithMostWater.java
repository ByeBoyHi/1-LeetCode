package Middle.盛最多水的容器;

public class ContainerWithMostWater {
    // 水的容量就是矩形的面积
    /*
        用双指针，左右各一个
        小的一方移动，当前的水的面积是 低的高度*距离
        不断更新

        因为双指针范围代表这个高度这个范围能够容纳的水的容量
        低的一方移动可能让下一次的容量增加，但是高的一方移动一定让下一次容量减少
     */
    public int maxArea(int[] height) {
        int ans = 0;
        int right = height.length-1;
        for (int left=0; left<right; ){
            ans = Math.max(ans, Math.min(height[left], height[right])*(right-left));
            if (height[right]<height[left]){
                right--;
            }else {
                left++;
            }
        }
        return ans;
    }
}
