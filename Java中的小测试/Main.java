package Java中的小测试;

public class Main{


    public static void main(String[] args) {

        // t1
        // 关于线程同步的测试
        // 这个取值在没有休眠的时候，因为是在主线程执行的
        // 所以可能还没有执行完，主线程就提前取值了（小问题）
//        long start = System.currentTimeMillis();
//        for (int i = 0; i < 100; i++) {
//            new Thread(new ThreadTest()).start();
//        }
////        try {
////            Thread.sleep(500);
////        } catch (InterruptedException e) {
////            throw new RuntimeException(e);
////        }
//        long end = System.currentTimeMillis();

//        System.out.println((end - start) + "ms:" + count);

        // ---------------------------------

        // t2
        // 测试锁循环和锁变量的效率
        // 由于并发的缘故，这个时间关系没有测试出来
//        synchronized (ThreadTest.class) {
//            ThreadTest.test();
//        }
//        ThreadTest1.test();

        // ---------------------------------

        // t3
        // 测试主线程是否等子线程
        System.out.println("Hello");
        ThreadTest2.test();
    }
}


// t1 t2
class ThreadTest implements Runnable {

    private static int count = 0;

    @Override
    public void run() {
        synchronized (ThreadTest.class) {
            for (int i = 0; i < 1000000; i++) {
                count++;
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(new ThreadTest()).start();
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(count);
    }

    public static void test(){
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            new Thread(new ThreadTest()).start();
        }
        long end = System.currentTimeMillis();
        System.out.println((end-start)+"ms");
    }
}

// t1 t2
class ThreadTest1 implements Runnable{
    private static int count =1;
    @Override
    public void run() {
        for (int i = 0; i < 1000000; i++) {
            synchronized (ThreadTest1.class){
                count++;
            }
        }
    }

    public static void test(){
        long start1 = System.currentTimeMillis();
        for (int i=0; i<1000; i++){
            new Thread(new ThreadTest1()).start();
        }
        long end1 = System.currentTimeMillis();
        System.out.println((end1-start1)+"ms");
    }
}

// t3
class ThreadTest2 implements Runnable{

    @Override
    public void run() {
        System.out.println("World");
    }

    public static void test(){
        for (int i = 0; i < 5; i++) {
            new Thread(new ThreadTest2()).start();
        }
    }
}