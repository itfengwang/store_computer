package com.store.mapper;

import com.store.pojo.Address;
import com.store.pojo.Order;
import com.store.pojo.OrderItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@SpringBootTest //表示标注当前的类是一个测试类，不会随同项目打包发送
@RunWith(SpringRunner.class) //表示启动这个单元测试类
public class OrderMapperTests {


    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void test01(){

        Order order = new Order();
        order.setUid(6);
        order.setRecvName("李家园园");
        order.setRecvPhone("1122334455");
        orderMapper.insertOrder(order);

    }

    @Test
    public void test02(){

        OrderItem orderItem = new OrderItem();

        orderItem.setOid(1);
        orderItem.setPid(10000003);
        orderItem.setTitle("呵呵哈哈哈或");
        orderMapper.insertOrderItem(orderItem);


    }




}
