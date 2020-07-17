package method;

import java.lang.reflect.Array;
import java.util.Arrays;


/**
 * @author dihua.wu
 * @description System.arraycopy  Arrays.copyOf
 * @create 2020/7/17
 */
public class ArrayCopy {

    public static void main(String[] args) {
        String[] srcArray = new String[8];
        srcArray[0] = "1";
        srcArray[1] = "2";
        srcArray[2] = "3";
        System.arraycopy(srcArray, 1, srcArray, 2,2);
        System.out.println(Arrays.toString(srcArray));

    }

//    public static native void arraycopy(Object src,  int  srcPos,
//                                        Object dest, int destPos,
//                                        int length);


    //Arrays.copyOf 内部也是调用System.arraycopy，区别在于Arrays.copyOf是全数据的全部元素拷贝
    public static <T> T[] copyOf(T[] original, int newLength) {
        return (T[]) copyOf(original, newLength, original.getClass());
    }

    public static <T,U> T[] copyOf(U[] original, int newLength, Class<? extends T[]> newType) {
        @SuppressWarnings("unchecked")
        T[] copy = ((Object)newType == (Object)Object[].class)
                ? (T[]) new Object[newLength]
                : (T[]) Array.newInstance(newType.getComponentType(), newLength);
        System.arraycopy(original, 0, copy, 0,
                Math.min(original.length, newLength));
        return copy;
    }
}
