package com.store.pojo;

import lombok.*;

import java.io.Serializable;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product extends BasePojo implements Serializable {


    private Integer id;
    private Integer categoryID;
    private String itemType;
    private String title;
    private String sellPoint;
    private Long price;
    private Integer num;
    private String image;
    private Integer status;
    private Integer priority;


}
