package Easy.有效的回旋镖;

/**
 * 回旋镖 定义为一组三个点，这些点 各不相同 且 不在一条直线上 。
 * Time: 2022/6/8
 * User: HeyBoy
 */
public class EffectiveBoomerang {

    public boolean isBoomerang(int[][] points) {
        int x1 = points[0][0];
        int y1 = points[0][1];

        int x2 = points[1][0];
        int y2 = points[1][1];

        int x3 = points[2][0];
        int y3 = points[2][1];
        if ((x1 == x2 && y1 == y2) || (x1 == x3 && y1 == y3) || (x2 == x3 && y2 == y3)) return false;
        // 只要两条线的斜率不一样，那么就是回旋镖

        float x12 = x1 - x2;
        float x13 = x1 - x3;

        // 分母都为0
        if (x12 == 0 && x13 == 0) return false;
        // 分母不都为0
        if (x12 == 0 || x13 == 0) return true;

        float y12 = y1 - y2;
        float y13 = y1 - y3;

        // 斜率=y/x
        return (y12 / x12) != (y13 / x13);

    }
}
