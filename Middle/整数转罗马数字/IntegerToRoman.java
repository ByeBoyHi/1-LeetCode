package Middle.整数转罗马数字;

public class IntegerToRoman {
    public String intToRoman(int num) {

        StringBuilder sb = new StringBuilder();
        int[] numbers = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] simples = {"M", "MC", "D", "DC", "C", "CX", "L", "LX", "X", "XI", "V", "VI", "I"};
        // 一个一个尝试
        for (int i = 0; i < simples.length; i++) {
            int cur = numbers[i];
            String s = simples[i];
            while (num>cur){
                num-=cur;
                sb.append(s);
            }
            if (num==0){
                break;
            }
        }

        return sb.toString();
    }

}
