/**
 * 给定三个线程ABC, 加入我们希望让3个线程按照顺序打印ABC，那么可以使用join方法，但是如果要求多线程按顺序循环打印，就不能使用join方法了
 */
public class ABC {
    private static ReentrantLock lock = new ReentrantLock();

    private static int state = 0;

    static class ThreadA extends Thread {
        @Override
        public void run() {
            lock.lock();
            if(state % 3 == 0) {
                System.out.println("线程 A 执行");
                state ++;
            }
            lock.unlock();
        }
    }

    static class ThreadB extends Thread {
        @Override
        public void run() {
            lock.lock();
            if(state % 3 == 1) {
                System.out.println("线程 B 执行");
                state ++;
            }
            lock.unlock();
        }
    }

    static class ThreadC extends Thread {
        @Override
        public void run() {
            lock.lock();
            if(state % 3 == 2) {
                System.out.println("线程 C 执行");
                state ++;
            }
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        while(true) {
            new ThreadA().start();
            new ThreadB().start();
            new ThreadC().start();
        }
    }
}
