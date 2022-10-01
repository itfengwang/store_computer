package com.store.service;

import com.store.pojo.District;
import com.store.pojo.User;

import java.io.StringReader;
import java.util.List;

/*用户模块业务接口*/
public interface IDistrictService {

    /**
     * 根据父代号来查询出它底下的区域信息（省市区都有可能）
     * @param parent
     * @return 多个区域的信息
     */
   List<District> getByParent(String parent);


   String getNameByCode(String code);


}
