package com.store.mapper;

/*用户模块的持久层接口*/

import com.store.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface UserMapper {

    /**
     * 插入用户的数据
     * @param user 用户的数据
     * @return 受影响的行数 可以根据返回值判断是否执行成功
     */
    Integer insertUser(User user);

    /**
     * 根据用户名来查询用户的数据
     * @param username 用户名
     * @return 如果找到对应的用户则返回这个用户，如果没有则返回null
     */
    User findByUsername(@Param("username") String username);

    /**
     * 根据用户的uid来修改用户的密码
     * @param uid
     *
     * @return 受影响的行数
     */
    Integer updatePasswordByUid(@Param("uid") Integer uid, @Param("password") String password, @Param("modifiedUser") String modifiedUser, @Param("modifiedTime") Date modifiedTime);


    /**
     * 根据用户的id查询用户的数据
     * @param uid
     * @return 找到了则返回对象，否则返回null
     */
    User findByUid(@Param("uid") Integer uid);

    /**
     * 更新用户的数据
     * @param user
     * @return
     */
    Integer updateInfoByUid(User user);


    /**
     * 根据用户的uid值来修改用户的头像
     * Param("SQL映射文件中#{}占位符的变量名") 解决的问题时是：当1sql语句的占位符和映射接口方法的参数名不一致时
     * 需要将某个参数强行注入到某个占位符变量上 使用这个注解
     * @param uid
     * @param avatar
     * @param modifiedUser
     * @param modifiedTime
     * @return
     */
    Integer updateAvatarByUid(@Param("uid") Integer uid,@Param("avatar") String avatar,@Param("modifiedUser") String modifiedUser,@Param("modifiedTime") Date modifiedTime);


}
