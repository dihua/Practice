package createThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author dihua.wu
 * @description
 * @create 2020/11/5
 */
public class UseExecutors {

    public static void main(String[] args) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(new ImpRunnable());
        System.out.println("线程任务开始执行");
        service.shutdown();
    }
}
