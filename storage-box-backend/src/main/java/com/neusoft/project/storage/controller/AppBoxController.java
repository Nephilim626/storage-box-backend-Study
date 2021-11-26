package com.neusoft.project.storage.controller;

import com.neusoft.framework.aspectj.lang.annotation.Log;
import com.neusoft.framework.aspectj.lang.enums.BusinessType;
import com.neusoft.framework.web.controller.BaseController;
import com.neusoft.framework.web.domain.AjaxResult;
import com.neusoft.project.storage.domain.*;
import com.neusoft.project.storage.service.AddressService;
import com.neusoft.project.storage.service.BoxInfoService;
import com.neusoft.project.storage.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/app/box")
@Api(tags = {"5.2.3空箱预约"},description = "空箱预约")
public class AppBoxController extends BaseController {

    @Resource
    private AddressService addressService;

    @Resource
    private BoxInfoService boxInfoService;

    @Resource
    private OrderService orderService;

    @Log(title = "获取当前用户默认收货地址",businessType = BusinessType.OTHER)
    @GetMapping("/defaultAddress")
    @ApiOperation(value = "获取当前用户默认收货地址",notes = "获取当前用户默认收货地址")
    public AjaxResult defaultAddress(AddressEntity address){

       AddressEntity address1=addressService.selectDefaultAddress(address);
        if (address1!=null){
            return AjaxResult.success("查询成功",address1);
        }
        return AjaxResult.error("查询失败");
    }

    @Log(title = "有效箱子规格选择",businessType = BusinessType.OTHER)
    @GetMapping("/standard/select")
    @ApiOperation(value = "有效箱子规格选择",notes = "有效箱子规格选择")
    public AjaxResult Validbox(BoxInfo boxInfo){
       List<BoxStandardSelectVO> list= boxInfoService.selectValidbox(boxInfo);
        if (list.size()!=0){
            return AjaxResult.success("查询成功",list);
        }
        return AjaxResult.error("查询失败");
    }


    @Log(title = "预约",businessType = BusinessType.OTHER)
    @PostMapping("/order")
    @ApiOperation(value = "预约",notes = "预约")
    public AjaxResult order(@RequestBody Order order){
        int num=orderService.setOrder(order);
        if (num>=3){
            return AjaxResult.success("预约成功");
        }
        return AjaxResult.error("预约失败");
    }

}
