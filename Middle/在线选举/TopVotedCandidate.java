package Middle.在线选举;

import java.util.*;

public class TopVotedCandidate {

    /*
        思路：
        用一个大根堆，大根堆里面存放的数据结构有数据：票选人，票数，最近的票选时间
        大根堆首先根据票数降序，其次按照票选时间降序
     */

    static class Person {
        int num;
        LinkedList<Integer> time;  // 最近的投票时间
        int count; // 票数

        public Person() {
        }

        public Person(int num, int count, LinkedList<Integer> time) {
            this.num = num;
            this.count = count;
            this.time = time;
        }
    }

    int[] votes;

    PriorityQueue<Person> pq = new PriorityQueue<>((p1, p2) -> {
        if (p1.count == p2.count) {
            if (p2.time.peekLast() != null && p1.time.peekLast() != null) {
                return p2.time.peekLast() - p1.time.peekLast();
            }
            return 0;
        }
        return p2.count - p1.count;
    });

    Map<Integer, LinkedList<Integer>> map = new HashMap<>();

    int[] count = new int[5005];
    int time;

    public TopVotedCandidate(int[] persons, int[] times) {
        if (times.length == 0) {
            return;
        }
        time = times[times.length-1];
        for (int i = 0; i < persons.length; i++) {
            count[persons[i]]++;
            if (!map.containsKey(persons[i])) {
                LinkedList<Integer> time = new LinkedList<>();
                time.add(times[i]);
                map.put(persons[i], time);
            } else {
                map.get(persons[i]).add(times[i]);
            }
        }

        for (int i = 0; i < 5005; i++) {
            if (count[i] != 0) {
                pq.add(new Person(i, count[i], map.get(i)));
            }
        }
        votes = new int[times[times.length - 1] + 1];
        process(persons, times);
    }

    private void process(int[] persons, int[] times) {
        int voteIndex = votes.length - 1;
        for (int i = times.length - 1; i >= 0; i--) {

            while (voteIndex > times[i]) {
                assert pq.peek() != null;
                votes[voteIndex] = pq.peek().num;
                voteIndex--;
            }

            if (voteIndex == times[i]) {
                assert pq.peek() != null;
                Person cur = pq.poll();
                boolean flag = false;
                if (cur.num == persons[i] && cur.count != count[persons[i]]) {
                    flag = true;
                    if (count[persons[i]] == 0) {
                        pq.remove(cur);
                    } else {
                        int dif = cur.count - count[persons[i]];
                        cur.count = count[persons[i]];
                        while (dif != 0) {
                            cur.time.removeLast();
                            dif--;
                        }
                        pq.add(cur);
                    }
                }
                if (flag) {
                    cur = pq.poll();
                }
                assert cur != null;
                votes[voteIndex] = cur.num;
                voteIndex--;

                count[persons[i]]--;
                if (cur.num == persons[i]) {
                    cur.count--;
                    cur.time.removeLast();
                }
                pq.add(cur);
            }
        }
        System.out.println(Arrays.toString(votes));
    }

    public int q(int t) {
        if (t > votes.length) {
            return votes[time];
        }
        return votes[t];
    }

    static class TopVotedCandidate2 {
        int[] persons;
        int[] times;
        int[] count;//用于记录每个人的实时票数
        int[] lead;//用于记录领先的人

        public TopVotedCandidate2(int[] persons, int[] times) {
            this.persons = persons;
            this.times = times;
            count = new int[5005];
            lead = new int[persons.length];  // 总的投票次数的长度
            int max = -1;
            for (int i = 0; i < persons.length; i++) {
                count[persons[i]]++;
                if (count[persons[i]] >= max) { // 出现了新的leader
                    lead[i] = persons[i];
                    max = count[persons[i]];
                } else {
                    // 用之前的leader代替当前的leader
                    // 对于times来说，这就是一个时间区间的leader
                    lead[i] = lead[i - 1];
                }
            }
        }

        public int q(int t) {
            if (t >= times[persons.length - 1]) {
                return lead[persons.length - 1];
            }
            int l = 0, r = persons.length - 1;
            // 这里退出循环是 l=r-1
            // 我们最后可以得到两个区间：r~r+1 和 l~l+1，且l+1=r
            while (l < r - 1) {
                int mid = l + (r - l) / 2;
                if (times[mid] > t) {
                    r = mid - 1;
                } else {
                    l = mid;
                }
            }
            // 如果最后确定的时间点小于等于我们传进来的，那么这个leader就是r到r+1所在范围，即lead[r]
            // 如果最后确定的时间点大于我们传进来的，那么这个leader就在l到l+1所在范围，即lead[l]
            return times[r] <= t ? lead[r] : lead[l];
        }
    }

    public static void main(String[] args) {
        // 负数在前，正数在后，默认升序
//        Map<Integer, Integer> tree = new TreeMap<>((x,y)->y-x);
//        tree.put(5,5);
//        tree.put(2,3);
//        tree.put(3,8);
//        tree.put(4,4);
//        for (Integer in: tree.keySet()){
//            System.out.println(tree.get(in));
//        }

        TopVotedCandidate voted = new TopVotedCandidate(new int[]{
                0, 0, 1, 1, 2
        }, new int[]{
                0, 67, 69, 74, 87
        });
    }
}
