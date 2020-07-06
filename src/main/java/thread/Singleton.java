/**
 * @author dihua.wu
 * @description
 * @create 2020/7/3
 */
public class Singleton {

    /**
     * 指令重排序问题：
     */

    private static Singleton instance;

    private Singleton () {

    }

    public static Singleton getInstance () {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                    //分为3步
                    //1.分配内存空间
                    //2.初始化对象
                    //3.将内存空间的地址赋值给instance
                    //但是这三步经过重排之后：
                    //①分配内存空间
                    //②将内存空间的地址赋值给instance
                    //③初始化对象Singleton
                    //会导致什么结果呢？
                    //线程A先执行getInstance()方法，当执行完指令②时恰好发生了线程切换，切换到了线程B上；
                    // 如果此时线程B也执行getInstance()方法，那么线程B在执行第一个判断时会发现instance!=null，
                    // 所以直接返回instance，而此时的instance是没有初始化过的，
                    // 如果我们这个时候访问instance的成员变量就可能触发空指针异常。
                    //../resources/singleton.png
                }
            }
        }
        return instance;
    }
}
