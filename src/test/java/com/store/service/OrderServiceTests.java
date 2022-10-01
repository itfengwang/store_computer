package com.store.service;

import com.store.pojo.Address;
import com.store.pojo.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest //表示标注当前的类是一个测试类，不会随同项目打包发送
@RunWith(SpringRunner.class) //表示启动这个单元测试类
public class OrderServiceTests {

    @Autowired
    private IOrderService orderService;


    @Test
    public void test01(){

        Integer[] cids = {2,3};
        Order order = orderService.create(11,6,"朴彩英",cids);

        System.out.println(order);

    }




}
