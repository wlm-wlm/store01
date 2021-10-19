package com.wlm.store.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 类名：BaseEntity
 * 描述：
 * 作者：汪灵敏
 * 日期：2021/8/26 1:49
 * 版本：V1.0
 */
/*实体类基类*/
@Data
public class BaseEntity implements Serializable {
    private String createdUser; //'日志-创建人',
    private Date createdTime; // '日志-创建时间',
    private String  modifiedUser; //COMMENT '日志-最后修改执行人',
    private Date modifiedTime;// COMMENT '日志-最后修改时间',
}
