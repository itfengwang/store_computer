package com.store.service;

import com.store.pojo.Address;
import com.store.pojo.User;
import com.store.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest //表示标注当前的类是一个测试类，不会随同项目打包发送
@RunWith(SpringRunner.class) //表示启动这个单元测试类
public class AddressServiceTests {

    @Autowired
    private IAddressService addressService;

    @Test
    public void insertTest(){

        Address address = new Address();
        address.setPhone("18222344");
        address.setName("亲爱的小妹妹");

       addressService.addNewAddress(5,"管理员",address);

    }


    @Test
    public void update(){

        addressService.setDefaultAddresss(5,6,"冯奕晨");


    }

    @Test
    public void delete(){

        addressService.delete(3,6,"冯临");


    }




}
