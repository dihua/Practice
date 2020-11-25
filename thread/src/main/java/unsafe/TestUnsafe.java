package unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author dihua.wu
 * @description
 * @create 2020/11/25
 */
public class TestUnsafe {

    private static TestUnsafe t = new TestUnsafe();
    int i = 0;

    public static void main(String[] args) throws Exception {

        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe)unsafeField.get(null);

        Field f = TestUnsafe.class.getDeclaredField("i");

        long offset = unsafe.objectFieldOffset(f);
        System.out.println(offset);

        boolean success = unsafe.compareAndSwapInt(t, offset, 0, 1);
        System.out.println(success);
        System.out.println(t.i);
    }
}
