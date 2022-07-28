package Difficult.课程表3;


import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class CourseScheduleIII {
    /*
        按照贪心来算，给一个优先队列存储最大的duration。
        当两个时间 (t1,d1) (t2,d2)，如果d1<d2，那么先学d1总是更优的，
        开始学习的时间是x，那么： x+t1<d1，x+t1+t2<d2  先学d1
        或者                  x+t2<d2，x+t1+t2<d1  先学d2
        因为 x+t1+t2<d1<d2  所以有 x+t1+t2<d2，所以在先学d2的情况下，必然可以先学d1，反之不行。
        在遍历当前时间表的时候，
        1. 当前课程的时间加上前面的所有时间如果小于等于当前结束时间，直接入队列
        2. 否则，判断堆顶的持续时间是否大于当前的持续时间，大的话，就弹出堆顶，让当前入堆
     */
    public int scheduleCourse(int[][] courses) {

        int timeTotal = 0;

        // 按照结束时间升序排
        Arrays.sort(courses, Comparator.comparingInt(x->x[1]));
        // 大根堆按照duration最大排
        PriorityQueue<int[]> pq = new PriorityQueue<>((x,y)->y[0]-x[0]);

        for (int[] cours : courses) {
            if (timeTotal+cours[0]<=cours[1]){
                pq.offer(cours);
                timeTotal+=cours[0];
            }else if (!pq.isEmpty() && pq.peek()[0]>cours[0]){
                timeTotal-=pq.poll()[0]-cours[0];
                pq.offer(cours);
            }
        }
        return pq.size();
    }
}
