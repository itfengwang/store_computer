package com.store.mapper;

import com.store.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest //表示标注当前的类是一个测试类，不会随同项目打包发送
@RunWith(SpringRunner.class) //表示启动这个单元测试类
public class UserMapperTests {

    //报错原因：idea有检测的功能，接口是不能直接创建Bean的，
    @Autowired
    private UserMapper userMapper;

    /*单元测试类：
    * 1 必须被@Test注解修饰
    * 2 返回值类型必须使 void
    * 3 方法的参数列表不能指定任何类型
    * 4 方法的访问修饰符必须是public
    * */
    @Test
    public void insert(){

        User user = new User();
        user.setUsername("wushixun");
        user.setPassword("123456");
        Integer row = userMapper.insertUser(user);
        System.out.println(row);


    }

    @Test
    public void select(){
        User user = userMapper.findByUsername("wushixun");
        System.out.println(user);

    }

    @Test
    public void Up(){

        userMapper.updatePasswordByUid(4,"654321","管理员",new Date());

    }
    @Test
    public void findById(){
        System.out.println(userMapper.findByUid(4));


    }
    @Test
    public void testUpInfo(){
        User user = new User();
        user.setUid(6);
        user.setPhone("18252372845");
        user.setEmail("16111@qq.com");
        user.setGender(0);

        userMapper.updateInfoByUid(user);

    }
    @Test
    public void avTest(){

        userMapper.updateAvatarByUid(5,"/jpg/pcy.jpg","管理员",new Date());

    }

}
