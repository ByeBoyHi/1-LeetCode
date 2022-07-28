package 多线程学习.中等.打印零与奇偶数;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

public class PrintZeroEvenOdd {

}

// Semaphore信号量
/**
 * 信号量机制：信号量大于0，则会减一然后执行，信号量等于0则会阻塞。
 * 信号量为1表明只有一个许可可分配，可以实现互斥。
 * 当信号量分配为0的时候，就只能进行释放，从而实现信号量+1，使得可以获得。
 * 会有内存一致性问题：即一个线程调用release()是在另一个线程调用acquire之前。
 */
class ZeroEvenOdd1 {
    private final int n;
    // 信息量
    Semaphore s1 = new Semaphore(1);  // 记录0
    Semaphore s2 = new Semaphore(0);  // 记录奇数
    Semaphore s3 = new Semaphore(0);  // 记录偶数

    public ZeroEvenOdd1(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            s1.acquire();  // -1
            printNumber.accept(0);
            if ((i & 1) == 0) s2.release(); // 偶数，释放s2  // +1
            else s3.release();  // 奇数，释放s3  // +1
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i += 2) {  // 奇数
            s2.acquire();
            printNumber.accept(i);
            s1.release();  // 每次在执行结束都会重新回到s1，输出0
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i += 2) {  // 偶数
            s3.acquire();
            printNumber.accept(i);
            s1.release();
        }
    }
}

// synchronized：wait+notifyAll
class ZeroEvenOdd2 {
    private final int n;

    public ZeroEvenOdd2(int n) {
        this.n = n;
    }

    // 锁对象
    private final Object obj = new Object();
    // 状态变量
    private int state = 1;

