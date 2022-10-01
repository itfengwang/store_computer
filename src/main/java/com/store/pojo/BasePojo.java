package com.store.pojo;

import lombok.*;

import java.io.Serializable;
import java.util.Date;


@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BasePojo implements Serializable {

    //日志创建人
    private String createdUser;
    //日志创建时间
    private Date createdTime;
    //日志 最后修改执行人
    private String modifiedUser;
    //日志 最后修改时间
    private Date modifiedTime;




}
