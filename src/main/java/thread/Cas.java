package thread;

/**
 * @author dihua.wu
 * @description
 * @create 2020/7/8
 */
public class Cas {

    /**
     * 底层指令 CMPXCHG
     * 这是一种乐观策略，假设每一次操作都不会产生冲突，
     * 当且仅当冲突发生的时候再去尝试
     *
     * CAS(V,O,N)核心思想为：
     * 若当前变量实际值V与期望的旧值O相同，
     * 则表明该变量没被其他线程进行修改，
     * 因此可以安全的将新值N赋值给变量；
     *
     * 若当前变量实际值V与期望的旧值O不相同，
     * 则表明该变量已经被其他线程做了处理，
     * 此时将新值N赋给变量操作就是不安全的，
     *
     * 再进行重试
     */
}