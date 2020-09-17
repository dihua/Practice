package practise.wu.aop.annotate.model;

/**
 * @author dihua.wu
 * @description
 * @create 2020/7/21
 */

public class Order {

    private long orderId;
    private String createTime;

    public long getOrderId() {
        return orderId;
    }
    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }
    public String getCreateTime() {
        return createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    @Override
    public String toString() {
        return "Order [orderId=" + orderId + ", createTime=" + createTime + "]";
    }
}
