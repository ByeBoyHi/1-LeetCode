package Middle.������ײ;

import java.util.*;

public class AsteroidCollision {

    // �ٽ�

    /**
     * ��һ��ջ��ģ��������˶���
     * ���˱���ÿ�����򣬵�������Ϊ������ʱ�����ջ��Ԫ��Ϊ������
     * �ж�����˭�������ջ��������ǰ����ը�����Ҵ��Ϊfalse��
     * ������������ȻΪtrue����ô˵����ǰ�������ջ������Ԫ�ض��ǿɴ��ģ��Ž�ջ����
     */
    public int[] asteroidCollision2(int[] asteroids) {
        Deque<Integer> queue = new ArrayDeque<>();
        for (int aster : asteroids) {
            boolean alive = true;
            // С����ſ��ܷ�����ײ��������ֻ������װ����С����ģ������ȴ����������
            while (alive && aster < 0 && !queue.isEmpty() && queue.peek() > 0) {
                alive = queue.peek() < -aster;  // �Ƿ���ڣ����һ��һ���ͻ���ײ��ը
                if (queue.peek() <= -aster) { // �ᱬը
                    queue.pop();
                }
            }
            if (alive) {
                queue.push(aster);
            }
        }
        int size = queue.size();
        int[] ans = new int[size];
        for (int i = size - 1; i >= 0; i--) {
            ans[i] = queue.pop();
        }
        return ans;
    }

    /**
     * ���յ���ײ����Ȼֻ������һ�����������
     * 1. ��ߺ��ұߵ���ײ�������ߵ�С����ô��ߵ���ʧ����ߵļ�������
     * 2. ��ߺ��ұߵ���ײ������ұߵ�С����ô�ұߵ���ʧ���ұߵļ�������
     * ��Ϊ�Ǵ���߿�ʼ�����ģ�����ߵ��ߵ�����޷����ƶ���ȥ�Ҷ˵���ұ߽�����һ�������ƶ������ǣ��ظ�����Ĳ���
     * <p>
     * ��һ�������ƶ����ĺ������ƶ������������ǡ�
     * 1. ����н�������ô�����е��������Ƕ���ʧ�������������´�ġ�
     * �ϴ�����ǵ���һ�ߣ��ظ��жϡ�
     * 2. ���û�н���������������
     */
    // �����ٿ�
    List<Integer> ans = new ArrayList<>();

    public int[] asteroidCollision(int[] asteroids) {
        process(asteroids, 0, asteroids.length - 1);
        Collections.sort(ans);
        int[] ret = new int[ans.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = asteroids[ans.get(i)];
        }
        return ret;
    }

    /**
     * @param asteroids ��������
     * @param left      ��߽�
     * @param right     �ұ߽�
     */
    public void process(int[] asteroids, int left, int right) {
        if (left < 0 || right >= asteroids.length || left > right) return;

        if (left == right) {
            ans.add(left);
            return;
        }

        int leftAsteroid = leftMax(asteroids, left, right);   // ���ֵ
        int rightAsteroid = rightMax(asteroids, left, right); // ��Сֵ

        // �����ҵ������������Ǹ������߶�������
        // ��ô�м����������Ҫ����������
        if (
                (asteroids[leftAsteroid] > 0 && asteroids[rightAsteroid] > 0)
                        ||
                        (asteroids[leftAsteroid] < 0 && asteroids[rightAsteroid] < 0)
        ) {

            int a = Math.min(leftAsteroid, rightAsteroid);
            int b = Math.max(leftAsteroid, rightAsteroid);
            leftAsteroid = a;
            rightAsteroid = b;
            for (int i = leftAsteroid;
                 i <= rightAsteroid;
                 i++) {
                ans.add(i);
            }
            process(asteroids, left, leftAsteroid - 1);
            process(asteroids, rightAsteroid + 1, right);
        } else {

            if (leftAsteroid < rightAsteroid) {  // �н���
                if (asteroids[leftAsteroid] + asteroids[rightAsteroid] > 0) {
                    // ��߸����м����еĶ���ʧ
                    ans.add(leftAsteroid);
                    process(asteroids, left, leftAsteroid - 1); // ���
                    if (rightAsteroid != asteroids.length - 1) {
                        asteroids[rightAsteroid + 1] = asteroids[leftAsteroid];
                    }
                    process(asteroids, rightAsteroid + 1, right); // �ұ�
                } else if (asteroids[leftAsteroid] + asteroids[rightAsteroid] < 0) {
                    // �ұ߸����м����еĶ���ʧ
                    ans.add(rightAsteroid);
                    process(asteroids, rightAsteroid + 1, right);  // �ұ�
                    if (leftAsteroid != 0) {
                        asteroids[leftAsteroid - 1] = asteroids[rightAsteroid];
                    }
                    process(asteroids, left, leftAsteroid - 1);  // ���
                } else {

                    /*
                        �ж������������ֵ����Сֵ�ж��ٸ��������һ���࣬��ô���¶����һ�������Եλ��
                     */
                    int leftNum = 1;
                    int rightNum = 1;
                    for (int i = leftAsteroid + 1; i < rightAsteroid; i++) {
                        if (asteroids[i] == asteroids[leftAsteroid]) {
                            leftNum++;
                        }
                        if (asteroids[i] == asteroids[rightAsteroid]) {
                            rightNum++;
                        }
                    }
                    if (leftNum != rightNum) {
                        if (leftNum > rightNum) { // �ұ�ȫ����������������������ұߵ�

                        } else { // ���ȫ�������������ұ���������ߵ�

                        }
                    }

                    // һ���࣬ͬ���ھ����м�����������ʧ
                    process(asteroids, left, leftAsteroid - 1);
                    process(asteroids, rightAsteroid + 1, right);
                }
            } else {  // û�н���������������
                if (leftAsteroid == rightAsteroid) {
                    ans.add(leftAsteroid);
                } else {
                    process(asteroids, rightAsteroid + 1, leftAsteroid - 1);
                }
                process(asteroids, left, rightAsteroid);
                process(asteroids, leftAsteroid, right);
            }
        }
    }

    public int leftMax(int[] asteroids, int left, int right) {
        int ans = left;
        for (int i = left + 1; i <= right; i++) {
            if (asteroids[i] > asteroids[ans]) {
                ans = i;
            }
        }
        return ans;
    }

    public int rightMax(int[] asteroids, int left, int right) {
        int ans = right;
        for (int i = right - 1; i >= left; i--) {
            if (asteroids[i] < asteroids[ans]) {
                ans = i;
            }
        }
        return ans;
    }
}
