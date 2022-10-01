package com.store.service.impl;

import com.store.VO.CartVo;
import com.store.mapper.OrderMapper;
import com.store.pojo.Address;
import com.store.pojo.Order;
import com.store.pojo.OrderItem;
import com.store.service.IAddressService;
import com.store.service.ICartService;
import com.store.service.IOrderService;
import com.store.service.ex.InsertEException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderImpl implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private IAddressService addressService;
    @Autowired
    private ICartService cartService;

    @Override
    public Order create(Integer aid, Integer uid, String username, Integer[] cids) {

        //即将要下单的列表
        List<CartVo> list = cartService.getVoByCid(uid, cids);

        //计算商品的总价
        Long totalPrice = 0L;
        for (CartVo vo : list) {

            totalPrice+=vo.getRealPrice()*vo.getNum();


        }

        Address address = addressService.getAddressByAid(aid, uid);
        Order order = new Order();

        order.setUid(uid);
        order.setRecvName(address.getName());
        order.setRecvPhone(address.getPhone());
        order.setRecvProvince(address.getProvinceName());
        order.setRecvCity(address.getCityName());
        order.setRecvAddress(address.getAddress());

        //支付 总价
        order.setStatus(0);
        order.setTotalPrice(totalPrice);
        order.setOrderTime(new Date(

        ));
        //日志
        order.setCreatedUser(username);
        order.setCreatedTime(new Date());
        order.setModifiedUser(username);
        order.setModifiedTime(new Date());

        //插入数据
        Integer rows = orderMapper.insertOrder(order);

        if(rows != 1){
            throw new InsertEException("插入时发生异常");
        }

        for (CartVo cartVo : list) {

            //创建订单项详细内容
            OrderItem orderItem = new OrderItem();
            orderItem.setOid(order.getOid());

            orderItem.setPid(cartVo.getPid());
            orderItem.setTitle(cartVo.getTitle());
            orderItem.setImage(cartVo.getImage());
            orderItem.setPrice(cartVo.getRealPrice());
            orderItem.setNum(cartVo.getNum());
            //日志字段
            orderItem.setModifiedUser(username);
            orderItem.setModifiedTime(new Date());
            orderItem.setCreatedUser(username);
            orderItem.setCreatedTime(new Date());

            Integer integer = orderMapper.insertOrderItem(orderItem);

            if(integer!=1){

                 throw new InsertEException("插入数据时产生异常");

             }

        }

        return order;

    }
}
