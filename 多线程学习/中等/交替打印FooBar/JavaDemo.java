package 多线程学习.中等.交替打印FooBar;

import java.util.concurrent.Semaphore;

public class JavaDemo {
    public static void main(String[] args) {
        FooBarYield fooBar = new FooBarYield(5);
        Runnable printFoo = ()->{
            System.out.print("foo");
        };
        Runnable printBar = ()->{
            System.out.print("bar");
        };
        Thread fooThread = new Thread(()->{
            try {
                fooBar.foo(printFoo);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread barThread = new Thread(()->{
            try {
                fooBar.bar(printBar);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        fooThread.start();
        barThread.start();
    }
}

/**
 * Semaphore 方法
 *      Semaphore 是一个计数信号量。
 *      从概念上将，Semaphore包含一组许可证。
 *      如果有需要的话，每个acquire()方法都会阻塞，直到获取到一个可用的许可证。
 *      每个release()方法都会释放持有许可证的线程，并且归还Semaphore一个可用的许可证。
 *      然后，实际上并没有真实的许可证对象供线程使用，Semaphore只是对可用的数量进行管理维护。
 *   总结：如果线程要访问一个资源，就必须先获得信号量。如果信号量内部计数器大于0，信号量减一，然后允许共享这个资源；
 *        否则，如果信号量的计数器等于0，信号量将会把线程置入休眠，直至计数器大于0。当信号量完成时必须释放。
 */
class FooBar {
    private int n;
    private Semaphore fooSema = new Semaphore(1);
    private Semaphore barSema = new Semaphore(0);

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            fooSema.acquire();  // 值为1的时候执行下面的操作，然后信号量会-1
            printFoo.run();
            barSema.release();  // 释放bar信号量，这个信号量的值会+1
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            barSema.acquire();
            printBar.run();
            fooSema.release();
        }
    }
}

// Thread.yield 使得当前线程让出一次CPU，也就是从运行态到继续态
class FooBarYield{
    private int n;
    volatile boolean foobar = true;  // true -> foo执行
    public FooBarYield(int n){ this.n = n; }
    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            while (!foobar)  // 自旋
                Thread.yield();
            printFoo.run();
            foobar = false;
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            while (foobar)  // 自旋
                Thread.yield();
            printBar.run();
            foobar = true;
        }
    }
}
