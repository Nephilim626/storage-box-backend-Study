package com.neusoft.project.storage.service;

import com.neusoft.framework.web.domain.AjaxResult;
import com.neusoft.project.storage.domain.Order;
import com.neusoft.project.storage.domain.OrderRequest;
import com.neusoft.project.storage.domain.OrderVo;

import java.util.List;

public interface OrderService {
    List<OrderVo> selectOrderList(OrderRequest orderRequest);

    OrderVo selectOrderListById(String id);

    int setOrder(Order order);

     int updateOrder(String id, String operate, OrderVo orderVo);

    AjaxResult deleteOrder(Long id);

    AjaxResult deleteBackendOrder(Long id);
}
