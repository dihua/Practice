package demo;

/**
 * @author dihua.wu
 * @description
 * @create 2020/11/25
 */
public class MutiThreadPrintNum {

    public static void main(String[] args) {
        Object lock = new Object();
        Thread thread1 = new Thread(new MyThread(lock, true, 1), "Thread-1");
        Thread thread2 = new Thread(new MyThread(lock, false, 2), "Thread-2");
        thread2.start();
        thread1.start();
    }
    static class MyThread implements Runnable {
        private volatile int i;
        private Object lock;
        private boolean runNow;
        public MyThread(Object lock, boolean runNow, int i) {
            this.lock = lock;
            this.runNow = runNow;
            this.i = i;
        }
        @Override
        public void run() {
            synchronized (lock) {
                while (i <= 100) {
                    if (runNow) {
                        //thread1 因为是true  第一次进来，会改为false
                        runNow = false;
                    } else {
                        //thread2 因为是false 第一次进来，会等待
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(Thread.currentThread() + " " + i);
                    i += 2;
                    //通知其他线程运行
                    lock.notify();
                }
            }
        }
    }


}
