package demo.filesize;

import java.io.File;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author dihua.wu
 * @description CountDownLatch和AtomicLong
 * @create 2020/11/26
 * Total Size:
 * Time taken:
 */
public  class FilePathSize4 {

    private ExecutorService service;
    final private AtomicLong pendingFileVisits = new AtomicLong();
    final private AtomicLong totalSize = new AtomicLong();
    final private CountDownLatch latch = new CountDownLatch(1);

    public static void main(final String[] args) throws Exception {
        final long start = System.currentTimeMillis();

        final File file = new File("C:\\Users\\wdhua\\Documents\\WeChat Files\\wxid_wp80e5pdv3h231\\FileStorage\\File");
        final long total = new FilePathSize4().getTotalSizeOfFile(file);

        final long end = System.currentTimeMillis();

        System.out.println("Total Size: " + total);
        System.out.println("Time taken: " + (end - start) + " ms");
    }

    private long getTotalSizeOfFile(File file) throws Exception {
        service = Executors.newFixedThreadPool(10);
        pendingFileVisits.incrementAndGet();
        try {
            updateTotalSizeOfFilesInDir(file);
            latch.await(100, TimeUnit.SECONDS);
            return totalSize.longValue();

        } finally {
            service.shutdown();
        }
    }

    private void updateTotalSizeOfFilesInDir(final File file) {
        long fileSize = 0;
        if (file.isFile()) {
            fileSize = file.length();
        } else {
            final File[] children = file.listFiles();
            if (children != null) {
                for (final File child : children) {
                    if (child.isFile()) {
                        fileSize += child.length();
                    } else {
                        pendingFileVisits.incrementAndGet();
                        service.execute(new Runnable() {
                            @Override
                            public void run() {
                                updateTotalSizeOfFilesInDir(child);
                            }
                        });
                    }
                }
            }
        }
        totalSize.addAndGet(fileSize);
        if (pendingFileVisits.decrementAndGet() == 0) {
            latch.countDown();
        }
    }

}
