package com.store.controller;

import com.store.Util.JsonResult;
import com.store.pojo.Order;
import com.store.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/orders")
public class OrderController extends BaseController{
    @Autowired
    private IOrderService orderService;

    @RequestMapping("/create")
    public JsonResult<Order> create(Integer aid, Integer[] cids, HttpSession session){

        Integer uid= getUidFromSession(session);
        String username = getUsernameFromSession(session);

        Order data = orderService.create(aid, uid, username, cids);

        return new JsonResult<>(OK,data);



    }
}
