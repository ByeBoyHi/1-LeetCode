package Java中的小测试;

public class NullTest {

    public static void f(){
        System.out.println("test1_Null.f");
    }

    // 由于是静态方法，属于类，因此不需要实例化也可以使用，这里的前缀调用相当于没有。
    public static void main(String[] args){
        try {
            ((NullTest)null).f();  // 编译安全
            System.out.println(((NullTest)null).getClass()); // 异常（实例化后才能调用）
        }catch (Exception e){
            System.out.println("Null Pointer");
        }
    }

}
