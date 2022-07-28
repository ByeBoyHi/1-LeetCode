package Easy.山羊拉丁文;

import java.util.HashSet;
import java.util.Set;

public class GoatLatin {

    static Set<Character> set = new HashSet<>();
    static {
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');
        set.add('A');
        set.add('E');
        set.add('I');
        set.add('O');
        set.add('U');
    }

    public String toGoatLatin(String sentence) {
        String[] split = sentence.split(" ");
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<split.length; i++){
            if (set.contains(split[i].charAt(0))){  // 元音字母
                sb.append(split[i]).append("m");
            }else{  // 辅音字母
                sb.append(split[i].substring(1)).append(split[i].charAt(0)).append("m");
            }
            sb.append("a".repeat(i + 2)).append(" ");
        }

        return sb.toString().trim();
    }
}
