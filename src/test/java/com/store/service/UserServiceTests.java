package com.store.service;

import com.store.mapper.UserMapper;
import com.store.pojo.User;
import com.store.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest //表示标注当前的类是一个测试类，不会随同项目打包发送
@RunWith(SpringRunner.class) //表示启动这个单元测试类
public class UserServiceTests {

    @Autowired
    private IUserService userService;

    @Test
     public void reg(){
        try {
            User user = new User();
            user.setUsername("zhuyixin2");
            user.setPassword("123456");
            userService.reg(user);
            System.out.println("注册成功一个");

        } catch (ServiceException e) {

            //获取类的对象，再获取类的名称
            System.out.println(e.getClass().getSimpleName());
            //获取异常的具体描述信息
            System.out.println(e.getMessage());

        }

    }

    @Test
    public void login(){

        User user = userService.login("Tom","123456");
        System.out.println(user);

    }


    @Test
    public void changes(){

        userService.changePassword(6,
                "管理员",
                "123456",
                "654321");

    }


    @Test
    public void getByUid(){
        User user = userService.getByUid(6);
        System.out.println(user);

    }

    @Test
    public void changeInfo(){

        User user = new User();
        user.setPhone("1122334455");
        user.setEmail("122@ww.com");
        user.setGender(1);

        userService.changeInFo(6,"管理员",user);
    }


    @Test
    public void TestAvatar(){
        userService.changeAvatar(5,"/uu/wsh.jsp","xiaoming");
    }


}
