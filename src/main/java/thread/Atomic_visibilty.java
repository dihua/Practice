package thread;

/**
 * @author dihua.wu
 * @description
 * @create 2020/6/23
 */
public class Atomic_visibilty {
    /**
     * 可见性问题：
     * 当多个线程从主内存中获取a=0,并且各自处理a++
     * 由于线程之间无法感知到其他线程内部对a的处理
     * 所以在同一时刻多个线程对a++,a=1，写入主内存，
     * 内存得到的结果a=1,不是a实际运算的结果累加
     *
     * 原子性问题：
     * a++ 包括3步操作
     * a = 0；
     * 0 + 1 = 1；
     * a = 1；
     * 由于线程之间存在切换
     * 当线程1执行到第二步，线程2进来操作，读取a，此时a=0,操作完a=1不是a=2
     *
     * 问题在于没有保证a++操作的原子性。
     * 如果保证a++的原子性，线程1在执行完三个操作之前，线程2不能执行a++，
     * 那么就可以保证在线程2执行a++时，读取到a=1，从而得到正确的结果。
     *
     */

    public int a = 0;

    public void increase() {
        a++;
    }

    public static void main(String[] args) {
        final Atomic_visibilty atomicvisibilty = new Atomic_visibilty();
        for (int i = 0; i < 10; i++) {
            new Thread() {
                public void run() {
                    for (int j = 0; j < 1000; j++)
                        atomicvisibilty.increase();
                };
            }.start();
        }

        while (Thread.activeCount() > 1) {
            // 保证前面的线程都执行完
            Thread.yield();
        }
        System.out.println(atomicvisibilty.a);
    }

}
//<=10000
//10000
//9489
//9547