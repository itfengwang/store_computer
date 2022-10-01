package com.store.service;

import com.store.pojo.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest //表示标注当前的类是一个测试类，不会随同项目打包发送
@RunWith(SpringRunner.class) //表示启动这个单元测试类
public class CartServiceTests {

    @Autowired
    private ICartService cartService;

    @Test
    public void addTo(){

        cartService.addToCart(6,10000001,6,"朴彩英");

    }




}
