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
public  class FilePathSize2 {

    class SubDirectoriesAndSize {
        final public long size;
        final public List<File> subDirectories;

        public SubDirectoriesAndSize(final long totalSize, final List<File> theSubDirs) {
            size = totalSize;
            subDirectories = Collections.unmodifiableList(theSubDirs);
        }
    }



    private SubDirectoriesAndSize  getTotalAndSubDirs(final File file) {
        long total = 0;
        final List<File> subDirectories = new ArrayList<File>();
        if (file.isDirectory()) {
            final File[] children = file.listFiles();
            if (children != null) {
                for (final File child : children) {
                    if (child.isFile()) {
                        total += child.length();
                    } else {
                        subDirectories.add(file);
                    }
                }
            }
        }
        return new SubDirectoriesAndSize(total, subDirectories);
    }


    private long getTotalSizeOfFilesInDir(final File file) throws InterruptedException, ExecutionException, TimeoutException {
        final ExecutorService service = Executors.newFixedThreadPool(100);
        try {
            long total = 0;
            final List<File> directories = new ArrayList<File>();
            directories.add(file);
            while (!directories.isEmpty()) {
                final List<Future<SubDirectoriesAndSize>> partialResults = new ArrayList<>();
                for (final File directory : directories) {
                    partialResults.add(service.submit(new Callable<SubDirectoriesAndSize>() {
                        @Override
                        public SubDirectoriesAndSize call() throws Exception {
                            return getTotalAndSubDirs(directory);
                        }
                    }));
                }
                directories.clear();
                for (final Future<SubDirectoriesAndSize> partialResultFuture : partialResults) {
                    final SubDirectoriesAndSize subDirectoriesAndSize = partialResultFuture.get(100, TimeUnit.SECONDS);
                    directories.addAll(subDirectoriesAndSize.subDirectories);
                    total += subDirectoriesAndSize.size;
                }
            }
            return total;
        } finally {
            service.shutdown();
        }

    }

    public static void main(final String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        final long start = System.nanoTime();

        final File file = new File("C:\\Users\\wdhua\\Documents\\WeChat Files\\wxid_wp80e5pdv3h231\\FileStorage\\File");
        final long total = new FilePathSize2().getTotalSizeOfFilesInDir(file);

        final long end = System.nanoTime();

        System.out.println("Total Size: " + total);
        System.out.println("Time taken: " + (end - start) / 1.0e6);
    }

}
