/**
 * Created by lenovo on 2018/11/12.
 */
public class TestCondition {
    private static BoundedBuffer bb = new BoundedBuffer();

    public static void main(String[] args) {
        // 启动10个“写线程”，向BoundedBuffer中不断写入数据
        // 启动10个“读线程”，向BoundedBuffer中不断读数据
        for(int i = 0; i < 10; i++) {
            new PutThread("生产者" + i, i).start();
            new TakeThread("消费者" + i).start();
        }
    }

    static class PutThread extends Thread{
        private int num;

        public PutThread(String name,int num) {
            super(name);
            this.num = num;
        }
        public void run() {
            try {
                // 线程休眠1ms
                Thread.sleep(1);
                // 向BoundedBuffer中写入数据
                bb.put(num);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    static class TakeThread extends Thread {
        public TakeThread(String name) {
            super(name);
        }

        public void run() {
            try {
                // 线程休眠
                Thread.sleep(10);
                // 从向BoundedBuffer中取出数据
                Integer num = (Integer) bb.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
