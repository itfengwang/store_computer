package com.store.service;

import com.store.pojo.Address;
import com.store.pojo.District;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest //表示标注当前的类是一个测试类，不会随同项目打包发送
@RunWith(SpringRunner.class) //表示启动这个单元测试类
public class DistrictServiceTests {

    @Autowired
    private IDistrictService districtService;

    @Test
    public void test01(){
        List<District> list = districtService.getByParent("86");

        for (District district : list) {
            System.out.println(district);

        }



    }



}
