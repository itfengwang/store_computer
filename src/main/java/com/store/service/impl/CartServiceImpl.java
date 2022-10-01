package com.store.service.impl;

import com.store.VO.CartVo;
import com.store.mapper.CartMapper;
import com.store.mapper.ProductMapper;
import com.store.pojo.Cart;
import com.store.pojo.Product;
import com.store.service.ICartService;
import com.store.service.ex.AccessDeniedException;
import com.store.service.ex.CartNotFoundException;
import com.store.service.ex.InsertEException;
import com.store.service.ex.UpdateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class CartServiceImpl implements ICartService {


    /*购物车的业务层依赖于购物车以及商品的持久层*/
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private ProductMapper productMapper;


    /**
     * 加入购物车的方法
     * @param uid 用户id
     * @param pid 商品id
     * @param amount 新增商品的数量
     * @param username 用户名(修改者)
     */
    @Override
    public void addToCart(Integer uid, Integer pid, Integer amount, String username) {


        //查询当前要添加的商品是否在购物车中已经存在
        Cart result = cartMapper.findByUidAndPid(uid, pid);

        Date date = new Date();

        if(result == null){ //表示这个商品从来没有被添加到购物车中，进行新增操作
            //需要创建一个cart对象
            Cart cart = new Cart();
            cart.setUid(uid);
            cart.setPid(pid);
            cart.setNum(amount);
            //价格来自商品中的数据 补全价格
            Product product = productMapper.findById(pid);
            cart.setPrice(product.getPrice());

            //补全四个日志
            cart.setCreatedUser(username);
            cart.setCreatedTime(date);
            cart.setModifiedUser(username);
            cart.setModifiedTime(date);
            //执行数据的插入操作
            Integer rows = cartMapper.insert(cart);

            if(rows != 1){
                throw new InsertEException("插入数据时产生未知的异常");

            }


        } else {
            //这个商品被添加过，在原有的num上继续加数量
            //进行原有数据与现有数据进行相加
            Integer num = result.getNum()+amount;
            Integer cid = result.getCid();

            Integer rows = cartMapper.updateNumByCid(cid, num, username, date);

            if(rows !=1){
                throw new UpdateException("更新数据时产生未知的异常");

            }

        }

    }


    /**
     * 获取购物车列表的方法
     * @param uid
     * @return
     */
    @Override
    public List<CartVo> getVoByUid(Integer uid) {
        return cartMapper.findVoByUid(uid);

    }

    @Override
    public Integer addNum(Integer cid, Integer uid, String username) {

        Cart result = cartMapper.findByCid(cid);

        if(result == null){
            throw new CartNotFoundException("数据不存在");
        }

        if(!result.getUid().equals(uid)){

            throw new AccessDeniedException("数据非法访问");

        }

        Integer num = result.getNum() + 1;

        Integer rows = cartMapper.updateNumByCid(cid, num, username, new Date());

        if(rows !=1 ){
            throw new UpdateException("更新失败");
        }

        //返回新的购物车的总量
        return num;
    }


    @Override
    public Integer lessNum(Integer cid, Integer uid, String username) {

        Cart result = cartMapper.findByCid(cid);

        if(result == null){
            throw new CartNotFoundException("数据不存在");
        }

        if(!result.getUid().equals(uid)){

            throw new AccessDeniedException("数据非法访问");

        }

        Integer num = result.getNum() - 1;

        Integer rows = cartMapper.updateNumByCid(cid, num, username, new Date());

        if(rows !=1 ){
            throw new UpdateException("更新失败");
        }

        //返回新的购物车的总量
        return num;


    }



    @Override
    public List<CartVo> getVoByCid(Integer uid, Integer[] cids) {

        List<CartVo> list = cartMapper.findVoByCid(cids);
        //判断查询出来的数据是否全属于当前uid
        Iterator<CartVo> iterator = list.iterator();

        while (iterator.hasNext()){
            CartVo cartVo = iterator.next();

            if(!cartVo.getUid().equals(uid)){//当前元素不属于该uid
                //移除这个元素
                list.remove(cartVo);
            }

        }
        return list;

    }


}
