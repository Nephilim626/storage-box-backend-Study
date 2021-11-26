package com.neusoft.project.storage.domain;

import com.neusoft.framework.web.domain.BaseEntity;
import lombok.Data;

@Data
public class PointsRecord extends BaseEntity {
    private  Long id;
    private Long userId;
    private  Short way;
    private Long points;
    private Long advertisementId;
    private Long orderId;
    private Long sortNo;

}
