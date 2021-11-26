package com.neusoft.project.storage.domain;

import com.neusoft.framework.web.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@ApiModel(value = "箱子信息",description = "箱子信息")
@Accessors(chain = true)
public class BoxInfo  extends BaseEntity {
        //id
        private long id;
        //箱子编号（年月日+6位序列）
        private long boxCode;
        //箱子规格（如20*20*20）
        private String boxStandard;
        //箱子积分单价（每月积分单价）
        private long boxUnitPrice;
        //使用人
        private long usedBy;
        //是否被使用（0：未使用；1：已使用）
        private short isUsed;
        //序号
        private  long sortNo;






}
