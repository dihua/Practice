package demo;

/**
 * @author dihua.wu
 * @description
 * @create 2020/11/25
 */
public class MutiThreadPrintNum2 {

    private volatile int i=1;
    private volatile int flag;
    private Thread thread1, thread2, thread3;

    public static void main(String[] args) {
        MutiThreadPrintNum2 mutiThreadPrintNum2 = new MutiThreadPrintNum2();
        mutiThreadPrintNum2.run();
    }

    public void run() {
        thread1 = new Thread(new MyThread1());
        thread2 = new Thread(new MyThread2());
        thread3 = new Thread(new MyThread3());
        thread1.start();
        thread2.start();
        thread3.start();
    }

    class MyThread1 implements Runnable {

        @Override
        public void run() {
            while (i <= 100) {
                if (flag == 0) {
                    System.out.println(Thread.currentThread() + " " + i);
                    i++;
                    flag=1;
                }
            }
        }
    }

    class MyThread2 implements Runnable {

        @Override
        public void run() {
            while (i <= 100) {
                if (flag == 1) {
                    System.out.println(Thread.currentThread() + " " + i);
                    i++;
                    flag=2;
                }
            }
        }
    }

    class MyThread3 implements Runnable {

        @Override
        public void run() {
            while (i <= 100) {
                if (flag == 2) {
                    System.out.println(Thread.currentThread() + " " + i);
                    i++;
                    flag=0;
                }
            }
        }
    }
}
