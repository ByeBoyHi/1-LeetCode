package Easy.最常见的单词;

import java.util.*;

public class MostCommonWord {
    public String mostCommonWord(String paragraph, String[] banned) {
        paragraph = paragraph.replaceAll(","," ")
                .replaceAll("\\."," ")
                .replaceAll("!", " ")
                .replaceAll("\\?", " ")
                .replaceAll("'", " ")
                .replaceAll(";", " ")
                .toLowerCase();
        HashMap<String, Integer> map = new HashMap<>();
        Set<String> set = new HashSet<>(Arrays.asList(banned));
        set.add("");
        String[] strings = paragraph.split(" ");
        for (String string : strings) {
            if (set.contains(string)) continue;
            map.put(string, map.getOrDefault(string, 0) + 1);
        }
        map.forEach((key1, value) -> System.out.println(key1 + " " + value));
        int num = 0;
        String ans = "";
        for (String cur: map.keySet()){
            if (map.get(cur)>num){
                num = map.get(cur);
                ans = cur;
            }
        }
        return ans;
    }
}
