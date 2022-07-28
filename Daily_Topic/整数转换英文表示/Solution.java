package Daily_Topic.整数转换英文表示;

import java.util.LinkedList;

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().numberToWords(1));
    }
    public String numberToWords(int num) {
        if (num == 0) return "Zero";

        int a;
        int b;
        int c;

        /**
         * 数字三位一体：
         *      第一种情况：个位十位百位都是 0 就跳过本次循环。
         *      第二种情况：十位是 1，存十几。
         *      第三种情况：个位是 0，存几十。
         *      第四种情况：只有一位不是 0，存对应位置的值
         *      第五种情况：正常存。
         *
         *      1 000 010
         */

        String[] numbers = new String[]{
                "Zero","One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", // 0 ~ 10 (0~10)
                "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen", // 11~19
                "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety",  // 10 ~ 90 (20~28)
                "Thousand", "Million", "Billion"  // 29 ~ 31
        };
        int cur = 28;
        int temp = num;
        LinkedList<String> ans = new LinkedList<>();
        StringBuilder s = new StringBuilder();
        while ((""+num).length()>=3){
            a = num%10;
            b = (num/10)%10;
            c = (num/100)%10;
            if (b==1){
                ans.add(numbers[0]);
                ans.add(numbers[10+a]);
                ans.add(numbers[c]);
            }else{
                ans.add(numbers[a]);
                if (b==0) {
                    ans.add(numbers[0]);
                }else {
                    ans.add(numbers[19 + b]);
                }
                ans.add(numbers[c]);
            }
            num = num/1000;
        }

        if (num!=0) {
            if (("" + num).length() == 1) {
                a = num % 10;
                ans.add(numbers[a]);
            } else {
                a = num % 10;
                b = (num / 10) % 10;
                if (a == 0 || b == 1) {
                    ans.add(numbers[0]);
                    if (a == 0) {
                        ans.add(numbers[19 + b]);
                    } else {
                        ans.add(numbers[10 + a]);
                    }
                } else {
                    ans.add(numbers[a]);
                    ans.add(numbers[19 + b]);
                }
            }
        }
        String as;
        String bs;
        String cs;
        for (int i=2; ans.size()>=3; i+=3){
            as = ans.get(0);
            bs = ans.get(1);
            cs = ans.get(2);
            boolean bool = as.equals("Zero") && bs.equals("Zero") && cs.equals("Zero");
            if (i!=2 && !bool){
                s.append(numbers[cur]).append(" ");
            }
            cur++;
            if (!as.equals("Zero") && !bs.equals("Zero") && !cs.equals("Zero")){  // 三都不为0
                s.append(as).append(" ");
                s.append(bs).append(" ");
                s.append("Hundred ");
                s.append(cs).append(" ");
            }else if (bool){  // 三都为0

            }else {
                if (!as.equals("Zero")){
                    s.append(as).append(" ");
                }
                if (!bs.equals("Zero")) {
                    s.append(bs).append(" ");
                }
                if (!cs.equals("Zero")) {
                    s.append("Hundred ");
                    s.append(cs).append(" ");
                }
            }
            ans.remove();
            ans.remove();
            ans.remove();
        }
        if (!ans.isEmpty()){
            if ((""+temp).length()>=4) {  // 当里面已经有大于等于3个数字的时候，才会考虑加单位
                s.append(numbers[cur]).append(" ");
            }
            for (String str: ans){
                if (!str.equals("Zero")){
                    s.append(str).append(" ");
                }
            }
        }

        String[] strings = s.toString().trim().split(" ");
        StringBuilder res = new StringBuilder();
        for (int i=strings.length-1; i>=0; i--){
            res.append(strings[i]).append(" ");
        }

        return res.toString().trim();
    }
}