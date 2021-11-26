package com.neusoft.project.storage.domain;

import com.neusoft.framework.web.domain.BaseEntity;
import lombok.Data;

@Data
public class Advice extends BaseEntity {
    private long id;
    private long userId;
    private String title;
    private String content;
    private long sortNo;

}
