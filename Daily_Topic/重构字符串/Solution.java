package Daily_Topic.重构字符串;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 给定一个字符串S，检查是否能重新排布其中的字母，使得"两相邻的字符不同"。
 * 用大顶堆进行出现次数的排序，可以让出现最多的先用掉，这样后面剩下的就是还没用掉的，就不会有相邻字符一样的情况
 * 每次弹出两个元素是为了使元素可以交错出现，避免相邻相同
 * 若可行，输出任意可行的结果。若不可行，返回空字符串。
 *
 * 示例1:
 * 输入: S = "aab"
 * 输出: "aba"
 *
 * 示例 2:
 * 输入: S = "aaab"
 * 输出: ""
 *
 * 注意:S 只包含小写字母并且长度在[1, 500]区间内。
 *
 * 这个题目的意思是如果某一个字母出现的次数超过了这个（字符串数+1）的一半，那么就不能重构。
 */
public class Solution {

    public static void main(String[] args){
        String s = "aab";
        String s1 = "abb";
        String s2 = "asdawa";
        System.out.println(reorganizeString(s));
        System.out.println(reorganizeString(s1));
        System.out.println(reorganizeString(s2));
    }



    /**
     * 用大顶堆进行重构
     * 1. 对每个字母出现的次数进行计数，存入newChar这个类的对象里面（这个类是我们自定义的）
     * 2. 构造一个优先级的队列，重写comparable方法，构造大顶堆规则
     * 3. 依次把每个字母放进这个"优先级队列"里面，在放入的时候判断是否符合重构要求
     * 4. 放入完毕后，用一个StringBuilder进行字符串拼接重构
     * 5. 每次让出现频次最大的弹出，放进字符串里面，然后频次都减一（相当于已经拼接了一个）后判断
     *      是否等于0，等于0就不放回优先级队列了
     * 6. 这样重复，里面的大顶堆会因为频次的变化不断变化，实现重构的效果，最后拼接完成的字符串就
     *      是我们要返回的
     * 7. 如果到最后队列里面还剩下一个，我们对他进行单独处理，然后拼接到字符串里面就行了
     *
     * @param S:判断是否需要重构的字符串
     * @return :如果可以重构，返回其中的一个重构字符串；如果不能，则返回空串。
     */
    public static String reorganizeString(String S){
        int[] count = new int[26]; // 存储26个字母
        for(int i=0; i<S.length(); i++){
            count[S.charAt(i)-'a']++;  // 里面都是小写，减a就得到了这个字母对应的下标位置，然后下标位置的值+1实现计数
        }

        // 定义一个有26个长度的优先级队列
        // 比较规则是大于的方式：大顶堆
        PriorityQueue<newChar> pq = new PriorityQueue<>(26, Comparator.comparingInt(o -> o.count));

        // 装弹
        for (int i=0; i<count.length; i++){
            if (count[i]>0 && count[i]<=(S.length()+1)/2){
                pq.add(new newChar(count[i], (char) ('a'+i)));  // 强转的时候，前面的char也要括起来
            } else if (count[i] > (S.length() + 1) / 2){  // 某个字母是数量过半，无法重构
                return "";
            }
        }

        // 装弹完毕，用StringBuilder进行字符串的重构拼接
        StringBuilder sb = new StringBuilder();

        // 每次从队列里面弹出两个第一大和第二大的
        // 然后对他们进行重构
        while (pq.size()>1){  // 至少有两个
            newChar c1 = pq.poll();  // 第一大
            newChar c2 = pq.poll();  // 第二大
            assert c2 != null;  // 断言c2!=null
            sb.append(c1.letter).append(c2.letter);

            if (--c2.count>0) pq.add(c2);  // 如果还有剩的，再放回堆
            if (--c1.count>0) pq.add(c1);
        }
        if (pq.size()==1){  // 如果只有一个了
            newChar t = pq.poll();
            // 进行最后的拼接
            int max = Math.max(0, t.count);
            for (int i=0; i<max; i++)
                sb.append(String.valueOf(t.letter));
        }
        return sb.toString();
    }
    static class newChar{
        int count; // 这个字符出现的次数
        char letter; // 这个字符的信息
    public newChar(int count, char letter){
            this.count = count;
            this.letter = letter;
        }
    }
}
