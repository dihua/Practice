package createThread;

/**
 * @author dihua.wu
 * @description
 * @create 2020/11/5
 */
public class ExtendsThread extends Thread {

    @Override
    public void run() {
        System.out.println("Extends Thread to create thread");
        System.out.println(Thread.currentThread().getName() + " run()方法正在执行...");
    }

    public static void main(String[] args) {
        ExtendsThread extendsThread = new ExtendsThread();
        extendsThread.start();
        System.out.println(Thread.currentThread().getName() + " main()方法执行结束");
    }
}
