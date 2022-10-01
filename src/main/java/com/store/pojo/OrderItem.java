package com.store.pojo;

import lombok.*;

import java.io.Serializable;
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderItem extends BasePojo implements Serializable {
    private Integer id;
    private Integer oid;
    private Integer pid;
    private String title;
    private String image;
    private Long price;
    private Integer num;

}
