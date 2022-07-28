package Easy.棒球比赛;

import java.util.ArrayList;
import java.util.List;

public class BaseballGame {
    public int calPoints(String[] ops) {
        List<Integer> list = new ArrayList<>();
        for (String op: ops){
            switch (op) {
                case "+" -> {
                    int size = list.size() - 1;
                    list.add(list.get(size - 1) + list.get(size));
                }
                case "D" -> list.add(list.get(list.size() - 1) * 2);
                case "C" -> list.remove(list.size() - 1);
                default -> list.add(Integer.parseInt(op));
            }
        }
        int ans = 0;
        for (int i: list){
            ans+=i;
        }
        return ans;
    }
}
