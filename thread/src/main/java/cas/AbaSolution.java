package cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author dihua.wu
 * @description
 * @create 2020/11/6
 */
public class AbaSolution {
    static AtomicStampedReference atomicStampedReference = new AtomicStampedReference(10,1);

    public static void main(String[] args) {

        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "第1次版本号：" + stamp);

            atomicStampedReference.compareAndSet(10,11,
                      atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);

            System.out.println(Thread.currentThread().getName() + "第2次版本号：" + atomicStampedReference.getStamp());

            atomicStampedReference.compareAndSet(11,10,
                    atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);

            System.out.println(Thread.currentThread().getName() + "第3次版本号：" + atomicStampedReference.getStamp());
        }, "线程1").start();

        new Thread(() -> {
            try {

                int stamp = atomicStampedReference.getStamp();
                System.out.println(Thread.currentThread().getName() + "第1次版本号：" + stamp);

                TimeUnit.SECONDS.sleep(2);
                boolean flag = atomicStampedReference.compareAndSet(10, 12,
                        atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
                System.out.println(Thread.currentThread().getName() + " 修改是否成功：" + flag +
                        " 最新的版本号 = " + atomicStampedReference.getStamp() +
                        " 最新的值 = " + atomicStampedReference.getReference());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "线程2").start();
    }
}
