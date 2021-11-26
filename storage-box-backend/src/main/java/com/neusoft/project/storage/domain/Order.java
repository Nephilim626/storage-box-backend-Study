package com.neusoft.project.storage.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.neusoft.framework.web.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
public class Order extends BaseEntity {
    private long id;
    private long userId;
    private Integer status;
    private long orderCode;
    private String orderName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date emptyBoxOrderTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date heavyBoxOrderTime;

    private String emptyBoxCallName;
    private String emptyBoxCallPhone;
    private String emptyBoxCallAddress;
    private Date emptyBoxCallTime;
    private String emptyBoxCallInterval;
    private String heavyBoxCallName;
    private String heavyBoxCallPhone;
    private String heavyBoxCallAddress;
    private Date heavyBoxCallTime;
    private String heavyBoxCallInterval;
    private long boxStandardId;
    private long boxCode;
    private String boxStandard;
    private long boxUnitPrice;
    private  int leaseDuration;
    private long totalPrice;
    private long sortNo;
    private short appDelFlag;
    private short backendDelFlag;

}
