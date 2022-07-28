package Middle.随机翻转矩阵;

import java.util.*;

public class RandomFlipMatrix {
    public static void main(String[] args) {
        Solution s = new Solution(1,3);
        System.out.println(Arrays.toString(s.flip()));
        System.out.println(Arrays.toString(s.flip()));
        System.out.println(Arrays.toString(s.flip()));
    }
}


class Solution2 {
    int[][] arr;
    Set<int[]> record;
    List<List<Integer>> space;
    int rows, cols;

    public Solution2(int m, int n) {
        rows = m;
        cols = n;
        arr = new int[rows][cols];
        record = new HashSet<>();
        space = new LinkedList<>();
        reset(this.space);
    }

    public int[] flip() {
        if (record.size()<rows*cols) {  // 如果里面还有0的话
            int[] res = new int[2];
            int n = (int) (Math.random() * space.size());  // 随机一个列
            int m = (int) (Math.random()*(space.get(n).size()-1))+1;  // 随机一个行
            int r = space.get(n).get(0);  // 行号
            int c = space.get(n).get(m);  // 列号
            arr[r][c] = 1;
            res[0] = r;
            res[1] = c;
            space.get(n).remove(m);
            if (space.get(n).size()==1){
                space.remove(n);
            }
            record.add(res);
            return res;
        }
        return null;
    }

    public void reset() {
        for (int[] a:record){  // 取出每一个变成1的位置，重新置为0
            this.arr[a[0]][a[1]] = 0;
        }
        record.clear();
        reset(this.space);
    }
    public void reset(List<List<Integer>> space){
        for (int i=0; i<rows; i++){
            List<Integer> cur = new ArrayList<>();
            cur.add(i); // 表头存一下当前行号
            for (int j=0; j<cols; j++){
                cur.add(j); // 所有列号放进去
            }
            space.add(cur);
        }
    }
}

// 官解1
class Solution {
    Map<Integer, Integer> map = new HashMap<>();
    int m, n, total;
    Random rand = new Random();

    public Solution(int m, int n) {
        this.m = m;
        this.n = n;
        this.total = m * n;
    }

    public int[] flip() {
        int x = rand.nextInt(total);
        total--;
        // 查找位置 x 对应的映射
        int idx = map.getOrDefault(x, x);
        // 将位置 x 对应的映射设置为位置 total 对应的映射
        map.put(x, map.getOrDefault(total, total));
        return new int[]{idx / n, idx % n};
    }

    public void reset() {
        total = m * n;
        map.clear();
    }
}

class Solution3 {
    int m, n;
    int total, bucketSize;
    List<Set<Integer>> buckets = new ArrayList<>();
    Random rand = new Random();

    public Solution3(int m, int n) {
        this.m = m;
        this.n = n;
        this.total = m * n;
        this.bucketSize = (int) Math.sqrt(total);
        for (int i = 0; i < total; i += bucketSize) {
            buckets.add(new HashSet<Integer>());
        }
    }

    public int[] flip() {
        int x = rand.nextInt(total);
        int sumZero = 0;
        int curr = 0;
        total--;

        for (Set<Integer> bucket : buckets) {
            if (sumZero + bucketSize - bucket.size() > x) {
                for (int i = 0; i < bucketSize; ++i) {
                    if (!bucket.contains(curr + i)) {
                        if (sumZero == x) {
                            bucket.add(curr + i);
                            return new int[]{(curr + i) / n, (curr + i) % n};
                        }
                        sumZero++;
                    }
                }
            }
            curr += bucketSize;
            sumZero += bucketSize - bucket.size();
        }

        return null;
    }

    public void reset() {
        total = m * n;
        for (Set<Integer> bucket : buckets) {
            bucket.clear();
        }
    }
}

// 我的超时，因为while(true)的缘故
class Solution4 {
    int[][] arr;
    Set<int[]> record;
    int rows, cols;

    public Solution4(int m, int n) {
        rows = m;
        cols = n;
        arr = new int[rows][cols];
        record = new HashSet<>();
    }

    public int[] flip() {
        if (record.size()<rows*cols) {  // 如果里面还有0的话
            int[] res = new int[2];
            while (true) {
                int n = (int) (Math.random() * cols);  // 随机一个列
                int m = (int) (Math.random() * rows);  // 随机一个行
                if (this.arr[m][n] == 0) {
                    this.arr[m][n] = 1;
                    res[0] = m;
                    res[1] = n;
                    record.add(res);
                    break;
                }
            }
            return res;
        }
        return null;
    }

    public void reset() {
        for (int[] a:record){  // 取出每一个变成1的位置，重新置为0
            this.arr[a[0]][a[1]] = 0;
        }
        record.clear();
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(m, n);
 * int[] param_1 = obj.flip();
 * obj.reset();
 */