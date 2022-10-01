package com.store.service;

import com.store.pojo.Product;
import com.store.pojo.User;

import java.util.List;

/*用户模块业务接口*/
public interface IProductService {

    List<Product> findHotList();


    Product findById(Integer id);


}
