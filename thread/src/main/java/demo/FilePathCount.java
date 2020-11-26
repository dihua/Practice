package demo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author dihua.wu
 * @description
 * @create 2020/11/25
 */
public class FilePathCount {

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        String filePath = "C:\\Users\\wdhua\\Documents\\WeChat Files\\wxid_wp80e5pdv3h231\\FileStorage\\File\\1";

        FilePathCount filePathCount = new FilePathCount();
        final long start = System.currentTimeMillis();

        int count = filePathCount.count(new File(filePath));

        final long end = System.currentTimeMillis();
        System.out.println("Total count: " + count);
        System.out.println("Time taken: " + (end - start) + " ms");
    }

    public int count(File file) throws InterruptedException, ExecutionException, TimeoutException {
        int i;
        ExecutorService service = Executors.newFixedThreadPool(10);
        try {
            i = calculator(service, file);
        } finally {
            service.shutdown();
        }
        return i;
    }

    public int calculator(ExecutorService service, File file) throws InterruptedException, ExecutionException, TimeoutException {

        if (file.isFile()) {
            return 1;
        }
        int count = 0;
        File[] files = file.listFiles();
        if (files != null) {
            List<Future<Integer>> futures = new ArrayList<>();
            for (File subFile : files) {
                futures.add(service.submit(new Callable<Integer>() {
                    @Override
                    public Integer call() throws Exception {
                        System.out.println(Thread.currentThread().getName() + "===" + subFile.getPath() );
                        return calculator(service, subFile);
                    }
                }));
            }
            for (Future<Integer> future : futures) {
                count += future.get(100,TimeUnit.SECONDS);
            }
        }
        return count;
    }
}
