package demo.filesize;

import java.io.File;
import java.util.Date;

/**
 * @author dihua.wu
 * @description 单线程
 * @create 2020/11/26
 * Total Size: 304895172
 * Time taken: 72.2819
 */
public class FilePathSize {

    private long getTotalSizeOfFilesInDir(final File file) {
        if (file.isFile()) {
            return file.length();
        }
        final File[] children = file.listFiles();
        long total = 0;
        if (children != null) {
            for (final File child : children) {
                total += getTotalSizeOfFilesInDir(child);
            }
        }
        return total;
    }

    public static void main(final String[] args) {
        final long start = System.currentTimeMillis();
        System.out.println(new Date());

        final File file = new File("C:\\Users\\wdhua\\Documents\\WeChat Files\\wxid_wp80e5pdv3h231\\FileStorage\\File");
        final long total = new FilePathSize().getTotalSizeOfFilesInDir(file);

        final long end = System.currentTimeMillis();
        System.out.println(new Date());

        System.out.println("Total Size: " + total);
        System.out.println("Time taken: " + (end - start) / 1000);
    }

}
