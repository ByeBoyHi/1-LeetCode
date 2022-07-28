package Middle.轮转数组;

public class RotateArray {

    // 方法一：新数组取值
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k%n;
        if (k==0) return;

        int[] arr = new int[n];
        if (n - k >= 0) System.arraycopy(nums, 0, arr, k, n - k);
        if (k >= 0) System.arraycopy(nums, n - k, arr, 0, k);
        System.arraycopy(arr, 0, nums, 0, n);
    }

    /*
        先翻转整体，然后翻转后n-k部分，最后翻转前k个
        也可以前面和后面分开翻转，然后再整体翻转
     */
    public void rotate2(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        if (k==0) return;
        reverse(nums, 0,n-1);
        reverse(nums, 0, k-1);
        reverse(nums, k, n-1);
    }
    // 翻转数组start到end之间的元素
    public void reverse(int[] arr, int start, int end){
        while (start<end){
            int t = arr[start];
            arr[start] = arr[end];
            arr[end] = t;
            start++;
            end--;
        }
    }

    // 约瑟夫环问题
    /*
        每次都移动k步，并且用前一个值覆盖新拿到的值
        覆盖之前，要把那个值存一下，用于下次覆盖
        直到所有的值都移动过k步，就可以退出了
        当 len%k==0 的时候，会出现几个数字重复访问原地转圈
        这时候我们就需要让下标加一，然后把下一圈也移动完，避免在重复位置转圈
     */
    public void rotate3(int[] nums, int k) {
        int hold = nums[0]; // 用来保存覆盖值，最开始是0号位置开始移动
        int index = 0; // 记录当前的位置
        int len = nums.length;
        k = k%len;
        boolean[] visited = new boolean[len]; // 用来记录当前元素是否访问过，避免转圈
        for (int i = 0; i < len; i++) {
            index = (index+k)%len;
            if (visited[index]){  // 如果访问过
                index = (index+1)%len;
                hold = nums[index];  // 记录新的圈值
                i--; // 这次当作没有走
            }else {
                int t = nums[index];
                nums[index] = hold;
                hold = t;
                visited[index] = true;
            }
        }
    }
}
