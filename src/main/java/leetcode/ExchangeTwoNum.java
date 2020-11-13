package leetcode;

import java.util.concurrent.BlockingDeque;

/**
 * @author dihua.wu
 * @description
 * @create 2020/11/13
 */
public class ExchangeTwoNum {

    /**
     * 两个数字，不引入第三个变量，如何替换
     * 如 a = 1, b = 2
     * 结果 a = 2, b = 1
     */
    public static void main(String[] args) {

        int a = 1;
        int b = 2;

        a = a + b;
        b = a - b;
        a = a - b;
        System.out.println("a=" + a + ",b=" + b);

        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println("a=" + a + ",b=" + b);

    }

}
