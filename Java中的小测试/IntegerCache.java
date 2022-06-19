package Java中的小测试;

public class IntegerCache {
    public static void main(String[] args) {
        Integer a = 123;
        Integer b = Integer.valueOf(123);
        Integer c = new Integer(123);
        System.out.println(a==b);  // true
        System.out.println(a==c);  // false
        System.out.println(b==c);  // false
        System.out.println("----------------------------");
        System.out.println(a.equals(b));  // true
        System.out.println(a.equals(c));  // true
        System.out.println(b.equals(c));  // true
    }
}
