package demo.print1A2B3C4D;

/**
 * @author dihua.wu
 * @description
 * @create 2020/12/9
 */
public class demo0 {

    /**
     * 输出：1A2B3C4D5E6F7G
     * 输出：A1B2C3D4E5F6G7
     * 如何一定让A在1前
     * @param args
     */

    public static void main(String[] args) {

        final Object lockObject = new Object();

        char[] number = "1234567".toCharArray();
        char[] letter = "ABCDEFG".toCharArray();

        new Thread(() -> {
            synchronized (lockObject) {
                for (char c : number) {
                    System.out.print(c);

                    try {
                        lockObject.notify();
                        lockObject.wait();
                        //wait 会释放锁，该线程会进入等待队列
                        //因此不能先wait后notify，notify是持有锁的前提下通知其他线程
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
