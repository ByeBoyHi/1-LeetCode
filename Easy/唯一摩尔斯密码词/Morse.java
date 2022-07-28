package Easy.唯一摩尔斯密码词;

import java.util.HashSet;
import java.util.Set;

public class Morse {
    public int uniqueMorseRepresentations(String[] words) {
        String[] password = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",
                ".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        Set<String> set = new HashSet<>();
        for (String word : words) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                sb.append(password[word.charAt(i)-'a']);
            }
            set.add(sb.toString());
        }
        return set.size();
    }
}
