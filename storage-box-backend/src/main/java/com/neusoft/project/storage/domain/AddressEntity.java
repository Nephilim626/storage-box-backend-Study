package com.neusoft.project.storage.domain;

import com.neusoft.framework.web.domain.BaseEntity;
import lombok.Data;

@Data
public class AddressEntity extends BaseEntity {
    private Long id;
    private Long userId;
    private String contacts;
    private String contactsPhone;
    private String province;
    private String provinceName;
    private String city;
    private String cityName;
    private String area;
    private String areaName;
    private String address;
    private Short isDefault;
    private Long sortNo;
}
