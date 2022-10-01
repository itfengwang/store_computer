package com.store.mapper;

import com.store.pojo.District;
import com.store.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@SpringBootTest //表示标注当前的类是一个测试类，不会随同项目打包发送
@RunWith(SpringRunner.class) //表示启动这个单元测试类
public class DisMapperTests {

    //报错原因：idea有检测的功能，接口是不能直接创建Bean的，
    @Autowired
    private DistrictMapper districtMapper;

    @Test
    public void test(){

        List<District> districtList = districtMapper.findByParent("210100");
        for (District district : districtList) {

            System.out.println(district);
        }
    }

    @Test
    public void test01(){

        String nameByCode = districtMapper.findNameByCode("610000");

        System.out.println(nameByCode);


    }




}
