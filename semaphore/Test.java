/**
 * Created by lenovo on 2018/11/14.
 */
public class Test extends Thread{
    public SemaphoreDemo semaphoreDemo = new SemaphoreDemo();

    public Test(String name, SemaphoreDemo semaphoreDemo) {
        super(name);
        this.semaphoreDemo = semaphoreDemo;
    }

    @Override
    public void run() {
        semaphoreDemo.useSemaphore();
    }

    public static void main(String[] args) {
        SemaphoreDemo semaphoreDemo = new SemaphoreDemo();
        for(int i = 0; i < 10; i++) {
            (new Test("线程的名字：" + i, semaphoreDemo)).start();
        }
    }
}
