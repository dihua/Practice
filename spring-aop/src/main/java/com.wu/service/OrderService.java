package com.wu.service;

import com.wu.model.Order;
import com.wu.utils.CommUtils;
import org.springframework.stereotype.Service;

/**
 * @author dihua.wu
 * @description
 * @create 2020/7/21
 */
@Service
public class OrderService {

    Order getOrder(Long orderId){
        Order order =new Order();
        order.setOrderId(orderId);
        order.setCreateTime(CommUtils.getTimeStr());
        return order;
    }
}
