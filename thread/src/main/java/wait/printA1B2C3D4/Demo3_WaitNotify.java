package wait.printA1B2C3D4;

/**
 * @author dihua.wu
 * @description
 * @create 2020/12/9
 */
public class Demo3_WaitNotify {

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
                while (!flag) {//一开始flag=false，!=flag=true，先释放锁，给thread2
                    try {
                        lockObject.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (char c : number) {
                    System.out.println(c);

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
            synchronized (lockObject) { //由于thread1先释放锁，thread2会拿到锁
                flag = true;//修改flag，让thread1拿到锁，可以输出
                System.out.println("flag = true;");
                for (char c : letter) {
                    System.out.println(c);

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
