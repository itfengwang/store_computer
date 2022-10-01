package com.store.service.impl;

import com.store.mapper.ProductMapper;
import com.store.pojo.Product;
import com.store.service.IProductService;
import com.store.service.ex.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductMapper productMapper;


    @Override
    public List<Product> findHotList() {

        List<Product> hotList = productMapper.findHotList();


        for (Product product : hotList) {

            product.setPriority(null);
            product.setCreatedUser(null);
            product.setCreatedTime(null);
            product.setModifiedUser(null);
            product.setModifiedTime(null);

        }

        return hotList;
    }

    @Override
    public Product findById(Integer id) {

        Product product = productMapper.findById(id);

        if (product == null){
            throw new ProductNotFoundException("您查找的商品不存在");

        }

        product.setPriority(null);
        product.setCreatedUser(null);
        product.setCreatedTime(null);
        product.setModifiedUser(null);
        product.setModifiedTime(null);


        return product;
    }
}
