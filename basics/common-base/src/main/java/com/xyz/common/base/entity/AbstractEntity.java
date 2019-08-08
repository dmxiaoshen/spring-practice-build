package com.xyz.common.base.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public abstract class AbstractEntity implements Serializable {

    protected String remarks;

    protected String createBy;

    protected String updateBy;

    protected Date createTime;

    protected Date updateTime;

    protected Boolean delFlag;
}
