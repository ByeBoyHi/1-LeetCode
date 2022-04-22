package Daily_Topic.旅游终点站;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        List<List<String>> paths = new ArrayList<>();
        List<String> p1 = new ArrayList<>();
        List<String> p2 = new ArrayList<>();
        List<String> p3 = new ArrayList<>();
        p1.add("a");
        p1.add("b");
        p2.add("b");
        p2.add("c");
        p3.add("c");
        p3.add("d");
        paths.add(p1);
        paths.add(p2);
        paths.add(p3);
        System.out.println(Solution.destCity(paths));
    }

    public static String destCity(List<List<String>> paths) {
        String start = paths.get(0).get(0);
        // 存储：起点站终点站
        Map<String ,String> map = new HashMap<>();
        for (List<String> list: paths){
            map.put(list.get(0), list.get(1));
        }
        String end = "";
        while (map.get(start)!=null){  // 如果上一个起点站没有找到对应的终点站，就说明是终点站
            end = map.get(start);  // 获得当前站的终点站
            start = end;  // 然后当做起点站再去找
        }
        return end;
    }

}
