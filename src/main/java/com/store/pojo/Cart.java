package com.store.pojo;

import lombok.*;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Cart extends BasePojo{
    private Integer cid; //购物车id

    private Integer uid;//用户id
    private Integer pid;//商品id
    private Long price;
    private Integer num;

}
