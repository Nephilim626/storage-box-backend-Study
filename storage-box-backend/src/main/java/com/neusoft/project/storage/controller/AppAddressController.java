package com.neusoft.project.storage.controller;

import com.neusoft.framework.aspectj.lang.annotation.Log;
import com.neusoft.framework.aspectj.lang.enums.BusinessType;
import com.neusoft.framework.web.controller.BaseController;
import com.neusoft.framework.web.domain.AjaxResult;
import com.neusoft.framework.web.page.TableDataInfo;
import com.neusoft.project.storage.domain.AddressEntity;
import com.neusoft.project.storage.service.AddressService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/app/address")
public class AppAddressController extends BaseController {
    @Resource
    private AddressService addressService;

    @Log(title = "地址列表" ,businessType = BusinessType.OTHER)
    @GetMapping("/list")
    public TableDataInfo list(AddressEntity address){
        return getDataTable(addressService.selectAddressList(address));
    }

    @Log(title = "地址新增" ,businessType = BusinessType.INSERT)
    @PostMapping("/create")
    public AjaxResult add(@RequestBody AddressEntity address){
        if(addressService.insertAddress(address)>0){
            return AjaxResult.success("新增成功");
        }
        return AjaxResult.error("新增失败");
    }

    @Log(title = "地址编辑" ,businessType = BusinessType.UPDATE)
    @PutMapping("/update")
    public AjaxResult update(@RequestBody AddressEntity address){
        if(addressService.updateAddress(address)>0){
            return AjaxResult.success("修改成功");
        }
        return AjaxResult.error("修改失败");
    }

    @Log(title = "修改默认地址",businessType=BusinessType.UPDATE)
    @PutMapping("/default/{id}")
    public AjaxResult updateDefault(AddressEntity address){
        if(addressService.updateDefault(address)>0){
            return AjaxResult.success("修改成功");
        }
        return AjaxResult.error("修改失败");
    }

    @Log(title = "删除地址",businessType=BusinessType.DELETE)
    @DeleteMapping("/delete/{id}")
    public AjaxResult delete(AddressEntity address){
        if(addressService.deleteAddress(address)>0){
            return AjaxResult.success("删除成功");
        }
        return AjaxResult.error("删除失败");
    }
}
