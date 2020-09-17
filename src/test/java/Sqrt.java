/**
 * @author dihua.wu
 * @description
 * @create 2020/9/4
 */
public class Sqrt {

    /**
     * 实现一个函数, 完成 开根号 的操作, 方法签名如下.
     * <p>
     * double sqrt(int v, double t)
     * <p>
     * 要求:
     * <p>
     * 不能调用系统库函数, 诸如 Math.sqrt(v) 之类的; 假设计算出的结果为 r, 要求满足如下条件, , 其中 是真实的值, t 为给定的一个误差范围, 例如0.1等, 即你计算出的值要在给定的误差范围内. 实现语言不限,
     * 你条件可以比上述更加苛刻, 但不能宽松, 举例而言, 我调用你的接口 sqrt(9, 0.21) 返回值属于 [2.79, 3.21] 这个区间的任意一个都满足条件.
     * </p>
     *
     * @param t 误差范围
     * @param v 参数
     */
    private static double sqrt1(int v, double t) {

        /**
         * 10，0.1
         * max min
         * 5^2=25>10  0~5
         * 2.5^2=6.25<10 2.5~5
         * r-t ~ r+t    max-min =2t
         */
        return 0;
    }
}
