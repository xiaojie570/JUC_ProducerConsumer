import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by lenovo on 2018/11/12.
 */
public class BoundedBuffer {
    final Lock lock = new ReentrantLock();
    final Condition notFull = lock.newCondition();
    final Condition notEmpty = lock.newCondition();

    final Object[] items = new Object[5];
    int putptr, takeptr, count;

    public void put(Object x) {
        lock.lock();  // 获取锁
        try {
            while(count == items.length)
                notFull.await();
            // 将x添加到缓冲中
            items[putptr] = x;
            // 将“put统计数putptr +　１”；如果“缓冲已满”，则设置putptr为0
            if(++putptr == items.length) putptr = 0;
            // 将“缓冲”数量+1
            ++ count;
            // 唤醒take线程，因为take线程通过notEmpty.await()等待
            notEmpty.signal();
            // 打印写入的数据
            System.out.println(Thread.currentThread().getName() + " 写入数据： " + (Integer)x);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();  // 释放锁
        }
    }

    public Object take() {
        lock.lock();
        try {
            // 如果“缓冲为空”，则等待；直到“缓冲”不为空，才将x从缓冲中取出。
             while(count == 0)
                notEmpty.await();
            // 将x从缓冲中取出
            Object x = items[takeptr];
            // 将"take统计数takeptr+1"；如果"缓冲为空"，则设takeptr为0
            if(++ takeptr == items.length) takeptr = 0;
            // 将缓冲数量 -1
            --count;
            // 唤醒put线程，因为put线程通过notFull.await（）等待
            notFull.signal();

            // 打印取出的数据
            System.out.println(Thread.currentThread().getName() + " 取出数据： " + (Integer)x);
            return x;
         } catch (InterruptedException e) {
             e.printStackTrace();
         } finally {
            lock.unlock(); // 释放锁
        }
        return null;
    }
}
