package demo;

import java.time.LocalDateTime;

/**
 * @author dihua.wu
 * @description
 * @create 2020/11/4
 */
public class DeadLock {

    //资源1
    private static Object resource1 = new Object();
    //资源2
    private static Object resource2 = new Object();

    public static void main(String[] args) {

        new Thread(() -> {
            //获得资源1，加锁，占用CPU
            synchronized (resource1) {
                System.out.println(LocalDateTime.now() + " " + Thread.currentThread() + " get resource1");
                try {
                    //休眠，让出CPU
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(LocalDateTime.now() + " " + Thread.currentThread() + " waiting get resource2");
                //想要获得资源2，此刻资源2被线程2锁住
                synchronized (resource2) {
                    System.out.println(LocalDateTime.now() + " " + Thread.currentThread() + " get resource2");
                }
            }
        }, "线程1").start();

        new Thread(() -> {
            //获得资源2，加锁，占用CPU
            synchronized (resource2) {
                System.out.println(LocalDateTime.now() + " " + Thread.currentThread() + " get resource2");
                try {
                    //休眠，让出CPU
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(LocalDateTime.now() + " " + Thread.currentThread() + " waiting get resource1");
                //想要获得资源1，此刻资源1被线程1锁住
                synchronized (resource1) {
                    System.out.println(LocalDateTime.now() + " " + Thread.currentThread() + " get resource1");
                }
            }
        }, "线程2").start();
    }

    /**
     * 线程 1 通过 synchronized (resource1) 获得 resource1 的监视器锁，然后通过Thread.sleep(1000)；
     * 让线程 1 休眠 1s 为的是让线程 2 得到CPU执行权，然后获取到 resource2 的监视器锁。
     *
     * 线程 1 和线程 2 休眠结束了都开始企图请求获取对方的资源，
     * 然后这两个线程就会陷入互相等待的状态，这也就产生了死锁。
     * 上面的例子符合产生死锁的四个必要条件。
     *
     *
     * 互斥条件：线程(进程)对于所分配到的资源具有排它性，即一个资源只能被一个线程(进程)占用，直到被该线程(进程)释放
     * 请求与保持条件：一个线程(进程)因请求被占用资源而发生阻塞时，对已获得的资源保持不放。
     * 不剥夺条件：线程(进程)已获得的资源在末使用完之前不能被其他线程强行剥夺，只有自己使用完毕后才释放资源。
     * 循环等待条件：当发生死锁时，所等待的线程(进程)必定会形成一个环路（类似于死循环），造成永久阻塞
     */
}
