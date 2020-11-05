package createThread;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author dihua.wu
 * @description
 * @create 2020/11/5
 */
public class ImpCallable implements Callable<String> {

    /**
     * 注意：
     *     1.call() 是由返回值的
     *     2.Callable 是泛型
     *     3.call() 方法是可以抛出异常的，一般的run() 方法是不行的d
     * @return
     * @throws Exception
     */
    @Override
    public String call() throws Exception {
        System.out.println("implements Callable to create thread");
        System.out.println(Thread.currentThread().getName() + " run()方法正在执行...");
        return "Callable has return-------------------- ";
    }

    public static void main(String[] args) {
        //使用FutureTask(Callable<V> callable)
        FutureTask<String> futureTask = new FutureTask<String>(new ImpCallable());
        //使用 Thread(Runnable target)
        Thread thread = new Thread(futureTask);
        thread.start();

        try {
            Thread.sleep(1000);
            //注：Callalbe接口支持返回执行结果，需要调用FutureTask.get()得到，
            // 此方法会阻塞主进程的继续往下执行，如果不调用不会阻塞。
            System.out.println("返回结果futureTask.get()= " + futureTask.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + " main()方法执行结束");
    }

    /**
     * 创建实现Callable接口的类ImpCallable
     * 以mImpCallable为参数 创建FutureTask对象
     * 将FutureTask作为参数 创建Thread对象
     * 调用线程对象的start()方法
     */
}
