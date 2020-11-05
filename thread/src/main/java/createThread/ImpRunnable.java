package createThread;

/**
 * @author dihua.wu
 * @description
 * @create 2020/11/5
 */
public class ImpRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println("implements Runnable to create thread");
        System.out.println(Thread.currentThread().getName() + " run()方法正在执行...");
    }

    public static void main(String[] args) {
        ImpRunnable impRunnable = new ImpRunnable();
        Thread thread = new Thread(impRunnable);
        thread.start();
        System.out.println(Thread.currentThread().getName() + " main()方法执行结束");

        //错误
//        ImpRunnable impRunnable = new ImpRunnable();
//        impRunnable.start();
    }
}
