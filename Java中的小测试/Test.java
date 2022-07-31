package Java中的小测试;

public class Test {

    public static void main(String[] args) {
        Point point = new Point(0,0);
                System.out.println(calcLocation("32(BL)", point));
    }

    static class Point {
        int x, y;

        public Point() {
        }

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    // 有一个机器人，给一串指令，L左走一步 R右走一步，F前进一步，B后退一步，问最后机器人的坐标，最开始，机器人位于 0 0，方向为正Y。
    //可以输入重复指令n ： 比如 R2(LF) 这个等于指令 RLFLF。 问最后机器人的坐标是多少？
    private static Point calcLocation(String cmd, Point point) {
        Point res = new Point(point.x, point.y);
        int start = -1;
        int repeat = 0;
        for (int i = 0; i < cmd.length(); i++) {
            char command = cmd.charAt(i);

            switch (command) {
                case 'L' -> res.x--;  // 左
                case 'R' -> res.x++;  // 右
                case 'F' -> res.y++;  // 上
                case 'B' -> res.y--;  // 下
                case '(' -> {
                    start = i + 1;
                    repeat = calNumber(cmd, i-1);
                }
                case ')' -> {
                    int end = i - 1;
                    // 重复repeat-1次
                    while (repeat-- > 1) {
                        for (int x = start; x <= end; x++) {
                            switch (cmd.charAt(x)){
                                case 'L' -> res.x--;
                                case 'R' -> res.x++;
                                case 'F' -> res.y++;
                                case 'B' -> res.y--;
                            }
                        }
                    }
                    repeat = 0;
                }
            }
        }
        return res;
    }

    public static int calNumber(String cmd, int idx) {
        int number = 0;
        int power = 0;
        while (idx>=0 && Character.isDigit(cmd.charAt(idx))) {
            number += (cmd.charAt(idx) - '0')*Math.pow(10,power++);
            idx--;
        }
        return number;
    }
}

