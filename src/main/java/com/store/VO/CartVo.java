package com.store.VO;

import lombok.*;

import java.io.Serializable;

//这个类来表示购物车数据的VO类
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartVo implements Serializable {

    private Integer cid;
    private Integer uid;
    private Integer pid;
    private Long price;
    private Integer num;
    private String title;
    private String image;
    private Long realPrice;


}
