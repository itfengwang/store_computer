package com.store.service.impl;

import com.store.mapper.AddressMapper;
import com.store.mapper.UserMapper;
import com.store.pojo.Address;
import com.store.pojo.User;
import com.store.service.IAddressService;
import com.store.service.IDistrictService;
import com.store.service.IUserService;
import com.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service //将当前类的对象交给spring来管理，可以自动创建对象以及对象的维护
public class AddressServiceImpl implements IAddressService {


    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private IDistrictService districtService;

    @Value("${user.address.max-count}")
    private Integer maxCount;
    /**
     * 新增收货地址
     * @param uid
     * @param username
     * @param address
     */
    @Override
    public void addNewAddress(Integer uid, String username, Address address) {

        //调用收货地址统计的方法
        Integer count = addressMapper.countByUid(uid);
        if(count>=maxCount){

            throw new AddressCountLimitException("收货地址已经超出上限");

        }
        //对 address对象中数据进行补全
        String ProvinceName = districtService.getNameByCode(address.getProvinceCode());
        String CityName = districtService.getNameByCode(address.getCityCode());
        String AreaName = districtService.getNameByCode(address.getAreaCode());

        address.setProvinceName(ProvinceName);
        address.setCityName(CityName);
        address.setAreaName(AreaName);
        //补全uid，default
        address.setUid(uid);
        Integer isDefault = count == 0 ? 1 : 0;
        address.setIsDefault(isDefault);

        //补全四项日志
        address.setCreatedUser(username);
        address.setCreatedTime(new Date());
        address.setModifiedUser(username);
        address.setModifiedTime(new Date());

        //调用收货地址的方法
        Integer rows = addressMapper.insert(address);

        if(rows !=1 ){
            throw new InsertEException("插入时发生异常");

        }

    }

    @Override
    public List<Address> getByUid(Integer uid) {

        List<Address> addresses = addressMapper.findByUid(uid);

        for (Address address : addresses) {
            //address.setAid(null);
            //address.setUid(null);
            address.setProvinceCode(null);
            address.setCityCode(null);
            address.setAreaCode(null);
            address.setTel(null);
            address.setCreatedTime(null);
            address.setIsDefault(null);
            address.setCreatedUser(null);
            address.setModifiedTime(null);
            address.setModifiedUser(null);

        }
        return addresses;
    }

    /**
     * 设置默认地址的方法
     * @param aid 收货地址的id
     * @param uid
     * @param username 修改执行的人
     */
    @Override
    public void setDefaultAddresss(Integer aid, Integer uid, String username) {

        Address address = addressMapper.findByAid(aid);
        if(address == null){

            throw new AddressNotFoundException("地址不存在熬");

        }
        //检测当前获取到的收货地址数据的归属是否为当前登录者的
        if (!address.getUid().equals(uid)){
            //如果不相等
            throw new AccessDeniedException("非法数据访问");

        }

        //先将所有的收货地址设置为非默认
        Integer rows = addressMapper.updateNonDefault(uid);
        if(rows<1){
            throw new UpdateException("更新数据时产生未知异常");

        }
        //将用户选中的某一条地址设置为默认收货地址
        rows = addressMapper.updateDefaultByAid(aid, username, new Date());
        if(rows!=1){
            throw new UpdateException("更新数据时产生未知异常");

        }

    }

    //删除指定的收货地址
    @Override
    public void delete(Integer aid, Integer uid, String username) {

        Address address = addressMapper.findByAid(aid);

        if(address == null){
            throw  new AddressNotFoundException("查找不到改地址");
        }

        //检测当前获取到的收货地址数据的归属是否为当前登录者的
        if (!address.getUid().equals(uid)){
            //如果不相等
            throw new AccessDeniedException("非法数据访问");

        }

        //根据aid来删除数据
        Integer rows = addressMapper.deleteByAid(aid);

        if(rows != 1){
            throw new DeleteException("删除异常");

        }

        Integer count = addressMapper.countByUid(uid);

        if(count == 0){

            return;//终止程序
        }


        //如果我们删除的地址是默认地址 那么就将最后修改的那个地址设置为默认地址再
        if(address.getIsDefault() == 1){

            //将这条数据中的 isDefault 设置为1
            Address result = addressMapper.findLastModified(uid);

            rows= addressMapper.updateDefaultByAid(result.getAid(),username,new Date());
        }


       if(rows !=1){
           throw new UpdateException("更新数据时产生未知的异常");

       }




    }

    @Override
    public Address getAddressByAid(Integer aid,Integer uid) {
        Address address = addressMapper.findByAid(aid);

        if(address == null){
            throw new AddressNotFoundException("您查找的地址不存在");

        }

        if(!address.getUid().equals(uid)){
            throw new AccessDeniedException("非法访问！！！");
        }

        address.setProvinceCode(null);
        address.setCityCode(null);
        address.setAreaCode(null);
        address.setModifiedUser(null);
        address.setModifiedTime(null);
        address.setCreatedTime(null);
        address.setCreatedUser(null);

        return address;
    }


}
