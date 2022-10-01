package com.store.mapper;

import com.store.pojo.Product;

import java.util.List;

public interface ProductMapper {

    //查询热销商品并展示
    List<Product> findHotList();

    //根据id查询商品详情
    Product findById(Integer id);


}
