package Daily_Topic.排列硬币;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().arrangeCoins2(5));
    }

    public int arrangeCoins(int n) {
        int i=0;
        while (n>=0){
            i++;
            n = n - i;
        }
        return i-1;
    }

    /**
     * 对于二分查找，我们取中值或者计算前n项和的时候可能出现计算过程中超过int范围的可能。
     * 为了避免不必要的麻烦，我们直接用long来保存值。
     * 在最后返回值的时候，我有纠结返回left还是right。
     * 为了避免纠结，我们打个比方：
     *      假如 mid 位置刚好大于 n，但是 mid-1 刚好小于 n，那么这时候的 right是合理返回；
     *      反之，如果 mid-1 还是大于 n，不慌，我们还要循环缩小范围。
     *      再假如 mid 位置小于 n，这时候 mid+1 可能刚好大于 n，那么这时候我们应该返回 left-1；
     *      反之，如果 mid+1 还是小于 n，并且就是我们要返回的值，那么我们应该返回 left。
     *      再假如，如果最后的 right 是大于 n 的，并且 left 也大于 n，那么这时候肯定推出循环了。
     *      因为 mid 已经没法计算了，如果还没有退出循环，那就只有 right==left 的情况了。
     *      这时候的 right 将会变成 mid-1，也就是比 left 小 1，这个位置就是之前的合理值。
     * 综上所述，left存在争议，而 right 是合理的。
     */
    // 二分+等差数列
    // 前n项和：n(n+1)/2
    public int arrangeCoins2(int n){
        long left = 0;
        long right = n;
        while (left<=right){
            long mid = (left+right)>>1;
            long cur = mid*(mid+1)>>1;
            if (cur==n) return (int)mid;
            else if (cur>n) right = mid-1;
            else left = mid+1;
        }
        return (int)right;
    }

    // 数学公式：x = (-1+sqrt(8n+1))/2
    public int arrangeCoins3(int n) {
        return (int)Math.floor((-1+Math.sqrt((long)8*n+1))/2);
    }
}
