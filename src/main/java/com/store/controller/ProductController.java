package com.store.controller;

import com.store.Util.JsonResult;
import com.store.pojo.Product;
import com.store.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController extends BaseController {

    @Autowired
    private IProductService productService;

    @RequestMapping("/hot_list")
    public JsonResult<List<Product>> getHotList(){

        List<Product> data = productService.findHotList();

        return new JsonResult<List<Product>>(OK,data);

    }

    @RequestMapping("{id}/details")
    public JsonResult<Product> getProductById(@PathVariable("id") Integer id){

        //调用业务执行获得数据
        Product data = productService.findById(id);

        return new JsonResult<Product>(OK,data);


    }


}
