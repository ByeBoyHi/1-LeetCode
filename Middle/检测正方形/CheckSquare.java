package Middle.检测正方形;

import java.util.*;

public class CheckSquare {
    public static void main(String[] args) {
        Point point1 = new Point(1, 2);
        Point point2 = new Point(1, 2);
        Set<Point> set = new HashSet<>();
        set.add(point1);
        System.out.println(set.contains(point2)); // 比较的是equals
    }
}

class DetectSquares {

    HashMap<Integer, Set<Point>> mapX;
    HashMap<Integer, Set<Point>> mapY;
    HashMap<Point, Integer> count;
    HashMap<Point, Integer> number;

    public DetectSquares() {
        mapX = new HashMap<>();
        mapY = new HashMap<>();
        count = new HashMap<>();
        number = new HashMap<>();
    }

    public void add(int[] point) {
        Point p = new Point(point[0], point[1]);
        Set<Point> points;
        points = mapX.getOrDefault(p.x, new HashSet<>());
        points.add(p);
        points = mapY.getOrDefault(p.y, new HashSet<>());
        points.add(p);

        if (number.containsKey(p)){
            number.put(p, number.get(p)+1);
        }else {
            number.put(p, 1);
        }
    }

    public int count(int[] point) {
        Point p = new Point(point[0], point[1]);
        if (number.size() < 3) return 0;  // 大于4个点才有可能构成正方形

        if (!count.containsKey(p)) {
            int ans = count.getOrDefault(p,0);
            Set<Point> vertical = mapX.get(p.x);  // 竖直方向所有点
            Set<Point> parallel = mapY.get(p.y);  // 水平方向所有点
            for (Point point1 : vertical) {
                int y = point1.y;
                for (Point point2 : parallel) {
                    Set<Point> points = mapX.get(point2.x);
                    for (Point point3: points){
                        if (!point3.equals(point2) && point3.y==y){
                            ans+=number.get(point3);
                        }
                    }
                }
            }
            count.put(p, ans);
        }
        return count.get(p);
    }
}

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

class DetectSquares2 {
    Map<Integer, Map<Integer, Integer>> cnt;

    public DetectSquares2() {
        cnt = new HashMap<Integer, Map<Integer, Integer>>();
    }

    public void add(int[] point) {
        int x = point[0], y = point[1];
        cnt.putIfAbsent(y, new HashMap<Integer, Integer>());
        Map<Integer, Integer> yCnt = cnt.get(y);
        yCnt.put(x, yCnt.getOrDefault(x, 0) + 1);
    }

    public int count(int[] point) {
        int res = 0;
        int x = point[0], y = point[1];
        if (!cnt.containsKey(y)) {
            return 0;
        }
        Map<Integer, Integer> yCnt = cnt.get(y);
        Set<Map.Entry<Integer, Map<Integer, Integer>>> entries = cnt.entrySet();
        for (Map.Entry<Integer, Map<Integer, Integer>> entry : entries) {
            int col = entry.getKey();
            Map<Integer, Integer> colCnt = entry.getValue();
            if (col != y) {
                // 根据对称性，这里可以不用取绝对值
                int d = col - y;
                res += colCnt.getOrDefault(x, 0) * yCnt.getOrDefault(x + d, 0) * colCnt.getOrDefault(x + d, 0);
                res += colCnt.getOrDefault(x, 0) * yCnt.getOrDefault(x - d, 0) * colCnt.getOrDefault(x - d, 0);
            }
        }
        return res;
    }
}