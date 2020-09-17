package practise.wu.aop.proxy;

/**
 * @author dihua.wu
 * @description 房东类
 * @create 2020/7/22
 */
public class Host implements Rent {
    @Override
    public void rent() {
        System.out.println("房东出租");
    }
}
