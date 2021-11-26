package com.neusoft.project.storage.domain;

import lombok.Data;

@Data
public class OrderRequest {
    private String nickName;
    private Integer status;
    private String emptyBoxOrderTimeStart;
    private String emptyBoxOrderTimeEnd;
    private String heavyBoxOrderTimeStart;
    private String heavyBoxOrderTimeEnd;
}
