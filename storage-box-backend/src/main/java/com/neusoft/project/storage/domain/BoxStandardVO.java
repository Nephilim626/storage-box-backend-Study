package com.neusoft.project.storage.domain;

import com.neusoft.framework.web.domain.BaseEntity;
import lombok.Data;

@Data
public class BoxStandardVO  extends BoxStandardEntity {
    //总数量
    private long totalNumber;
    //已使用数量
    private long  usedNumber;
    //库存数量
    private long  inventoryNumber;
    //使用比例
    private String useRatio;
}
