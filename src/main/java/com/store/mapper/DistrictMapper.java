package com.store.mapper;

import com.store.pojo.Address;
import com.store.pojo.District;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 表示收货地址持久层的接口
 */
public interface DistrictMapper {

    /**
     * 根据父代号查询区域信息 返回的是某个父区域下的所有区域列表
     * @param parent
     * @return
     */
     List<District> findByParent(String parent);

     String findNameByCode(String code);





}
