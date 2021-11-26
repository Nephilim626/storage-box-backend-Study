package com.neusoft.project.storage.controller;


import com.neusoft.framework.aspectj.lang.annotation.Log;
import com.neusoft.framework.aspectj.lang.enums.BusinessType;
import com.neusoft.framework.web.controller.BaseController;
import com.neusoft.framework.web.domain.AjaxResult;
import com.neusoft.framework.web.page.TableDataInfo;
import com.neusoft.project.storage.domain.OrderRequest;
import com.neusoft.project.storage.domain.OrderVo;
import com.neusoft.project.storage.domain.UserEntity;
import com.neusoft.project.storage.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/backend/order")
@Api(tags = {"5.3.6订单管理"},description = "订单列表（分页），订单详情，订单编辑")
public class BackendOrderController extends BaseController {

    @Resource
    private OrderService orderService;


    @Log(title = "订单列表（分页）",businessType = BusinessType.OTHER)
    @GetMapping("/list")
    @ApiOperation(value = "订单列表（分页）",notes = "查询订单列表")
    /*TableDataInfo(分页)*/
    public TableDataInfo list(OrderRequest orderRequest){
        /*获取分页信息*/
        startPage();
        //查询后台端用户列表
        List<OrderVo> list=orderService.selectOrderList(orderRequest);
        //返回响应请求分页数据
        return getDataTable(list);
    }

    @Log(title = "订单列表（分页）",businessType = BusinessType.OTHER)
    @GetMapping("/info/{id}")
    @ApiOperation(value = "订单列表（分页）",notes = "查询订单列表")
    public AjaxResult list(@PathVariable String id){
        OrderVo list=orderService.selectOrderListById(id);
        //返回响应请求分页数据
        return AjaxResult.success("查询成功",list);
    }
    @Log(title = "订单操作",businessType = BusinessType.UPDATE)
    @PutMapping("operate/{id}/{operate}")
    @ApiOperation(value = "5.2.4.2 订单操作",notes = "订单操作")
    public AjaxResult list(@PathVariable String id, @PathVariable String operate, OrderVo orderVo){

        if(orderService.updateOrder(id,operate,orderVo)>=1){
            return AjaxResult.success("操作成功");}
        return AjaxResult.error("操作失败");
    }

    @Log(title = "5.3.5.3 订单删除",businessType = BusinessType.DELETE)
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "5.3.5.3 订单删除",notes = "订单删除")
    public AjaxResult delete(@PathVariable("id") Long id){

        return  orderService.deleteBackendOrder(id);
    }

}
