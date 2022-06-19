package Difficult.我的日程安排表3;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <返回在同一个时间段有多少预定> <br>
 * Time: 2022/6/6 <br>
 * User: HeyBoy <br>
 */
public class DailyTable3 {
    
}

/**
 * 差分算法：
 *
 */
class  MyCalendarThree{

    private final TreeMap<Integer, Integer> cnt;

    public MyCalendarThree(){
        cnt = new TreeMap<>();
    }

    public int book(int start, int end){
        AtomicInteger max = new AtomicInteger(0);
        AtomicInteger maxBook = new AtomicInteger(0);
        cnt.put(start, cnt.getOrDefault(start,0)+1);
        cnt.put(end, cnt.getOrDefault(end,0)-1);
        cnt.forEach((key, value) -> {
            maxBook.addAndGet(value);
            if (maxBook.get() > max.get()) {
                max.set(maxBook.get());
            }
        });
        return max.get();
    }

}

// 线段树规则：arr[idx] = max(arr[idx*2], arr[idx*2+1]) + lazy
class SegmentCalendar{
    // [start,end)之间的预定，也就是[start,end-1]的预定+1
    // 那么只需要使用线段树进行区间求和即可
    // 因为我们不知道预定总数有多少，所以直接给的Map存储所有信息
    final Map<Integer, Integer> tree;
    final Map<Integer, Integer> lazy;
    final int MAX = 1000000000;  // 最大预定数

    public SegmentCalendar(){
        tree = new HashMap<>();
        lazy = new HashMap<>();
    }

    public int book(int start, int end){
        update(start, end-1, 0, MAX, 1);
        // 因为我们的更新是从1开始的，所以下面取1的值就行了
        // 跟start，end无关
        return tree.getOrDefault(1,0);
    }

    /**
     * 这个线段树的更新过程，最终会把最大的结果返回给最上面的一个节点。 <br>
     * 也就是说，每一个区间，记录的是这个区间内部的最大重叠次数，而不是这个区间的整个的最大重叠次数。
     * @param start  插入区间的左边界
     * @param end    插入区间的右边界
     * @param l      当前区间的左边界
     * @param r      当前区间的右边界
     * @param idx    当前线段树位置
     */
    public void update(int start, int end, int l, int r, int idx){
        if (r<start || l>end) return;  // 区间范围越界了

        if(start<=l && end>=r){  // 子区间
            tree.put(idx, tree.getOrDefault(idx, 0)+1);  // 当前区间和+1
            lazy.put(idx, lazy.getOrDefault(idx, 0)+1);  // 给当前父区间打上惰性标记
        }else {
            int mid = (l+r)>>1;
            // 更新左边
            update(start, end, l, mid, idx*2);
            // 更新右边
            update(start, end, mid+1, r, idx*2+1);
            tree.put(idx, lazy.getOrDefault(idx,0) +
                    Math.max(tree.getOrDefault(idx*2,0), tree.getOrDefault(idx*2+1, 0)));
        }

    }

}
