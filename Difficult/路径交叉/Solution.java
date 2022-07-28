package Difficult.路径交叉;

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().isSelfCrossing2(new int[]{
                1, 1, 2, 1, 1
        }));
    }

    // 枚举，三种情况可能相交的情况
    // 也可以枚举三种不可能相交的情况
    public boolean isSelfCrossing(int[] distance) {
        if (distance.length < 4) {
            return false;
        }
        for (int i = 4; i < distance.length; i++) {
            if (distance[i] >= distance[i - 2] && distance[i - 1] <= distance[i - 3]) {  // 情况一
                return true;
            } else if (i > 5 && distance[i] + distance[i - 4] >= distance[i - 2]
                    && distance[i - 2] > distance[i - 4]
                    && distance[i - 1] + distance[i - 5] >= distance[i - 3]
                    && distance[i - 1] <= distance[i - 3]) {  // 情况二
                return true;
            } else if (i == 4 && distance[4] + distance[0] >= distance[2]
                    && distance[3] == distance[1]) {  // 情况三
                return true;
            }
        }
        return false;
    }

    // 外卷转内卷技巧

    /**
     * 如果坐标一直外卷，那么是不会相交的，如果突然转内卷，这时候就判断是否会一直内卷
     * 如果这时候从内卷转外卷了，那么就相交了
     */
    public boolean isSelfCrossing2(int[] distance) {
        if (distance.length < 4) return false;
        int i = 2;  // 用来记录已经走到第几步了

        //向外卷说明， distance[i]>distance[i-2]的
        while (i < distance.length && distance[i] > distance[i - 2]) i++;

        // 一直外卷到结束，说明没有相交
        if (i == distance.length) return false;

        // 出现内卷了
        // 出现新的卷空间，有 distance[i-1]=distance[i-1]-distance[i-3]
        // 且 distance[i]>=distance[i-2]-distance[i-4]
        // 这里要取等号！！！
        if (distance[i] >= distance[i - 2] - (i < 4 ? 0 : distance[i - 4])) {
            distance[i - 1] = distance[i - 1] - (i < 3 ? 0 : distance[i - 3]);
        }

        // 内卷开始
        // 要先++！！！
        // 之前的i是竖着的那个，这里要++，然后让i走到横着那里，和上面的i-1相呼应形成内卷圈
        ++i;
        while (i < distance.length && distance[i] < distance[i - 2]) {
            i++;
        }

        // 如果内卷结束的时候，i没有走到distance的长度，说明相交了
        return i != distance.length;
    }
}

