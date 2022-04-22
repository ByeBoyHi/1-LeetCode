package 多线程学习.简单.按序打印;

public class Foo3_volatile实现可见性_main {
    public static void main(String[] args) {
        Foo3 foo3 = new Foo3();
        new Thread(()->{
            try {
                foo3.first(()->{
                    System.out.println("1");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try {
                foo3.second(()->{
                    System.out.println("2");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try {
                foo3.third(()->{
                    System.out.println("3");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
class Foo3 {

    public Foo3() {}
    volatile int count=1;
    public void first(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        count++;
    }

    public void second(Runnable printSecond) throws InterruptedException {
        while (count!=2);
        printSecond.run();
        count++;
    }

    public void third(Runnable printThird) throws InterruptedException {
        while (count!=3);
        printThird.run();
    }
}