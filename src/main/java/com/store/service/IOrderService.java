package com.store.service;

import com.store.pojo.Address;
import com.store.pojo.Order;

public interface IOrderService {

    Order create(Integer aid, Integer uid, String username, Integer[] cids);

}

