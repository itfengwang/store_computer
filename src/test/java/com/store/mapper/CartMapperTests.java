package com.store.mapper;

import com.store.pojo.Cart;
import com.store.pojo.District;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.Date;
import java.util.List;

@SpringBootTest //表示标注当前的类是一个测试类，不会随同项目打包发送
@RunWith(SpringRunner.class) //表示启动这个单元测试类
public class CartMapperTests {


    @Autowired
    private CartMapper cartMapper;


    @Test
    public void test01(){

        Cart cart = new Cart();
        cart.setUid(6);
        cart.setPid(10000001);
        cart.setNum(2);
        cart.setPrice(2000L);

        cartMapper.insert(cart);


    }

    @Test
    public void test02(){

      cartMapper.updateNumByCid(1,4,"冯临",new Date());


    }

    @Test
    public void test03(){

        Cart cart = cartMapper.findByUidAndPid(6, 10000001);
        System.out.println(cart);



    }

    @Test
    public void test04(){

        System.out.println(cartMapper.findVoByUid(6));


    }



    @Test
    public void test05(){


        Integer[] cids = {1,2,3,4,5,6};
        System.out.println(cartMapper.findVoByCid(cids));



    }


}
