package com.store.service;

import com.store.VO.CartVo;
import com.store.pojo.Cart;

import java.util.List;

public interface ICartService {


    /**
     * 将商品添加到购物车
     * @param uid 用户id
     * @param pid 商品id
     * @param amount 新增商品的数量
     * @param username 用户名(修改者)
     */
   void addToCart(Integer uid,Integer pid,Integer amount,String username);


   List<CartVo> getVoByUid(Integer uid);

   //更新 增加 购物车的数量
   Integer addNum(Integer cid,Integer uid,String username);

   //更新 减少 购物车中商品的数量
   Integer lessNum(Integer cid,Integer uid,String username);


   List<CartVo> getVoByCid(Integer uid,Integer[] cids);






}
