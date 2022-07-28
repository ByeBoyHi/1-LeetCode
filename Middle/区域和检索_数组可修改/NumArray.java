package Middle.区域和检索_数组可修改;


// 我的超时了
class TLE {

    // 记录从0~i的全和
    private final int[] sum;
    private final int[] nums;
    private final int n;

    public TLE(int[] nums) {
        n = nums.length;
        this.nums = nums;
        sum = new int[n];
        process();
    }

    // TLE
    public void update(int index, int val) {
        int diff = val-nums[index];
        nums[index] = val;
        if (diff!=0){
            for (int i = index; i < n; i++) {
                sum[i]+=diff;
            }
        }
    }

    public int sumRange(int left, int right) {
        if (left==0) return sum[right];
        return sum[right]-sum[left-1];
    }

    private void process(){
        sum[0] = nums[0];
        for (int i = 1; i < n; i++) {
            sum[i]+=sum[i-1]+nums[i];
        }
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */

// 分块处理
public class NumArray {

    private final int[] sum; // sum[i] 表示第 i 个块的元素和
    private final int size; // 块的大小
    private final int[] nums;

    public NumArray(int[] nums) {
        this.nums = nums;
        int n = nums.length;
        size = (int) Math.sqrt(n);
        sum = new int[(n - 1) / size + 1]; // n/size 向上取整。  n-1 可以保证除以size必然是向下取整的。
        for (int i = 0; i < n; i++) {
            sum[i / size] += nums[i];
        }
    }

    public void update(int index, int val) {
        sum[index / size] += val - nums[index];
        nums[index] = val;
    }

    public int sumRange(int left, int right) {
        int b1 = left / size, i1 = left % size, b2 = right / size, i2 = right % size;
        // 用所在块号乘以块的大小，来得到准确的下标
        if (b1 == b2) { // 区间 [left, right] 在同一块中
            int sum = 0;
            for (int j = i1; j <= i2; j++) {
                sum += nums[b1 * size + j];
            }
            return sum;
        }
        int sum1 = 0;
        for (int j = i1; j < size; j++) {
            sum1 += nums[b1 * size + j];
        }
        int sum2 = 0;
        for (int j = 0; j <= i2; j++) {
            sum2 += nums[b2 * size + j];
        }
        int sum3 = 0;
        for (int j = b1 + 1; j < b2; j++) {
            sum3 += sum[j];
        }
        return sum1 + sum2 + sum3;
    }

}

// 线段树
class SegmentTree {
    private final int[] segmentTree;
    private final int n;

    public SegmentTree(int[] nums) {
        n = nums.length;
        segmentTree = new int[n * 4];
        build(0, 0, n - 1, nums);
    }

    public void update(int index, int val) {
        change(index, val, 0, 0, n - 1);
    }

    public int sumRange(int left, int right) {
        return range(left, right, 0, 0, n - 1);
    }

    private void build(int node, int s, int e, int[] nums) {
        if (s == e) {
            segmentTree[node] = nums[s];
            return;
        }
        int m = s + (e - s) / 2;
        build(node * 2 + 1, s, m, nums);
        build(node * 2 + 2, m + 1, e, nums);
        segmentTree[node] = segmentTree[node * 2 + 1] + segmentTree[node * 2 + 2];
    }

    private void change(int index, int val, int node, int s, int e) {
        if (s == e) {
            segmentTree[node] = val;
            return;
        }
        int m = s + (e - s) / 2;
        if (index <= m) {
            change(index, val, node * 2 + 1, s, m);
        } else {
            change(index, val, node * 2 + 2, m + 1, e);
        }
        segmentTree[node] = segmentTree[node * 2 + 1] + segmentTree[node * 2 + 2];
    }

    private int range(int left, int right, int node, int s, int e) {
        if (left == s && right == e) {
            return segmentTree[node];
        }
        int m = s + (e - s) / 2;
        if (right <= m) {
            return range(left, right, node * 2 + 1, s, m);
        } else if (left > m) {
            return range(left, right, node * 2 + 2, m + 1, e);
        } else {
            return range(left, m, node * 2 + 1, s, m) + range(m + 1, right, node * 2 + 2, m + 1, e);
        }
    }
}

// 树状数组
class TreeArr {
    private final int[] tree;
    private final int[] nums;

    public TreeArr(int[] nums) {
        this.tree = new int[nums.length + 1];
        this.nums = nums;
        for (int i = 0; i < nums.length; i++) {
            add(i + 1, nums[i]);
        }
    }

    public void update(int index, int val) {
        add(index + 1, val - nums[index]);
        nums[index] = val;
    }

    public int sumRange(int left, int right) {
        return prefixSum(right + 1) - prefixSum(left);
    }

    /*
        取一个数字的最低位的1的值，如：lowBit(34)=2，因为 34 = 0010 0010，最低位：0000 0010=2
        对 34取反=1101 1101，再+1=1101 1110。
        0010 0010 & 1101 1111 = 0000 0010 = 2
        即： x & (~x+1)
        而 ~x+1 = -x
        所以是 x & -x
     */
    private int lowBit(int x) {
        return x & -x;
    }

    private void add(int index, int val) {
        while (index < tree.length) {
            tree[index] += val;
            index += lowBit(index);
        }
    }

    private int prefixSum(int index) {
        int sum = 0;
        while (index > 0) {
            sum += tree[index];
            index -= lowBit(index);
        }
        return sum;
    }
}
