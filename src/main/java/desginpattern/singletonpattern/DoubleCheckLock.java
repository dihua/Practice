package desginpattern.singletonpattern;

/**
 * @author dihua.wu
 * @description
 * @create 2020/12/11
 */
public class DoubleCheckLock {

    //静态 对象 volatile
    private static volatile DoubleCheckLock instance;

    private DoubleCheckLock() {

    }

    //静态工厂方法
    public static DoubleCheckLock getInstance() {
        if (instance == null) {
            synchronized (DoubleCheckLock.class) {
                if (instance == null) {
                    instance = new DoubleCheckLock();
                    //1. memory = allocate() //分配内存
                    //2. ctorInstanc(memory) //初始化对象 new DoubleCheckLock()
                    //3. instance = memory //设置instance指向刚分配的地址 instance = new DoubleCheckLock()
                    //当2，3顺序颠倒时，instance = 刚分配的内存（instance 指向刚分配的内存地址，已经 != null 了）
                    //这样，其他线程在判断19行的时候，是false，进入32行
                    //这样，这个getInstance() 返回的是一个尚未初始化的对象
                }
            }
        }
        return instance;
    }
}
