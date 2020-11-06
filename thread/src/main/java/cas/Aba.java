package cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author dihua.wu
 * @description
 * @create 2020/11/6
 */
public class Aba {
    private static AtomicInteger index = new AtomicInteger(10);

    public static void main(String[] args) {

        new Thread(() -> {
            index.compareAndSet(10,11);
            index.compareAndSet(11,10);
            System.out.println(Thread.currentThread().getName() + " 10->11->10");
        }, "线程1").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                boolean flag = index.compareAndSet(10, 12);
                System.out.println(Thread.currentThread().getName() + " 修改index=10是否成功：" + flag +
                        " 最新的index = " + index);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "线程2").start();
    }
}
