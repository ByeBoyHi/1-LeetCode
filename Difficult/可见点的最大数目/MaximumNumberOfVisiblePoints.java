package Difficult.可见点的最大数目;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MaximumNumberOfVisiblePoints {
    /**
     * 温馨提示：
     *  atan(x)：传进去一个度数，算出来的也是度数
     *  atan2(x, y)：传进去的是两个直角边，算的就是这两个直角边对应的角的弧度值，也就是PI为单位制
     *  上面两个函数算出来的值的基本单位是不一样的，要注意！！！
     *  也就是下面这个方法，我们在计算里面的弧度的时候，需要调整为统一的，再计算。
     */
    /*
        计算每个点相对于当前点的极角，以x轴为起点
        极角计算公式：
            两个点 [x1, y1]  [x2, y2]
            求出反正切值：arctan(x/y) = atan2(x,y)（因为我们知道这两个点的坐标，所以用求弧度值的函数更方便）
            根据题意，我们需要找[dp-angle/2, dp+angle/2]
            那么我们也可以找[dp, dp+angle]
            由于传进来的angle是度数，所以我们可以用 Ans*PI/180 来得到对应的PI值比例，即弧度制
            1. 求出和[x,y]重叠的点的个数
            2. 求出剩下的点，可以笼罩的范围的最大点的个数
     */
    public int visiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {
        int sameCnt = 0;
        int locationX = location.get(0);
        int locationY = location.get(1);
        List<Double> polarDegrees = new ArrayList<>();

        for (List<Integer> point: points){
            int x = point.get(0);
            int y = point.get(1);
            if (x==locationX && y==locationY){
                sameCnt++;
                continue;
            }
            double val = Math.atan2(y-locationY, x-locationX);
            polarDegrees.add(val);
        }

        int m = polarDegrees.size();  // 线程不安全，长度需要取出来
        for (int i=0; i<m; i++){
            // ***加上2PI，可以避免当dp+angle>180的时候，由于反转，错过的一些角
            // 这些角通过2PI，可以得到一些同等的角，只是值不一样
            polarDegrees.add(polarDegrees.get(i)+2*Math.PI);
        }


        //  度数从小到大排序
        Collections.sort(polarDegrees);
        int maxCnt = 0;
        //***  换算成PI的计量单位
        Double toDegree = angle*Math.PI/180;
        for (int i=0; i<m; i++){
            int iter = binaryS(polarDegrees, polarDegrees.get(i)+toDegree);
            maxCnt = Math.max(maxCnt, iter-i);
        }

        return maxCnt+sameCnt;
    }

    // 二分查找：度数小于dp+angle的所有点的个数和
    public int binaryS(List<Double> Degrees, Double degree){
        int left = 0;
        int right = Degrees.size();
        int ans = 0;
        while (left<=right){
            int mid = left+((right-left)>>1);
            //***
            if (Degrees.get(mid)>degree){
                right = mid-1;
                ans = mid;
            }else {
                left = mid+1;
            }
        }
        return ans;
    }

    // 滑动窗口
    public int visiblePoints2(List<List<Integer>> points, int angle, List<Integer> location) {
        int sameCnt = 0;
        int locationX = location.get(0);
        int locationY = location.get(1);
        List<Double> polarDegrees = new ArrayList<>();

        for (List<Integer> point: points){
            int x = point.get(0);
            int y = point.get(1);
            if (x==locationX && y==locationY){
                sameCnt++;
                continue;
            }
            double val = Math.atan2(y-locationY, x-locationX);
            polarDegrees.add(val);
        }

        int m = polarDegrees.size();  // 线程不安全，长度需要取出来
        for (int i=0; i<m; i++){
            // ***加上2PI，可以避免当dp+angle>180的时候，由于反转，错过的一些角
            // 这些角通过2PI，可以得到一些同等的角，只是值不一样
            polarDegrees.add(polarDegrees.get(i)+2*Math.PI);
        }


        //  度数从小到大排序
        Collections.sort(polarDegrees);

        int maxCnt = 0;
        int right = 0;
        //***  换算成PI的计量单位
        Double toDegree = angle*Math.PI/180;
        for (int i=0; i<m; i++){
            Double cur = polarDegrees.get(i)+toDegree;
            while (right<polarDegrees.size() && polarDegrees.get(right)<=cur){
                right++;
            }
            maxCnt = Math.max(maxCnt, right-i);
        }

        return maxCnt+sameCnt;
    }
}
