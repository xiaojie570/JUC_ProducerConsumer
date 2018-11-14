import java.util.concurrent.Semaphore;

/**
 * Created by lenovo on 2018/11/14.
 */
public class SemaphoreDemo {
    final Semaphore semaphore = new Semaphore(5);

    public void useSemaphore() {
        try {
            // 从信号量中获取一个允许的机会
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName() + " 开始执行的时间： " + System.currentTimeMillis());
            Thread.sleep(100);
            System.out.println(Thread.currentThread().getName() + " 结束执行的时间： " + System.currentTimeMillis());
            // 释放允许，将占有的信号量归还
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
