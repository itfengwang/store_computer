package com.store.mapper;

import com.store.pojo.Address;
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
public class AddressMapperTests {

    //报错原因：idea有检测的功能，接口是不能直接创建Bean的，
    @Autowired
    private AddressMapper addressMapper ;

    /*单元测试类：
    * 1 必须被@Test注解修饰
    * 2 返回值类型必须使 void
    * 3 方法的参数列表不能指定任何类型
    * 4 方法的访问修饰符必须是public
    * */

    @Test
    public void test01(){

        Address address = new Address();
        address.setUid(5);
        address.setPhone("1234567");
        address.setName("对象");

        addressMapper.insert(address);
    }

    @Test
    public void test02(){
        Integer count = addressMapper.countByUid(5);
        System.out.println(count);
    }

    @Test
    public void findByUid(){
        List<Address> addresses = addressMapper.findByUid(6);
        for (Address address : addresses) {
            System.out.println(address);
        }

    }

    @Test
    public void fin1(){


        System.out.println(addressMapper.findByAid(6));



    }
    @Test
    public void fin3(){

        //根据当前用户把他的用户全部设置为 非默认
        addressMapper.updateNonDefault(6);



    }
    @Test
    public void fin2(){

        //设置指定的地址为默认地址
        addressMapper.updateDefaultByAid(6,"冯临",new Date());

    }

    @Test
    public void delete(){

        addressMapper.deleteByAid(5);

    }

    @Test
    public void mod(){

        System.out.println(addressMapper.findLastModified(6));

    }








}