    public void zero(IntConsumer printNumber) throws InterruptedException {
        synchronized (obj) {
            for (int i = 1; i <= n; i++) {
                while (state != 1) obj.wait();
                printNumber.accept(0);
                if ((i & 1) == 0) state = 2;  // 偶数
                else state = 3;  // 奇数
                obj.notifyAll();
            }
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        int count = n % 2 == 1 ? n / 2 + 1 : n / 2;
        synchronized(obj){
            for(int i=1; i<=n; i+=2){  // 1~n
                while(state != 3) obj.wait();
                printNumber.accept(i);
                state = 1;
                obj.notifyAll();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        synchronized (obj) {
            for (int i = 2; i <= n; i += 2) {  // 2~n
                while (state != 2) obj.wait();
                printNumber.accept(i);
                state = 1;
                obj.notifyAll();
            }
        }
    }
}

// ReentrantLock + Condition
/**
 * ReentrantLock Condition 类似于synchronized，更加灵活
 * 在 lock() 和 unlock() 之间就是锁同步的部分，然后用condition来实现阻塞
 * 并且在一个lock()区域里面，可以有多个condition实现阻塞，从而实现我们的需求
 * 需要注意的是：conditionA阻塞的对象会放在cA的阻塞队列里面，另一个condition的signalAll是无法唤醒的
 * 在线程池的阻塞队列会使用这个组合，比synchronized更灵活，但是没有它方便
 * 在synchronized无法实现需求的时候，我们可以用这个组合，他的灵活性可以担任大部分场景
 * 如非必要，使用尽量简单的工具
 */
class ZeroEvenOdd3 {
    private final int n;

    private final Lock loc = new ReentrantLock();
    private final Condition cond = loc.newCondition();
    private int state = 1;

    public ZeroEvenOdd3(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for(int i=1; i<=n; i++){
            loc.lock();
            try{
                while(state != 1) cond.await();
                printNumber.accept(0);
                if((i & 1) == 1) state = 3;
                else state = 2;
                cond.signalAll();
            }finally{
                loc.unlock();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for(int i=2; i<=n; i+=2){
            loc.lock();
            try{
                while(state != 2) cond.await();
                printNumber.accept(i);
                state = 1;
                cond.signalAll();
            }finally{
                loc.unlock();
            }
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for(int i=1; i<=n; i+=2){
            loc.lock();
            try{
                while(state != 3) cond.await();  // wait
                printNumber.accept(i);
                state = 1;
                cond.signalAll();  // notifyAll
            }finally{
                loc.unlock();
            }
        }
    }
}

// Thread.yield() + volatile
class ZeroEvenOdd4 {
    private final int n;

    private volatile int state = 1;

    public ZeroEvenOdd4(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for(int i=1; i<=n; ){
            if(state == 1){
                printNumber.accept(0);
                if((i & 1) == 1) state = 3;
                else state = 2;
                i++;
            }else{
                Thread.yield();  // 只要不是自己的状态码，就不断的让出CPU
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for(int i=2; i<=n; ){
            if(state == 2){
                printNumber.accept(i);
                i+=2;
                state = 1;
            }else{
                Thread.yield();
            }
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for(int i=1; i<=n; ){
            if(state == 3){
                printNumber.accept(i);
                i+=2;
                state = 1;
            }else{
                Thread.yield();
            }
        }
    }
}

// BlockingQueue LinkedBlockingQueue
// 可选阻塞队列：对头一定是阻塞时间最久的，队尾一定是阻塞时间最短的
// 可以不断的放元素进行，然后依次执行每个线程
class ZeroEvenOdd5 {
    private final int n;

    // 初始化的时候，把一个元素放入到队头，用于取出执行
    private final BlockingQueue<Integer> b1 = new LinkedBlockingQueue<>(){{
        offer(0);
    }};
    private final BlockingQueue<Integer> b2 = new LinkedBlockingQueue<>();
    private final BlockingQueue<Integer> b3 = new LinkedBlockingQueue<>();

    public ZeroEvenOdd5(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for(int i=1; i<=n; i++){
            b1.take();  // 取出队列头部的第一个阻塞线程，用于执行
            printNumber.accept(0);
            if((i & 1) == 1) b3.put(0);  // 往奇数队列放元素，用于执行
            else b2.put(0);  // 往偶数队列放元素，用于执行
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for(int i=2; i<=n; i+=2){
            // take只会正在有元素的时候执行，没有的时候会阻塞
            // if necessary until an element becomes available.
            b2.take();
            printNumber.accept(i);
            b1.put(0);
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for(int i=1; i<=n; i+=2){
            b3.take();
            printNumber.accept(i);
            b1.put(0);
        }
    }
}

// CyclicBarrier
// 通过await把成员放入等待屏障，才能进行后续的执行
class ZeroEvenOdd6 {
    private final int n;

    // parties参数：互相竞争的对手有多少个-1，等待队列的大小
    private CyclicBarrier cb1 = new CyclicBarrier(1);
    private final CyclicBarrier cb2 = new CyclicBarrier(2);
    private final CyclicBarrier cb3 = new CyclicBarrier(2);

    public ZeroEvenOdd6(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        try{
            for(int i=1; i<=n; i++){
                // 把这个放入等待队列，他在等待队列的第一个，就会先执行
                cb1.await();
                printNumber.accept(0);
                cb1 = new CyclicBarrier(2);
                if((i & 1) == 1) cb3.await();  // 奇数
                else cb2.await();  // 偶数
            }
            cb1.await();
        }catch(BrokenBarrierException e){
            e.printStackTrace();
        }
    }

    // 偶数
    public void even(IntConsumer printNumber) throws InterruptedException {
        for(int i=2; i<=n; i+=2){
            try{
                cb2.await();
                printNumber.accept(i);
                cb1.await();
            }catch(BrokenBarrierException e){
                e.printStackTrace();
            }
        }
    }

    // 奇数
    public void odd(IntConsumer printNumber) throws InterruptedException {
        for(int i=1; i<=n; i+=2){
            try{
                cb3.await();
                printNumber.accept(i);
                cb1.await();
            }catch(BrokenBarrierException e){
                e.printStackTrace();
            }
        }
    }
}

// CountDownLatch：倒计时锁存器，当倒计时为0的时候执行
class ZeroEvenOdd7 {
    private final int n;

    // 锁个数初始值
    private CountDownLatch cd1 = new CountDownLatch(0);
    private CountDownLatch cd2 = new CountDownLatch(1);
    private CountDownLatch cd3 = new CountDownLatch(1);

    public ZeroEvenOdd7(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for(int i=1; i<=n; i++){
            cd1.await();
            printNumber.accept(0);
            cd1 = new CountDownLatch(1);
            if((i & 1) == 1) cd3.countDown();  // 唤醒
            else cd2.countDown();  // 唤醒
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for(int i=2; i<=n; i+=2){
            cd2.await();
            printNumber.accept(i);
            cd2 = new CountDownLatch(1);
            cd1.countDown();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for(int i=1; i<=n; i+=2){
            cd3.await();
            printNumber.accept(i);
            cd3 = new CountDownLatch(1);
            cd1.countDown();
        }
    }
}

class ZeroEvenOdd8 {
    private final int n;

    // 为三种可能分配配备名字和线程
    private final Map<String, Thread> map = new HashMap<>();
    // 倒计时锁存器：三个需求
    private final CountDownLatch latch = new CountDownLatch(3);

    public ZeroEvenOdd8(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        map.put("zero", Thread.currentThread());  // 三个都公用一个线程
        latch.countDown();  // 唤醒之后马上放进去
        latch.await();
        for(int i=1; i<=n; i++){
            printNumber.accept(0);
            if((i & 1) == 1) LockSupport.unpark(map.get("odd"));  // 唤醒
            else LockSupport.unpark(map.get("even"));  // 唤醒
            LockSupport.park();  // 阻塞
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        map.put("even", Thread.currentThread());
        latch.countDown();
        latch.await();
        for(int i=2; i<=n; i+=2){
            LockSupport.park();  // 阻塞
            printNumber.accept(i);
            LockSupport.unpark(map.get("zero"));  // 唤醒
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        map.put("odd", Thread.currentThread());
        latch.countDown();
        latch.await();
        for(int i=1; i<=n; i+=2){
            LockSupport.park();
            printNumber.accept(i);
            LockSupport.unpark(map.get("zero"));
        }
    }
}




















