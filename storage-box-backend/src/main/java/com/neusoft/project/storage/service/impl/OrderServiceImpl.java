package com.neusoft.project.storage.service.impl;

import com.neusoft.common.exception.CustomException;
import com.neusoft.common.utils.DateUtils;
import com.neusoft.common.utils.SecurityUtils;
import com.neusoft.framework.web.domain.AjaxResult;
import com.neusoft.project.storage.domain.*;
import com.neusoft.project.storage.mapper.*;
import com.neusoft.project.storage.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private BoxInfoMapper boxInfoMapper;

    @Resource
    private PointsMapper pointsMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private OrderHisMapper orderHisMapper;

    @Override
    public List<OrderVo> selectOrderList(OrderRequest orderRequest) {
        return orderMapper.selectOrderList(orderRequest);
    }

    @Override
    public OrderVo selectOrderListById(String id) {

        return orderMapper.selectOrderListById(id);

    }

    @Override
    public int setOrder(Order order) {
        int count=0;
        //订单的插入
        order.setUserId(SecurityUtils.getUserId());
        order.setCreateBy(SecurityUtils.getUsername());
        order.setCreateTime(DateUtils.getNowDate());
        order.setStatus(1);
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyMMdd");
        long code= Long.parseLong(simpleDateFormat.format(DateUtils.getNowDate())+"000001");
        order.setOrderCode(code);
        BoxInfo boxInfo=new BoxInfo();
        boxInfo.setSearchValue(order.getBoxStandard());
       List<BoxInfo> list= boxInfoMapper.selectValidBoxInfoList(boxInfo);
       if (list.size()==0){
           throw new CustomException("该规格下没有可使用的箱子");
       }
       BoxInfo vBoxinfo =list.get(0);
       order.setBoxCode(vBoxinfo.getBoxCode());
       short isUsed=1;
       vBoxinfo.setIsUsed(isUsed);
       vBoxinfo.setUsedBy(SecurityUtils.getUserId());
       vBoxinfo.setUpdateBy(SecurityUtils.getUsername());
       vBoxinfo.setUpdateTime(DateUtils.getNowDate());
        boxInfoMapper.updateInfoIsUsed(vBoxinfo);

        count+=orderMapper.insertOrder(order);
       //积分记录的插入
       List<OrderVo> orderVoList= orderMapper.selectOrderListByUserId(SecurityUtils.getUserId());
        PointsRecord points=new PointsRecord();
        points.setOrderId( orderVoList.get(0).getId());
        points.setUserId(SecurityUtils.getUserId());
        short num=3;
        points.setWay(num);
        long num1=0-order.getTotalPrice();
        points.setPoints(num1);
        points.setCreateBy(SecurityUtils.getUsername());
        points.setCreateTime(DateUtils.getNowDate());
        long code1= Long.parseLong(simpleDateFormat.format(DateUtils.getNowDate())+"000001");
        points.setSortNo(code1);
        count+=pointsMapper.insertPointRecord(points);
        //用户的修改

        UserEntity userEntity=new UserEntity();
        userEntity.setUserId(SecurityUtils.getUserId());
        UserEntity user=userMapper.selectUserByUserId(SecurityUtils.getUserId());
        long num3=user.getCurrentPoints()+points.getPoints();
        if (num3<0){
            throw new CustomException("积分不足");
        }
        userEntity.setCurrentPoints(num3);
        userEntity.setUpdateBy(SecurityUtils.getUsername());
        userEntity.setUpdateTime(DateUtils.getNowDate());
        count+=userMapper.addUserPoints(userEntity);

        return count;
    }

    @Override
    public int updateOrder(String id, String operate, OrderVo orderVo) {
        orderVo.setId(Long.valueOf(id));
        orderVo.setUpdateBy(SecurityUtils.getUsername());
        orderVo.setUpdateTime(DateUtils.getNowDate());
        int count=0;
        switch (operate){
            case "2":
                orderVo.setStatus(2);
                count+= orderMapper.updateOrder(orderVo);
                break;
            case "3":
                orderVo.setStatus(3);
               count+= orderMapper.updateOrder(orderVo);
                break;
            case "4":
                orderVo.setStatus(4);
                count+= orderMapper.updateOrder(orderVo);
                break;
            case "5":
                orderVo.setStatus(5);
                count+= orderMapper.updateOrder(orderVo);
                break;
            case "6":
                orderVo.setStatus(6);
                if (orderVo.getHeavyBoxOrderTime()==null||orderVo.getHeavyBoxCallTime()==null||orderVo.getHeavyBoxCallAddress()==null){
                    throw new CustomException("必须输入重箱提取相关信息");
                }
                count+= orderMapper.updateOrder(orderVo);
                break;
            case "7":
                orderVo.setStatus(7);
                count+= orderMapper.updateOrder(orderVo);
                break;
            case "8":
                orderVo.setStatus(8);
                count+= orderMapper.updateOrder(orderVo);
                break;
            case "9":
                orderVo.setStatus(9);
                count+= orderMapper.updateOrder(orderVo);
                break;
            case "10":
                orderVo.setStatus(10);
                count+= orderMapper.updateOrder(orderVo);
                break;
        }
        return count;
    }

    @Override
    public AjaxResult deleteOrder(Long id) {
        int count=0;
       OrderVo orderVo= orderMapper.selectOrderListById(String.valueOf(id));
        if (orderVo.getStatus()!=10){
            throw new CustomException("该订单未完成无法删除");
        }else {
            OrderVo orderVo1 =new OrderVo();
            orderVo1.setId(id);
            short num =2;
            orderVo1.setAppDelFlag(num);
            orderVo1.setUpdateBy(SecurityUtils.getUsername());
            orderVo1.setUpdateTime(DateUtils.getNowDate());
          count+= orderMapper.updateOrder(orderVo1);
        }
         if(count>=1) {
             return AjaxResult.success("删除成功");
         }
        return AjaxResult.error("删除失败");
    }

    @Override
    public AjaxResult deleteBackendOrder(Long id) {

        int count=0;
        OrderVo orderVo= orderMapper.selectBackendOrderListById(String.valueOf(id));
        if (orderVo.getStatus()!=10){
            throw new CustomException("该订单未完成无法删除");
        } else if(orderVo.getAppDelFlag()!=2){
            throw new CustomException("该订单客户还需查看无法删除");
        }else {
        OrderVo orderVo1 =new OrderVo();
            orderVo1.setId(id);
            short num =2;
            orderVo1.setBackendDelFlag(num);
            orderVo1.setDelFlag("2");
            orderVo1.setUpdateBy(SecurityUtils.getUsername());
            orderVo1.setUpdateTime(DateUtils.getNowDate());
            orderVo1.setAppDelFlag(num);
            count+= orderMapper.updateOrder(orderVo1);
           OrderVo orderVo2= orderMapper.selectAllOrderListById(String.valueOf(id));
            Long order_id=orderVo2.getId();
            orderVo2.setOrder_id(orderVo2.getId());
            orderHisMapper.insertOrderHis(orderVo2);
        }
        if(count>=1) {
            return AjaxResult.success("删除成功");
        }
        return AjaxResult.error("删除失败");
    }

}
