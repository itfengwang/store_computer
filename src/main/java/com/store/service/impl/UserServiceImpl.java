package com.store.service.impl;

import com.store.mapper.UserMapper;
import com.store.pojo.User;
import com.store.service.IUserService;
import com.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

@Service //将当前类的对象交给spring来管理，可以自动创建对象以及对象的维护
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;
   //注册功能
    @Override
    public void reg(User user) {
        //业务逻辑
        //通过user参数获取传递过来的name
        String username = user.getUsername();
        //判断用户是否被注册过 被注册过直接抛出

        User result = userMapper.findByUsername(username);

        if(result != null){
            throw new UsernameDuplicatedException("用户名被占用");
        }


        //密码加密处理的实现 md5算法
        //盐值 + password +盐值   ----盐值是一个随机数
        String oldPassword = user.getPassword();

         //获取盐值 随机生成一个盐值
               String salt = UUID.randomUUID().toString().toUpperCase();
               //将此刻的盐值记录
               user.setSalt(salt);
               //调用算法加密的方法
               String md5Password = getMD5Password(oldPassword, salt);
               //重新补全到user对象中
               user.setPassword(md5Password);


               //补全数据
               user.setIsDelete(0);
               //4个日志字段信息
               user.setCreatedUser(user.getUsername());
               user.setModifiedUser(user.getUsername());
               Date date = new Date();
               user.setCreatedTime(date);
               user.setModifiedTime(date);


               //执行注册业务功能的实现
               Integer rows = userMapper.insertUser(user);

               if(rows != 1){

                   throw new InsertEException("在用户注册过程中产生了未知的异常");
               }


    }


    //定义一个md5算法的加密处理
    private String getMD5Password(String password,String salt){
        //MD5算法方法的调用 要进行三次加密
        for (int i = 0; i < 3; i++) {

                password = DigestUtils.md5DigestAsHex((salt+password+salt).getBytes()).toUpperCase();

        }
        //返回加密之后的password
        return password;

    }



    @Override
    /*登录的功能*/
    public User login(String username, String password) {
        //根据用户名称去查询用户的数据是否存在，不存在则抛出异常
        User result = userMapper.findByUsername(username);

        if(result==null){
            throw new UserNotFoundException("用户数据不存在");
        }

        //检测用户的密码是否匹配
        //获取数据库中加密之后的密码
        String oldPassword = result.getPassword();

        //1 先获取到数据库中加密的盐值
        String salt = result.getSalt();
        // 将用户的密码按照相同的md5算法进行加密
        String md5Password = getMD5Password(password, salt);
        //将用户输入的密码加密后与数据库中的密码进行比较
        if(!md5Password.equals(oldPassword)){
            throw new PasswordNotMatchException("用户密码输入错误");

        }
        //判断is_delete字段是否为1表示被标记为删除
        if(result.getIsDelete() == 1){
            throw new UserNotFoundException("用户数据不存在");
        }

        //查询该登录用户的数据  提升了系统的性能
        User user = new User();
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());
        user.setAvatar(result.getAvatar());

        //将当前的用户数据返回,辅助其他页面，做辅助展示用
        return user;
    }

    /**
     * 修改用户密码
     * @param uid
     * @param username
     * @param oldPassword
     * @param newPassword
     */
    @Override
    public void changePassword(Integer uid, String username, String oldPassword, String newPassword) {

        User result = userMapper.findByUid(uid);

        if(result == null || result.getIsDelete() == 1){
            throw new UserNotFoundException("用户数据不存在");
        }
        //原始密码与数据库中密码进行比较
        //拿到原始加密之后的密码
        String oldMd5Password = getMD5Password(oldPassword,result.getSalt());

        if(!result.getPassword().equals(oldMd5Password)){

            throw new PasswordNotMatchException("密码错误");

        }
        //将新的密码设置到数据库中
        //将新的密码进行加密 在进行更新1
        String newMd5Password = getMD5Password(newPassword,result.getSalt());

        Integer rows = userMapper.updatePasswordByUid(uid, newMd5Password, username, new Date());

        if(rows!=1){
            throw new UpdateException("更新数据时产生未知的异常");

        }

    }

    /**
     * 根据id获取到用户
     * @param uid
     * @return
     */
    @Override
    public User getByUid(Integer uid) {

        User result = userMapper.findByUid(uid);

        if(result == null || result.getIsDelete()==1){
            throw new UserNotFoundException("用户没有被找到");
        }

        User user = new User();
        user.setUsername(result.getUsername());
        user.setPhone(result.getPhone());
        user.setEmail(result.getEmail());
        user.setGender(result.getGender());

        return user;
    }

    /**
     * user 参数中只有phonr，email，gender  需要手动将uid username封装到user对象中
     * @param uid
     * @param username
     * @param user
     */
    @Override
    public void changeInFo(Integer uid, String username, User user) {

        User result = userMapper.findByUid(uid);

        if(result == null || result.getIsDelete()==1){
            throw new UserNotFoundException("用户没有被找到");
        }


        user.setUid(uid);
        //user.setUsername(username);
        user.setModifiedUser(username);
        user.setModifiedTime(new Date());


        Integer rows = userMapper.updateInfoByUid(user);

        if(rows!=1){
            throw new UpdateException("更新出现了未指的错误");
        }


    }


    /**
     * 修改用户的头像
     * @param uid 用户的id
     * @param avatar 用户头像的路径
     * @param username 用户的名称
     */
    @Override
    public void changeAvatar(Integer uid, String avatar, String username) {

        //查询当前的用户是否存在
        User result = userMapper.findByUid(uid);
        if(result == null || result.getIsDelete().equals(1)){
            throw new UserNotFoundException("用户没有被找到");
        }

        Integer rows = userMapper.updateAvatarByUid(uid, avatar, username, new Date());

        if(rows != 1){
            throw new UpdateException("更新用户头像时产生异常");
        }
    }




}
