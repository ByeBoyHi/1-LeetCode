package Middle.下一个排列;

import java.util.TreeMap;

public class NextPermutation {

    /*
        整体是递减的，那么只能翻转
        整体非递减的，
            从后往前看，找到第一个降序点，然后以此为分界点x，在后面找比这个大的最小值，交换位置，剩下的重排序。
     */
    public void nextPermutation(int[] nums) {
        if (isDecrease(nums)){  // 递减的
            reverse(nums);
        } else {  // 非递减的
            process(nums, 0, nums.length);
        }
    }

    // 递归原地处理数组
    public void process(int[] arr, int start, int end){
        if (start==end) return;
        int max = getMax(arr, start);
        if (arr[start]==max){  // 2
            process(arr, start+1, end);
        }else {  // 1
            for (int i = end-1; i > start; i--) {
                if (arr[i]>arr[i-1]){
                    //找到降序点
                    int idx = i;
                    for (int j = i; j < end; j++) {
                        if (arr[j]>arr[i-1] && arr[j]<max){
                            max = arr[j];
                            idx = j;
                        }
                    }
                    swap(arr, i-1, idx);
                    reSort(arr, i);
                }
            }
        }
    }

    // 对数组从start位置开始的所有元素进行重排序
    public void reSort(int[] arr, int start){
        TreeMap<Integer, Integer> tree = new TreeMap<>();
        for (int i = start; i < arr.length; i++) {
            tree.put(arr[i], tree.getOrDefault(arr[i],0)+1);
        }
        for (int i = start; i < arr.length; i++) {
            int first = tree.firstKey();
            arr[i] = first;
            if (tree.get(first)==1){
                tree.remove(first);
            }else {
                tree.put(first, tree.get(first) - 1);
            }
        }
    }

    // 获取最大值
    public int getMax(int[] arr, int start){
        int max = arr[start];
        for (int i=start+1; i<arr.length; i++){
            max = Math.max(max, arr[i]);
        }
        return max;
    }

    // 交换
    public void swap(int[] arr, int i, int j){
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    // 判断是否递减
    public boolean isDecrease(int[] arr){
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i]<arr[i+1]){
                return false;
            }
        }
        return true;
    }

    // 翻转数组
    public void reverse(int[] arr){
        for (int i = 0; i < arr.length / 2; i++) {
            swap(arr, i, arr.length-1-i);
        }
    }
}
