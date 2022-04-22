package Daily_Topic.今天你做核酸了吗;

import java.util.*;

public class Repeated_DNA_Sequences {
    public static void main(String[] args) {
        System.out.println(new Repeated_DNA_Sequences().findRepeatedDnaSequences(
//                "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
//                "AAAAAAAAAAAAA"
                ""
        ));
    }
    public List<String> findRepeatedDnaSequences(String s) {
        if (s.length()<10) return new ArrayList<>();

        List<String> res = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<s.length(); i++){
            sb.append(s.charAt(i));
            if (i>=9) {
                if (i>9) {
                    sb.deleteCharAt(0);
                }
                int index = 0;
                if (map.get(sb.toString())!=null){  // 判断这个字符串存储过没
                    index = map.get(sb.toString());
                }
                map.put(sb.toString(), index+1);
            }
        }
        for (String cur : map.keySet()) {
            if (map.get(cur) > 1) {
                res.add(cur);
            }
        }
        return res;
    }
}
