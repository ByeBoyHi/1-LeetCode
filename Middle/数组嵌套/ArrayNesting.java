package Middle.数组嵌套;


public class ArrayNesting {

    /**
     * 由于{A[i], A[A[i]], A[A[A[i]]], ...}
     * 结束必然是访问到已经访问过的节点
     * 并且节点的值是不会超过数组长度的，因此必然会环
     */
    public int arrayNesting(int[] nums) {
        int ans = 0;
        int n = nums.length;
        boolean[] vis = new boolean[n];
        for (int i=0; i<n; i++){
            int cnt = 0;
            while (!vis[i]){
                vis[i] = true;
                i = nums[i];
                ++cnt;
            }
            ans = Math.max(ans, cnt);
        }
        return ans;
    }

    // 令nums[i]=n来实现vis的功能
    // 这个题目的数组特点是：
    // 1. 长度为N
    // 2. 数值为 0~N-1
    // 3. 各个数组下标的值各不相同
    // 那么必然会出现首尾相连的闭环，而不会出现一个环和一条链在一起的情况
    // 因为那样的话就出现了两个位置都指向了同一个位置
    public int arrayNesting2(int[] nums) {
        int ans = 0;
        int n = nums.length;

        for (int i=0; i<n; i++){
            int cnt = 0;
            while (nums[i]<n){
                int num = nums[i];  // 这里的nums[i]会变化，所以记得先取出来
                nums[i] = n;
                cnt++;
                i = num;
            }
            ans = Math.max(ans, cnt);
        }
        return ans;
    }
}
