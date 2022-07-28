package 多线程学习.简单.按序打印;

class Foo2 {

    private boolean firstFinished = false;  // 屏障1
    private boolean secondFinished = false;  // 屏障2
    private Object lock = new Object();  // 锁

    public Foo2() {

    }

    public void first(Runnable printFirst) throws InterruptedException {

        synchronized (lock) {
            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            firstFinished = true;
            lock.notifyAll();
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {

        synchronized (lock) {
            while (!firstFinished) {
                lock.wait();  // wait会自动释放锁资源，所以就算first没有抢到锁也没关系。
                // wait唤醒锁之后，线程会被重新挂起自旋
            }

            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            secondFinished = true;
            lock.notifyAll();
        }
    }

    public void third(Runnable printThird) throws InterruptedException {

        synchronized (lock) {
            while (!secondFinished) {
                lock.wait();
            }

            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
        }
    }
}