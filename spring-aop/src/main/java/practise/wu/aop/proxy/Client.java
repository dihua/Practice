package practise.wu.aop.proxy;

/**
 * @author dihua.wu
 * @description
 * @create 2020/7/22
 */
public class Client {


    /**
     * 未使用代理模式
     * @param args
     */
    public static void main(String[] args) {
        Host host = new Host();
        host.rent();
    }


}
