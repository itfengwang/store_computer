package com.store.pojo;

import lombok.*;


@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
/*省市区的数据的实体类*/
public class District extends BasePojo{

    private Integer id;
    private String parent;
    private String code;
    private String name;


}
