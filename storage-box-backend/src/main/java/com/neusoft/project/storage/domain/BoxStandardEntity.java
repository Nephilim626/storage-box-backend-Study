package com.neusoft.project.storage.domain;

import com.neusoft.framework.web.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "箱子标准",description = "箱子标准")
public class BoxStandardEntity extends BaseEntity {
    //箱子ID
    private long id;
    //箱子规格(20*20*20)
    private String boxStandard;
    /*箱子积分单价（每月积分单价）*/
    private  long boxUnitPrice;
     /*  备注*/
    private String remark;
    //序号
    private long sortNo;
}
