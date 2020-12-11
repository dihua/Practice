package wait;

/**
 * @author dihua.wu
 * @description
 * @create 2020/12/10
 */
public class WaitTest1 {


    public static void main(String[] args) {
        WaitTest1 waitTest1 = new WaitTest1();
        new Thread(new Runnable() {
            @Override
            public void run() {
                waitTest1.testWait();
            }
        }).start();
    }

    private void testWait() {
        System.out.println("start");
        try {
            wait(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end");
    }

    /**
     *
     * start
     * Exception in thread "Thread-0" java.lang.IllegalMonitorStateException
     * 	at java.lang.Object.wait(Native Method)
     * 	at wait.WaitTest.testWait(WaitTest.java:24)
     * 	at wait.WaitTest.access$000(WaitTest.java:8)
     * 	at wait.WaitTest$1.run(WaitTest.java:16)
     */


    /**
     * 原因：
     * IllegalMonitorStateException异常又是什么？我们可以看一下JDK中对IllegalMonitorStateException的描述：
     *
     * Thrown to indicate that a thread has attempted to wait on an object's monitor
     * or to notify other threads waiting on an object's monitor
     * without owning the specified monitor.
     * 线程试图等待对象的监视器
     * 或者试图通知其他正在等待对象监视器的线程，
     * 但本身没有对应的监视器的所有权。
     *
     * wait方法是一个本地方法，其底层是通过一个叫做监视器锁的对象来完成的。
     * 所以上面之所以会抛出异常，是因为在调用wait方式时没有获取到monitor对象的所有权，
     *
     * wait方法的使用必须在同步的范围内
     */
}
