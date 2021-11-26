package com.neusoft.project.storage.domain;


import com.neusoft.framework.web.domain.BaseEntity;
import lombok.Data;

@Data
public class Advertisement extends BaseEntity {

    private long id;

    private String title;

    private String content;

    private String imgUrl;

    private  long points;

    private  short isEnable;

    private long sortNo;



}
