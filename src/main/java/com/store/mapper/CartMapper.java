package com.store.mapper;

import com.store.VO.CartVo;
import com.store.pojo.Cart;
import org.apache.ibatis.annotations.Param;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;

import java.util.Date;
import java.util.List;

public interface CartMapper {

    //插入购物车数据
    Integer insert(Cart cart);

    //更新购物车某件商品的数量
    Integer updateNumByCid(@Param("cid") Integer cid, @Param("num") Integer num, @Param("modifiedUser") String modifiedUser,@Param("modifiedTime") Date modifiedTime);

    //根据用户的id和商品的id来查询购物车中的数据
    Cart findByUidAndPid(@Param("uid") Integer uid,@Param("pid") Integer pid);


    //VO:当进行select查询时，查询的结果属于多张表的内容，此时发现结果集不能直接使用某个pojo实体类来接收
    //因为实体类不能包含多表查询的结果，我们可以重新构建一个新的结果对象，用来存储查询出来的结果集对应的映射
    //称之为 值对象

    /**
     * 用于购物车列表的展示
     * @param uid
     * @return
     */
    List<CartVo> findVoByUid(Integer uid);


    //根据cid查询当前的购物车数据是否存在
    Cart findByCid(Integer cid);

    List<CartVo> findVoByCid(Integer[] cids);



}
