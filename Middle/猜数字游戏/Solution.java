package Middle.猜数字游戏;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    // 第一轮找A，然后把对应的值全部去掉
    // 第二轮找B，找到存在就去掉，防止找重复了
    public String getHint(String secret, String guess) {
        int a = 0;
        int b = 0;
        char[] secrets = secret.toCharArray();
        char[] guesses = guess.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        // 找出所有A
        for (int i=0; i<secrets.length; i++){
            if (secrets[i]==guesses[i]){
                a++;
                secrets[i] = 'X';
                guesses[i] = 'X';
            }else {
                map.put(secrets[i], map.getOrDefault(secrets[i], 0)+1);
            }
        }
        // 找出所有B
        for (char g : guesses) {
            if (g != 'X' && map.containsKey(g)) {
                b++;
                if (map.get(g) - 1 != 0) {
                    map.put(g, map.get(g) - 1);
                } else {
                    map.remove(g);
                }
            }
        }
        return a+"A"+b+"B";
    }
}
