package com.neusoft.project.storage.domain;

import lombok.Data;

@Data
public class OrderVo extends Order{
    private String nickName;
    private Long order_id;
}
