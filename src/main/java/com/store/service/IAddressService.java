package com.store.service;


import com.store.pojo.Address;

import java.util.List;

/*收货地址业务接口*/
public interface IAddressService {

    /**
     * 新增收货地址的方法
     * @param uid
     * @param username
     * @param address
     */
    void addNewAddress(Integer uid, String username,Address address);


    /**
     * 根据uid获取用户的收货地址
     * @return
     */
    List<Address> getByUid(Integer uid);


    /**
     * 修改某个用户的收货地址的默认收货地址
     * @param aid 收货地址的id
     * @param uid
     * @param username 修改执行的人
     */
   void setDefaultAddresss(Integer aid,Integer uid,String username);


   //删除用户选中的收货地址的数据
   void delete(Integer aid,Integer uid ,String username);


   //根据id获取收货地址的数据
   Address getAddressByAid(Integer aid,Integer uid);




}
