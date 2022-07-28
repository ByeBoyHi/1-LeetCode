package 多线程学习.简单.按序打印;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 多线程并发主要为多任务情况设计，可能会引发一些漏洞，如下：
 * 竞态条件：由于多进程之间的竞争执行，导致程序未按照期望的顺序输出。
 * 死锁：并发程序等待一些必要资源，导致没有程序可以执行。
 * 资源不足：进程被永久剥夺了运行所需的资源。
 *
 * 本题的漏洞是 竞态条件。
 *
 * 思路：
 *  1. 建立依赖关系：A>B>C，也就是A执行了B才能执行，B执行了C才能执行。
 *      依赖关系可以使用并发机制实现，使用一个共享变量 firstJobDone协调A和B，使用另一个 secondJobDone 协调 B和 C。
 *  2. 锁住关键部分，防止同步出现数据错误：synchronized
 *
 */
public class Foo {
    // Atomic原子操作就是volatile + cas。
    // 在这里同时只会有一个线程去改变这个值, 所以cas可以不用, 那也就是说 可以直接volatile
    /**
     *    CAS
     * compare and swap 你可以理解为一共包含三个参数（V，E，N），分别代表变量现在值，预期的值，还有要更新的值。
     * 只有 V = E，即现在的值和预期的值相等的时候才更新为新的值 N，
     * 如果 V 和 E 不相等说明什么呢？说明已经有其他线程做了更新，则当前线程什么都不做。
     * 简单地说就是，变量现在的值和你期望中的值是不是还一样，如果不一样说明已经被人改过了。
     * 当然如果一样也不能说明一定没被改过，可能改过了又被改回来了。
     */
    private AtomicInteger firstJobDone = new AtomicInteger(0);
    private AtomicInteger secondJobDone = new AtomicInteger(0);

    public Foo() {}

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        firstJobDone.incrementAndGet();  //++并赋值
    }

    public void second(Runnable printSecond) throws InterruptedException {
        while (firstJobDone.get()!=1) {}  // 等待
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        secondJobDone.incrementAndGet(); // ++并赋值
    }

    public void third(Runnable printThird) throws InterruptedException {
        while (secondJobDone.get()!=1){}  // 等待
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}
