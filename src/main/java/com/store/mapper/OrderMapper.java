package com.store.mapper;

import com.store.pojo.Order;
import com.store.pojo.OrderItem;

/*订单的持久层接口*/
public interface OrderMapper {

    /**
     * 插入订单的数据
     * @param order
     * @return
     */
    Integer insertOrder(Order order);


    /**
     * 插入订单项的数据
     * @param orderItem
     * @return
     */
    Integer insertOrderItem(OrderItem orderItem);




}
