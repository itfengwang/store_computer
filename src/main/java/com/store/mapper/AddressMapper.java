package com.store.mapper;

import com.store.pojo.Address;
import com.store.pojo.District;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 表示收货地址持久层的接口
 */
public interface AddressMapper {

    //插入用户的收货地址数据
    Integer insert(Address address);

    /**
     * 根据用户的id统计收货地址的数量
     * @return
     */
    Integer countByUid(@Param("uid") Integer uid);

    //根据用户的id 查询用户的收货地址数据
    List<Address> findByUid(Integer uid);

    //根据aid查询收货地址数据
    Address findByAid(@Param("aid") Integer aid);

    //根据用户的uid来修改用户的收货地址 设置为非默认
    Integer updateNonDefault(@Param("uid") Integer uid);

    //设置某用户的默认收货地址
    Integer updateDefaultByAid(@Param("aid") Integer aid,@Param("modifiedUser") String modifiesUser,@Param("modifiedTime") Date modifiedTime);

    //根据aid删除地址的方法
    Integer deleteByAid(@Param("aid") Integer aid);

    //根据uid来查询当前用户最后一次被修改的收货地址的数据
    Address findLastModified(@Param("uid")Integer uid);



}
