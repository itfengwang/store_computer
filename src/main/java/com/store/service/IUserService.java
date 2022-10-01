package com.store.service;

import com.store.pojo.User;

import java.io.StringReader;

/*用户模块业务接口*/
public interface IUserService {

    /**
     * 用户注册的方法
     * @param user
     */
    void reg(User user);

    //将当前登录成功的用户整体的数据给返回

    /**
     *
     * @param username
     * @param password
     * @return
     */
    User login(String username,String password);

    /**
     * 修改密码
     * @param uid
     * @param username
     * @param oldPassword
     * @param newPassword
     */
    void changePassword(Integer uid,String username,String oldPassword,String newPassword);


    /**
     * 根据用户的id查询用户的数据
     * @param uid
     * @return
     */
    User getByUid(Integer uid);


    /**
     * 更新用户的数据操作
     * @param uid
     * @param username
     * @param user
     */
    void changeInFo(Integer uid, String username,User user);


    /**
     * 修改用户的头像
     * @param uid 用户的id
     * @param avatar 用户头像的路径
     * @param username 用户的名称
     */
    void changeAvatar(Integer uid,String avatar,String username);



}
