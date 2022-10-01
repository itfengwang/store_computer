package com.store.pojo;

import lombok.*;

import java.io.Serializable;

/*收货地址的实体类*/
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Address extends BasePojo implements Serializable {
    private Integer aid;
    private Integer uid;
    private String name;
    private String provinceName;
    private String provinceCode;
    private String cityName;
    private String cityCode;
    private String areaName;
    private String areaCode;
    private String zip;
    private String address;
    private String phone;
    private String tel;
    private String tag;
    private Integer isDefault;




}
