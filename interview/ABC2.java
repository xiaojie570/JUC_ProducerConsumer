/**
 * Created by lenovo on 2018/11/21.
 */
public class ABC2 {
    private static ReentrantLock lock = new ReentrantLock();

    private static int state = 0;

    static class ThreadTest extends Thread {
        @Override
        public void run() {
            while(true) {
                if(state % 3 == 0) {
                    lock.lock();
                    System.out.println("A");
                    state ++;
                    lock.unlock();
                } else if(state % 3  == 1) {
                    lock.lock();
                    state ++;
                    System.out.println("B");
                    lock.unlock();
                } else if(state % 3 == 2){
                    lock.lock();
                    state ++;
                    System.out.println("C");
                    lock.unlock();
                }

            }
        }
    }

    public static void main(String[] args) {
        new ThreadTest().start();
    }
}
