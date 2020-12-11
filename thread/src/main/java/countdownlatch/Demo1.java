package countdownlatch;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * @author dihua.wu
 * @description
 * @create 2020/12/11
 */
public class Demo1 {

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(3);

        Worker worker1 = new Worker("worker1", countDownLatch);
        Worker worker2 = new Worker("worker2", countDownLatch);
        Worker worker3 = new Worker("worker3", countDownLatch);
        Boss boss = new Boss(countDownLatch);
        boss.start();
        worker1.start();
        worker2.start();
        worker3.start();
    }

    static class Worker extends Thread {
        public String name;
        public CountDownLatch countDownLatch;

        public Worker(String name, CountDownLatch countDownLatch) {
            this.name = name;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            System.out.println(new Date() + "-" + name + "开始工作");
            try {
                Thread.sleep(1000);
                System.out.println(new Date() + "-" + name + "结束工作");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
        }
    }

    static class Boss extends Thread {
        public CountDownLatch countDownLatch;

        public Boss(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            System.out.println(new Date() + "-BOSS等待worker工作");
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(new Date() + "-BOSS检查工作");
        }

    }
}
