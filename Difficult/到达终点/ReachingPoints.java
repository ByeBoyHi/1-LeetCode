package Difficult.到达终点;

public class ReachingPoints {
    // Stack overflow
    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        if (sx == tx && sy == ty) return true;  // 找到
        if (sx > tx || sy > ty) return false; // 越界

        // 走 (x+y, y)
        if (reachingPoints(sx + sy, sy, tx, ty)) {
            return true;
        }
        // 走 (x, x+y)
        return reachingPoints(sx, sx + sy, tx, ty);
    }

    /*
        两个数字进行累加转换，最终是不可能出现 sx==sy的，必然会有一大一小。（因为都不为零）
        当 tx<sx || ty<sy 的时候，说明已经凑不出来了，false。
        当 tx==ty的时候，说明没法走了，因为拼不出两个一样的数字，false。(1,1除外)
        当 tx==sx && ty==sy 的时候，说明拼凑成功，true。

        往下递归的时候，每次都是 tx = tx%ty (当tx>ty的时候)
        因为 tx = i*ty+rest，所以由 ty 拼凑 i 次，可以得到，所以 tx=rest(新的值)
        当 tx<ty 的时候，同理。

        当出现 tx==sx && ty!=sy的时候，只需要判断 (ty-sy)%sx==0 ，即看之间的差距是否可以由 sx多次转换抹平
     */
    public boolean reachingPoints2(int sx, int sy, int tx, int ty) {
        if (tx!=1 && tx == ty) return false;
        if (tx == sx && ty == sy) return true;
        if (tx < sx || ty < sy) return false;
        if (tx == sx) return (ty - sy) % sx == 0;
        if (ty == sy) return (tx - sx) % sy == 0;

        return tx >= ty ? reachingPoints(sx, sy, tx % ty, ty) :
                reachingPoints(sx, sy, tx, ty % tx);
    }
    // 对一个数取余是相当于让这个数字不断的拼凑，逆序求解就是不断的减去，直到无法减了，那就开始新的
}
