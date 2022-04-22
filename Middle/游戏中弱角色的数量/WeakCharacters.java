package Middle.游戏中弱角色的数量;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;

public class WeakCharacters {
    // TLE
    public int numberOfWeakCharacters(int[][] properties) {
        int ans = 0;
        // 排序结束必然是攻击力低的在前面
        Arrays.sort(properties, Comparator.comparingInt(a -> a[0]));
        for (int i = 0; i < properties.length; i++) {
            for (int j = i + 1; j < properties.length; j++) {
                if (properties[i][0] != properties[j][0]) {
                    if (properties[i][1] < properties[j][1]) {
                        ans++;
                        break;
                    }
                }
            }
        }

        return ans;
    }

    public int numberOfWeakCharacters2(int[][] properties) {
        Arrays.sort(properties, (a, b) -> a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]);
        // 攻击力高的在前面
        // 攻击力相同的，防御力低的在前面
        int maxDef = 0;
        int ans = 0;
        // 当前遍历到的值，攻击力都是比前一个低或者相等的
        // 当低的时候，我们可以直接放心的比较防御力
        // 当相等的时候，因为是按照防御力低排序的，所以也不会把攻击相等的计算在内
        for (int[] p : properties) {
            if (p[1] < maxDef) ans++;
            else maxDef = p[1];
        }
        return ans;
    }

    // 单调栈
    // 在栈里的元素都是攻击力低的
    // 在攻击力相等的情况下，保证防御力高排序，避免同类攻击力元素之间，因为防御力而计数
    public int numberOfWeakCharacters3(int[][] properties) {
        Arrays.sort(properties, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        int ans = 0;
        Deque<Integer> st = new ArrayDeque<>();
        for (int[] p: properties){
            // 栈非空的时候，栈顶元素的防御力低于当前元素防御力，说明是一个弱角色
            // 然后把当前元素的防御力的值放进去
            // 因为最开始是以攻击力低防御力高排序的，所以之前放进去的一定是攻击力低的
            // 所以只需要比较防御力即可
            while (!st.isEmpty() && st.peek()<p[1]){
                st.pop();
                ans++;
            }
            st.push(p[1]);
        }
        return ans;
    }
}
