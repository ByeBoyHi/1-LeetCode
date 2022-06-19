package Easy.罗马数字转整数;

/**
 * 罗马数字：
 *  I ：一
 *  V ：五
 *  X ：十
 *  L ：五十
 *  C ：百
 *  D ：五百
 *  M ：千
 *
 * 罗马数字规律：小数在大数前面，则做减法；小数在大数后面，则做加法。
 * 并且罗马数字的大数前面一半只挨着一个小数，所以在移动的时候，可以向前同时移动。
 */

/**
 * 静态方法可以被静态和非静态调用，但是非静态方法不能被静态方法调用。
 */

public class RomanToInt {
    /**
     * 举个例子：IV：4  VI：5  VII：6
     */
    public int romanToInt(String s){
        int sum = 0;
        int preNum = getValue(s.charAt(0));
        for (int i=1;i<s.length();i++){
            int n = getValue(s.charAt(i));
            if (n <= preNum){
                sum += preNum;
            }else {
                sum -= preNum;
            }
            preNum = n;
        }
        // 上面的for循环只走到了倒数第二个；
        // 最后一个值赋给了preNum后，就退出循环了；
        // 并且罗马数字的最后一个不管是几都要加上。
        sum += preNum;
        return sum;
    }
    public int getValue(char ch){
        return switch (ch) {
            case 'I' -> 1;
            case 'V' -> 5;
            case 'X' -> 10;
            case 'L' -> 50;
            case 'C' -> 100;
            case 'D' -> 500;
            case 'M' -> 1000;
            default -> 0;
        };
    }

    public static void main(String[] args) {
        RomanToInt romanToInt = new RomanToInt();
        System.out.println(romanToInt.romanToInt("MDCXLVIII"));
    }
}
