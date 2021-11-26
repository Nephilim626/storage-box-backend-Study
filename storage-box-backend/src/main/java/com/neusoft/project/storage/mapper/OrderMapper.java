package com.neusoft.project.storage.mapper;

import com.neusoft.project.storage.domain.Order;
import com.neusoft.project.storage.domain.OrderRequest;
import com.neusoft.project.storage.domain.OrderVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper {
    List<OrderVo> selectOrderList(OrderRequest orderRequest);
    OrderVo selectOrderListById(String id);
    int insertOrder(Order order);

    List<OrderVo> selectOrderListByUserId(Long userId);

    int updateOrder(OrderVo orderVo);

    OrderVo selectBackendOrderListById(String valueOf);

    OrderVo selectAllOrderListById(String valueOf);
}
