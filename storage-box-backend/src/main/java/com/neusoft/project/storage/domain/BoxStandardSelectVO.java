package com.neusoft.project.storage.domain;

import lombok.Data;

@Data
public class BoxStandardSelectVO {
    private long id;
    //箱子规格(20*20*20)
    private String boxStandard;
    /*箱子积分单价（每月积分单价）*/
    private  long boxUnitPrice;
}
