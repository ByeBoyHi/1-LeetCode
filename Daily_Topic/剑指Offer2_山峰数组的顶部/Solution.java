package Daily_Topic.剑指Offer2_山峰数组的顶部;

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().peakIndexInMountainArray2(new int[]{
                3,5,3,2,0
        }));
    }
    // O(n)
    public int peakIndexInMountainArray(int[] arr) {
        int index = 0;
        for (; index < arr.length - 1; index++) {
            if (arr[index] > arr[index + 1]) {
                break;
            }
        }
        return index;
    }

    // O(log n)
    public int peakIndexInMountainArray2(int[] arr) {
        int start = 0;
        int end = arr.length-1;
        int mid = start + ((end-start)>>1);
        while (start<end){
            if (arr[mid]>=arr[mid-1]){
                if (arr[mid]>=arr[mid+1]) {
                    return mid;
                }else if (arr[mid]<=arr[mid+1]){
                    start = mid+1;
                }
            }else if (arr[mid]<=arr[mid-1]){
                end = mid-1;
            }
            mid = start + ((end-start)>>1)+1;
            // 如果mid等于0，防止上面的mid-1越界，我们+1判断
            // mid不可能走到末尾，因为末尾只能start=end的时候才会有，但是这会退出我们的循环条件，我们也就不会需要进行-1规避
            if (mid==0){
                mid++;
            }
        }
        return start;
    }
}
