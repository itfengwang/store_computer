package com.store.service.impl;

import com.store.mapper.AddressMapper;
import com.store.mapper.DistrictMapper;
import com.store.pojo.Address;
import com.store.pojo.District;
import com.store.service.IAddressService;
import com.store.service.IDistrictService;
import com.store.service.ex.AddressCountLimitException;
import com.store.service.ex.InsertEException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service //将当前类的对象交给spring来管理，可以自动创建对象以及对象的维护
public class DistrictServiceImpl implements IDistrictService {

    @Autowired
    private DistrictMapper districtMapper;


    @Override
    public List<District> getByParent(String parent) {

        List<District> list = districtMapper.findByParent(parent);
        //在进行网络数据传输时，为了尽量避免无效数据的传递，我们可以将无效数据设置为null
        //可以节省流量，提升效率
        for(District d:list){
            d.setId(null);
            d.setParent(null);
        }

        return list;
    }

    /**
     * 根据code获取省市的名字
     * @param code
     * @return
     */
    @Override
    public String getNameByCode(String code) {

        return districtMapper.findNameByCode(code);
    }
}
