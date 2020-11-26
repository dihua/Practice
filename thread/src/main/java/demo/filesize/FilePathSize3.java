package demo.filesize;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author dihua.wu
 * @description Executors.newFixedThreadPool和callable
 * @create 2020/11/26
 * Total Size:
 * Time taken:
 */
public  class FilePathSize3 {

    public static void main(final String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        final long start = System.currentTimeMillis();

        final File file = new File("C:\\Users\\wdhua\\Documents\\WeChat Files\\wxid_wp80e5pdv3h231\\FileStorage\\File");
        final long total = new FilePathSize3().getTotalSizeOfFile(file);

        final long end = System.currentTimeMillis();

        System.out.println("Total Size: " + total);
        System.out.println("Time taken: " + (end - start) + " ms");
    }

    private long getTotalSizeOfFile(File file) throws InterruptedException, ExecutionException, TimeoutException {
        final ExecutorService service = Executors.newFixedThreadPool(10);
        //todo 当线程数较少时，将不会执行？？？为什么
        try {
            return getTotalSizeOfFilesInDir(service, file);
        } finally {
            service.shutdown();
        }
    }

    private long getTotalSizeOfFilesInDir(final ExecutorService service, final File file) throws InterruptedException, ExecutionException, TimeoutException {
        if (file.isFile()) {
            return file.length();
        }
        final File[] children = file.listFiles();
        long total = 0;
        if (children != null) {
            final List<Future<Long>> futures = new ArrayList<>();
            for (final File child : children) {
                futures.add(service.submit(new Callable<Long>() {
                    @Override
                    public Long call() throws Exception {
                        System.out.println(Thread.currentThread().getName() + "===" + child.getPath());
                        return getTotalSizeOfFilesInDir(service, child);
                    }
                }));
            }
            for (final Future<Long> future : futures) {
                total += future.get(100, TimeUnit.SECONDS);
            }
        }
        return total;
    }

}
