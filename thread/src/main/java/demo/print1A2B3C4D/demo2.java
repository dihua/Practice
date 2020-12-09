package demo.print1A2B3C4D;

import java.util.concurrent.CountDownLatch;

/**
 * @author dihua.wu
 * @description
 * @create 2020/12/9
 */
public class demo2 {

    /**
     * 输出：1A2B3C4D5E6F7G
     * 输出：A1B2C3D4E5F6G7
     * 如何一定让A在1前
     *
     * 标志位
     *
     * @param args
     */

    private static volatile boolean flag = false;

    public static void main(String[] args) {

        final Object lockObject = new Object();

        char[] number = "1234567".toCharArray();
        char[] letter = "ABCDEFG".toCharArray();

        new Thread(() -> {

            synchronized (lockObject) {
                while (!flag) {
                    try {
                        lockObject.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (char c : number) {
                    System.out.print(c);

                    try {
                        lockObject.notify();
                        lockObject.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lockObject.notify();
//                lockObject.notifyAll();
            }
        }, "thread1").start();

        new Thread(() -> {
            synchronized (lockObject) {
                for (char c : letter) {
                    System.out.print(c);
                    flag = true;
                    try {
                        lockObject.notify();
                        lockObject.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lockObject.notify();
//                lockObject.notifyAll();
            }
        }, "thread2").start();
    }
}
