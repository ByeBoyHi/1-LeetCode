package Daily_Topic.FizzBuzz;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().fizzBuzz(15));
    }

    public List<String> fizzBuzz(int n) {
        List<String> ans = new ArrayList<>();
        for (int i=1; i<=n; i++){
            if (i%3==0 && i%5==0){
                ans.add("FizzBuzz");
            }else if (i%3==0){
                ans.add("Fizz");
            }else if (i%5==0){
                ans.add("Buzz");
            }else {
//                ans.add(""+i);
                // 把拼接换成转换，效率会高很多
                // 也会少很多的字符串垃圾
                ans.add(String.valueOf(i));
            }
        }
        return ans;
    }
}
